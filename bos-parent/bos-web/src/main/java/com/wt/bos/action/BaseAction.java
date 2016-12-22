package com.wt.bos.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.wt.bos.service.facade.FacadeService;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	@Autowired
	protected T model;

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	// 父类中 注入 门面业务层
	@Autowired
	protected FacadeService serviceFacade;

	// 目的 获取子类参数化类型
	// 父类构造方法中 使用 参数化泛型+反射 获取子类具体泛型对应类型 newInstance 创建实例
	public BaseAction() {
		// 对model进行实例化， 通过子类 类声明的泛型
		Type superclass = this.getClass().getGenericSuperclass();
		// 转化为参数化类型
		ParameterizedType parameterizedType = (ParameterizedType) superclass;
		// 获取一个泛型参数
		Class<T> modelClass = (Class<T>) parameterizedType
				.getActualTypeArguments()[0];
		try {
			model = modelClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	// 父类封装通用方法 比如值栈操作 分页操作 session 操作 request操作
	// 1: 值栈操作 获取 值栈
	public ValueStack getValueStack() {
		return ActionContext.getContext().getValueStack();
	}

	// 压入栈顶
	public void push(Object obj) {
		getValueStack().push(obj);
	}

	// 压入栈顶map结构<>
	public void set(String key, Object obj) {
		getValueStack().set(key, obj);
	}

	// 2: session 操作
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public void setSessionAttribute(String key, Object obj) {
		getSession().setAttribute(key, obj);
	}

	public void removeSessionAttribute(String key) {
		getSession().removeAttribute(key);
	}

	public Object getSessionAttribute(String key) {
		return getSession().getAttribute(key);
	}

	// 3: request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();

	}

	public String getParameter(String key) {
		return getRequest().getParameter(key);
	}

	// 分页操作 接受页面 和 每页显示记录
	protected int pageNum;// 页码
	protected int pageSize;// 每页显示记录数

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
