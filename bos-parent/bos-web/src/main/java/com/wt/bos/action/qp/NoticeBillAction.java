package com.wt.bos.action.qp;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.wt.bos.action.BaseAction;
import com.wt.bos.domain.qp.NoticeBill;
import com.wt.bos.domain.user.User;
import com.wt.mavencrm.domain.Customer;

@Controller
@ParentPackage("mavenbos")
@Namespace("/")
@Scope("prototype")
public class NoticeBillAction extends BaseAction<NoticeBill> {
	static Logger logger = Logger.getLogger(NoticeBillAction.class);
	
	
	@Action(value = "noticeBillAction_findCustomerByTelephone", results = { @Result(name = "findCustomerByTelephone", type="json") })
	public String findCustomerByTelephone() throws Exception {
		String telephone = getParameter("telephone");
	 	Customer customer = facadeService.getNoticeBillService().findCustomerByTelephone(telephone);
		push(customer);
		return "findCustomerByTelephone";
	}
	
	@Action(value = "noticeBillAction_save", results = { @Result(name = "save", location = "/WEB-INF/pages/qupai/noticebill_add.jsp") })
	public String save() throws Exception {
		String province = getParameter("province");
		String city = getParameter("city");
		String district = getParameter("district");
		model.setPickaddress(province+city+district+model.getPickaddress());
		
		User exitsUser = (User) getSessionAttribute("exitsUser");
		model.setUser(exitsUser);
		facadeService.getNoticeBillService().save(model,province,city,district);
		return "save";
	}
/*

	@Action(value = "regionAction_ajaxNameList", results = { @Result(name = "ajaxNameList", type="json") })
	public String ajaxNameList() throws Exception {
		try {
			String parameter = getParameter("q");
			List<Region> list =facadeService.getRegionService().findAll(parameter);
			push(list);
		} catch (Exception e) {
			logger.error("查询失败");
			e.printStackTrace();
		}
		
		return "ajaxNameList";
	}
	
	@Action(value = "regionAction_pageQuery")
	public String pageQuery() throws Exception {
		Specification<Region> specification = getConditons();
		
		String paramSort = getParameter("sort");
		String paramOrder = getParameter("order");
		
		if (StringUtils.isNotBlank(paramOrder)
				&& StringUtils.isNotBlank(paramSort)) {
			Direction direction = Direction.fromString(paramOrder);
			Sort sort = new Sort(direction, paramSort);
			pageRequest = getPageRequest(sort);
		} else {
			pageRequest = getPageRequest();
		}
		
		Page<Region> responsePage = facadeService.getRegionService().pageQuery(
				pageRequest,specification);
		setPageData(responsePage);
		
		
		return "pageQuery";
	}

	private Specification<Region> getConditons() {
		return new Specification<Region>() {
			@Override
			public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getProvince())){
					Predicate p1 = cb.like(root.get("province").as(String.class), "%"+model.getProvince()+"%");
					list.add(p1);
				}
				if(StringUtils.isNotBlank(model.getCity())){
					Predicate p2 = cb.equal(root.get("city").as(String.class), model.getCity());
					list.add(p2);
				}
				if(StringUtils.isNotBlank(model.getDistrict())){
					Predicate p3 = cb.equal(root.get("district").as(String.class), model.getDistrict());
					list.add(p3);
				}
				Predicate[] predicates = new Predicate[list.size()];
				return cb.and(list.toArray(predicates));//条件用and连接
			}
		};
	}*/
}
