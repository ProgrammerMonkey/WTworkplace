package com.wt.bos.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.wt.bos.service.facade.FacadeService;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected T model; // new T();

	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	// 父类中 注入 门面业务层
	@Autowired
	protected FacadeService facadeService;
	
	protected PageRequest pageRequest;
	
	

	protected PageRequest getPageRequest() {
		return new PageRequest(page - 1, rows);
	}

	protected PageRequest getPageRequest(Sort sort) {
		return new PageRequest(page - 1, rows, sort);
	}
	
	protected Page<T> pageData;
	
	
	public void setPageData(Page<T> pageData) {
		this.pageData = pageData;
	}
	
	public Map<String, Object> getObj(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageData.getTotalElements());
		map.put("rows", pageData.getContent());
		getValueStack().push(map);
		return map;
	}

	// 目的 获取子类参数化类型
	// 父类构造方法中 使用 参数化泛型+反射 获取子类具体泛型对应类型 newInstance 创建实例
	public BaseAction() {
		// 对model进行实例化， 通过子类 类声明的泛型
		Type superclass = this.getClass().getGenericSuperclass();
		// 转化为参数化类型
		ParameterizedType parameterizedType = (ParameterizedType) superclass;
		// 获取一个泛型参数
		Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
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
	protected int page = 1;// 页码
	protected int rows = 10;// 每页显示记录数

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	

}
