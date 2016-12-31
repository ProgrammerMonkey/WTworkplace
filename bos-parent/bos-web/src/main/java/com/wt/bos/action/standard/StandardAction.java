package com.wt.bos.action.standard;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wt.bos.action.BaseAction;
import com.wt.bos.domain.bc.Standard;
import com.wt.bos.domain.user.User;
@Controller
@ParentPackage("mavenbos")
@Namespace("/")
@Scope("prototype")
public class StandardAction extends BaseAction<Standard>{
	@Action(value="standardAction_save",results={
			@Result(name="save",location="/WEB-INF/pages/base/standard.jsp")})
	public String save() throws Exception {
		User user = (User) getSessionAttribute("exitsUser");
		String username = user.getUsername();
		model.setOperator(username);
		model.setOperationtime(new Timestamp(System.currentTimeMillis()));
		model.setOperatorcompany("上海百度分公司");
		
		facadeService.StandardService.save(model);
		return "save";
	}
	@Action(value="standardAction_pageQuery",results={
			@Result(name="pageQuery",type="json")})
	public String pageQuery() throws Exception {
		PageRequest pageRequest =new PageRequest(page-1, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Standard> responsePage =  facadeService.StandardService.pageQuery(pageRequest);
		map.put("total", responsePage.getTotalElements());
		map.put("rows", responsePage.getContent());
		getValueStack().push(map);
		return "pageQuery";
	}
	@Action(value="standardAction_batchDel",results={
			@Result(name="batchDel",type="json")})
	public String batchDel() throws Exception {
		String result = getParameter("ids");
		String[] arr = result.split(",");
		try {
			facadeService.StandardService.batchDel(arr);
			push(true);
		} catch (Exception e) {
			push(false);
			e.printStackTrace();
		}
		return "batchDel";
	}

	@Action(value="standardAction_ajaxList",results={
			@Result(name="ajaxList",type="json")})
	public String ajaxList() throws Exception {
		List<Standard> list =facadeService.StandardService.ajaxList();
		push(list);
		return "ajaxList";
	}
}
