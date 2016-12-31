package com.wt.bos.action.staff;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wt.bos.action.BaseAction;
import com.wt.bos.domain.bc.Staff;
import com.wt.bos.domain.bc.Standard;
import com.wt.bos.domain.user.User;

@Controller
@ParentPackage("mavenbos")
@Namespace("/")
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Action(value = "staffAction_save", results = { @Result(name = "save", location = "/WEB-INF/pages/base/staff.jsp") })
	public String save() throws Exception {
		facadeService.getStaffService().save(model);
		return "save";
	}

	@Action(value = "staffAction_validTelephone", results = { @Result(name = "validTelephone", type = "json") })
	public String validTelephone() throws Exception {
		boolean flag = false;
		Staff s = facadeService.getStaffService().findStaffByTelephone(
				model.getTelephone());
		if (s == null) {
			flag = true;
		}
		push(flag);
		return "validTelephone";
	}
	@Action(value = "staffAction_ajaxList", results = { @Result(name = "ajaxList", type = "json") })
	public String ajaxList() throws Exception {
		List<Staff> list = facadeService.getStaffService().ajaxList();
		push(list);
		return "ajaxList";
	}

	@Action(value = "staffAction_pageQuery", results = { @Result(name = "pageQuery", type = "json") })
	public String pageQuery() throws Exception {
		Specification<Staff> specification = new Specification<Staff>() {
			@Override
			public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getName())){
					Predicate p1 = cb.like(root.get("name").as(String.class), "%"+model.getName()+"%");
					list.add(p1);
				}
				if(StringUtils.isNotBlank(model.getTelephone())){
					Predicate p2 = cb.equal(root.get("telephone").as(String.class), model.getTelephone());
					list.add(p2);
				}
				if(StringUtils.isNotBlank(model.getStation())){
					Predicate p3 = cb.equal(root.get("station").as(String.class), model.getStation());
					list.add(p3);
				}
				Predicate[] predicates = new Predicate[list.size()];
				return cb.and(list.toArray(predicates));//条件用and连接
			}
		};
		Map<String, Object> map = new HashMap<String, Object>();
		String paramSort = getParameter("sort");
		String paramOrder = getParameter("order");
		PageRequest pageRequest;
		if (StringUtils.isNotBlank(paramOrder)
				&& StringUtils.isNotBlank(paramSort)) {
			Direction direction = Direction.fromString(paramOrder);
			Sort sort = new Sort(direction, paramSort);
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}
		Page<Staff> responsePage = facadeService.getStaffService().pageQuery(
				pageRequest,specification);
//		Page<Staff> responsePage = facadeService.getStaffService().pageQuery(
//				pageRequest);
		map.put("total", responsePage.getTotalElements());
		map.put("rows", responsePage.getContent());
		getValueStack().push(map);
		return "pageQuery";
	}
	
	@Action(value = "staffAction_batchDel", results = { @Result(name = "batchDel", type = "json") })
	public String batchDel() throws Exception {
		String idsparam = getParameter("ids");
		if (StringUtils.isNotBlank(idsparam)) {
			try {
				String[] ids = idsparam.split(",");
				facadeService.getStaffService().bathDel(ids);
				push(true);
			} catch (Exception e) {
				e.printStackTrace();
				push(false);
			}
		}else{
			push(false);
		}
		return "batchDel";
	}

}
