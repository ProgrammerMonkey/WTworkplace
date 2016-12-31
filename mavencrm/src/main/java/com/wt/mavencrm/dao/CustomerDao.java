package com.wt.mavencrm.dao;

import java.util.List;

import com.wt.mavencrm.domain.Customer;

public interface CustomerDao {
	// 获取定区未关联的客户信息
		public List<Customer> getNoAssociations();

		// 获取指定定区绑定客户信息
		public List<Customer> getInUserAssociations(String decidezone_id);

		// 定区绑定客户
		public void assignedCustomerToDecidedZone(Integer customer_id, String decidedZone_id);

		// 取消定区关联所有客户
		public void cancleDecidedZoneCustomers(String decidedZone_id);
		
		//根据手机号查询用户并返回
		public Customer findCustomerByTelephone(String telephone);

		public Customer save(Customer customer);

		public Customer findCustomerByAddr(String addr);

		public void updateAddreByCustomerId(String addr, String cid);

}
