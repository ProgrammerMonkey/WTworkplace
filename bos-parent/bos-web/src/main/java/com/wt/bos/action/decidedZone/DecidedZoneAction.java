package com.wt.bos.action.decidedZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.wt.bos.action.BaseAction;
import com.wt.bos.domain.bc.DecidedZone;
import com.wt.bos.domain.bc.Region;
import com.wt.mavencrm.domain.Customer;
@Controller
@ParentPackage("mavenbos")
@Namespace("/")
@Scope("prototype")   
public class DecidedZoneAction extends BaseAction<DecidedZone>{

	@Action(value = "decidedZoneAction_save", results = { @Result(name = "save", location = "/WEB-INF/pages/base/decidedzone.jsp")})
	public String save() throws Exception {
		String[] ids = getRequest().getParameterValues("subareaId");
		facadeService.getDecidedZoneService().save(model,ids);
		return "save";
	}
	
	@Action(value = "decidedZoneAction_pageQuery")
	public String pageQuery() throws Exception {
		Page<DecidedZone> pageResponse = facadeService.getDecidedZoneService().pageQuery(getPageRequest(),getConditons());
		setPageData(pageResponse);
		return "pageQuery";
	}
	
	//条件的封装
		private Specification<DecidedZone> getConditons() {
			return new Specification<DecidedZone>() {
				@Override
				public Predicate toPredicate(Root<DecidedZone> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					ArrayList<Predicate> list = new ArrayList<Predicate>();
					if(StringUtils.isNotBlank(model.getId())){
						Predicate p1 = cb.equal(root.get("id").as(String.class), model.getId());
						list.add(p1);
					}
					// 连接staff
					if(model.getStaff()!=null){
						Join<DecidedZone, Region> join = root.join(root.getModel().getSingularAttribute("staff", Region.class),JoinType.LEFT);
						if (StringUtils.isNotBlank(model.getStaff().getStation())) {
							Predicate c2 = cb.like(join.get("station").as(String.class), "%"+model.getStaff().getStation()+"%");
							list.add(c2);
						}
					}
					String association = getParameter("isAssociationSubarea");
					// 连接subarea分区表
					if(StringUtils.isNotBlank(association)){
						if("0".equals(association)){
							Predicate c3 = cb.isEmpty(root.get("subareas").as(Set.class));
							list.add(c3);
						}else if("1".equals(association))
							{
							Predicate c3 = cb.isNotEmpty(root.get("subareas").as(Set.class));
							list.add(c3);
						}
					}
					Predicate[] predicates = new Predicate[list.size()];
					return cb.and(list.toArray(predicates));//条件用and连接
				}
			};
		}
		
		
		@Action(value = "decidedZoneAction_findnoassociationcustomers", results = { @Result(name = "findnoassociationcustomers", type = "json") })
		public String findnoassociationcustomers() {
			List<Customer> customers = facadeService.getDecidedZoneService().findnoassociationcustomers();
			push(customers);
			return "findnoassociationcustomers";
		}

		@Action(value = "decidedZoneAction_findassociationcustomers", results = { @Result(name = "findassociationcustomers", type = "json") })
		public String findassociationcustomers() {
			List<Customer> customers = facadeService.getDecidedZoneService().findassociationcustomers(model.getId());
			push(customers);
			return "findassociationcustomers";
		}
		
		@Action(value = "decidedZoneAction_assigncustomerstodecidedzone", results = { @Result(name = "assigncustomerstodecidedzone", location = "/WEB-INF/pages/base/decidedzone.jsp") })
		public String assigncustomerstodecidedzone() {
			// 客户ids
			String[] customerIds = getRequest().getParameterValues("customerIds");// 1,2,3
			facadeService.getDecidedZoneService().assigncustomerstodecidedzone(model.getId(), customerIds);
			return "assigncustomerstodecidedzone";
		}
}
