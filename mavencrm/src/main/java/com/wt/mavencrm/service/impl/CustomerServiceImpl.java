package com.wt.mavencrm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.mavencrm.dao.CustomerDao;
import com.wt.mavencrm.domain.Customer;
import com.wt.mavencrm.service.CustomerService;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public List<Customer> getNoAssociations() {
		return customerDao.getNoAssociations();
	}

	public List<Customer> getInUserAssociations(String decidezone_id) {
		return customerDao.getInUserAssociations(decidezone_id);
	}

		// 1,2,3,4
	public void assignedCustomerToDecidedZone(String customerids, String decidedZone_id) {
		// 给客户 重新关联定区 先解除之前用户定区绑定 再重新绑定
		customerDao.cancleDecidedZoneCustomers(decidedZone_id);
		// 先取消所有定区关联客户
		// 重新绑定
		if(StringUtils.isNotBlank(customerids)&&!"''".equals(customerids)){
			String[] ids = customerids.split(",");
			for (String id : ids) {
				customerDao.assignedCustomerToDecidedZone(Integer.parseInt(id), decidedZone_id);
			}
		}

	}

	@Override
	public Customer findCustomerByTelephone(String telephone) {
		
		return customerDao.findCustomerByTelephone(telephone);
		
	}

	@Override
	public Customer save(Customer customer) {
		return customerDao.save(customer);
	}

	@Override
	public Customer findCustomerByAddr(String addr) {
		return customerDao.findCustomerByAddr(addr);
	}

	@Override
	public void updateAddreByCustomerId(String addr, String cid) {
		customerDao.updateAddreByCustomerId(addr,cid);
	}
}
