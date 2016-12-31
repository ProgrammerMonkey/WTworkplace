package com.wt.mavencrm.service.impl;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.wt.mavencrm.domain.Customer;


public class CustomerServiceImplTest {
public static void main(String[] args) {
			// String url = "http://192.168.15.222:9999/mavencrm/ws/customerService/customer";
//			 String url = "http://localhost:9999/mavencrm/ws/customerService/customer/40288f9c594a6b5e01594a6c24110000";
			String url = "http://localhost:9999/mavencrm/ws/customerService/customer/1/8a7e859e5948830501594883ab710000";
			// crm 录入客户信息业务 数据库表录入临时测试数据
			// 1: 服务 获取未关联的定区客户信息
//			 Collection<? extends Customer> customers = WebClient.create(url).accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
			// Collection<? extends Customer> customers = WebClient.create(url).accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
//			 System.out.println(customers);
			WebClient.create(url).put(null);
}
}
