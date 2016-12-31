package com.wt.mavencrm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.wt.mavencrm.domain.Customer;

public interface CustomerService {
	@GET
	@Path("/customer")
	@Produces({ "application/xml", "application/json" })
	public List<Customer> getNoAssociations();

	@GET
	// 客户端 查询请求
	@Path("/customer/{decidezoneId}")
	// http://xxxx/user/1
	@Consumes({ "application/xml", "application/json" })
	// 客户端 只能发送 xml 数据类型
	@Produces({ "application/xml", "application/json" })
	public List<Customer> getInUserAssociations(@PathParam("decidezoneId") String decidezone_id);

	@PUT
	@Path("/customer/{cids}/{decidezoneId}")
	// /customer/1,2,3/dq001
	@Consumes({ "application/xml", "application/json" })
	public void assignedCustomerToDecidedZone(@PathParam("cids") String customerids, @PathParam("decidezoneId") String decidedZone_id);
	
	@GET
	// 客户端 查询请求
	@Path("/customer/tel/{tel}")
	// http://xxxx/user/1
	@Consumes({ "application/xml", "application/json" })
	// 客户端 只能发送 xml 数据类型
	@Produces({ "application/xml", "application/json" })
	public Customer findCustomerByTelephone(@PathParam("tel")String telephone);
	
	
	@POST
	//添加请求
	@Path("/customer/save")
	// http://xxxx/user/1
	@Consumes({ "application/xml", "application/json" })
	// 客户端 只能发送 xml 数据类型
	@Produces({ "application/xml", "application/json" })
	public Customer save(Customer customer);
	
	@GET
	// 客户端 查询请求  根据地址查询客户信息
	@Path("/customer/addr/{addr}")
	// http://xxxx/user/1
	@Consumes({ "application/xml", "application/json" })
	// 客户端 只能发送 xml 数据类型
	@Produces({ "application/xml", "application/json" })
	public Customer findCustomerByAddr(@PathParam("addr") String addr);
     //  根据客户id 更新地址
	@PUT
	@Path("/customer/update/{addr}/{cid}")
	@Consumes({ "application/xml", "application/json" })
	public void updateAddreByCustomerId(@PathParam("addr") String addr, @PathParam("cid") String cid);

}

