package com.wt.bos.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wt.bos.domain.user.User;
import com.wt.bos.service.UserService;
import com.wt.bos.service.facade.FacadeService;
@Controller
@Namespace("/user")
@Scope("prototype")
@ParentPackage("mavenbos")
public class UserAction extends BaseAction<User> {
	@Action(value="userAction_validCheckcode",results={@Result(name="validCheckCode",type="json")})
	public String validCheckCode() throws Exception {
		String page_code = getParameter("checkcode");
		String real_code = (String) getSession().getAttribute("key");
		boolean flag = false;
		if(StringUtils.isNotBlank(page_code)){
			if(real_code.equalsIgnoreCase(real_code)){
				flag = true;
			}
		}
		push(flag);
		return "validCheckCode";
	}
	
	@Action(value="userAction_login",results={
			@Result(name="login_ok",location="/index.jsp",type="redirect"),
			@Result(name="login_error",location="/login.jsp")
	})
	public String login() throws Exception {
		String page_code = getParameter("checkcode");
		String real_code = (String) getSession().getAttribute("key");
		if(StringUtils.isNotBlank(page_code)){
			if(real_code.equalsIgnoreCase(real_code)){
				removeSessionAttribute("key");
				User exitsUser = facadeService.getUserService().login(model.getUsername(), model.getPassword());
				System.out.println(exitsUser);
				if(exitsUser!=null){
					getSession().setAttribute("exitsUser", exitsUser);
					return "login_ok";
				}else{
					this.addActionError(this.getText("login.usernameOrPassword.error"));
					return "login_error";
				}
			}
		}
		this.addActionError(this.getText("login.error"));
		return "login_error";
	}
	@Action(value="userAction_edit",results={@Result(name="edit",type="json")})
	public String edit() throws Exception {
		String password = model.getPassword();
		User user = (User) getSessionAttribute("exitsUser");
		try {
			facadeService.getUserService().editPassword(model.getPassword(),user.getId());
			push(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			push(false);
		}
		return "edit";
	}
}
