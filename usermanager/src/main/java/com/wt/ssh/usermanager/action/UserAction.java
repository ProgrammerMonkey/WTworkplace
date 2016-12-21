package com.wt.ssh.usermanager.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wt.ssh.usermanager.domain.User;
import com.wt.ssh.usermanager.service.UserService;
@Controller("userAction")
@Scope("prototype")
@Namespace("/")
@ParentPackage("usermanager")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
private User user = new User();
@Autowired
private UserService userService;

	public User getModel() {
		return user;
	}
		@Action(value="userAction_save",results={@Result(location="/index.jsp",name="save",type="redirect")})
		public String save() throws Exception {
			userService.save(user);
			return "save";
		}
		public String findUserById() throws Exception {
			User user2 = userService.findUserById(user.getId());
			ActionContext.getContext().getValueStack().push(user2);
			return "findUserById";
		}
	
}
