package com.wt.mavencrm.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wt.mavencrm.dao.CustomerDao;
import com.wt.mavencrm.domain.Customer;
@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	
	@Autowired
	public void setXXXX(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@Override
	public List<Customer> getNoAssociations() {
		List<Customer> list = getHibernateTemplate().find("from Customer where decidedzoneId is null");
		return list.isEmpty() ? null : list;
	}

	public List<Customer> getInUserAssociations(String decidezone_id) {
		List<Customer> list = getHibernateTemplate().find("from Customer where decidedzoneId = ?", decidezone_id);
		return list.isEmpty() ? null : list;
	}

	public void assignedCustomerToDecidedZone(Integer customer_id, String decidedZone_id) {
		// 修改 update xxx set decidezoneid = ? where id = ? hql Query session
		getSession().createQuery("update Customer set decidedzoneId = ? where id = ?").setParameter(0, decidedZone_id).setParameter(1, customer_id).executeUpdate();
	}

	// 取消 定区 关联所有用户
	public void cancleDecidedZoneCustomers(String decidedZone_id) {
		getSession().createQuery("update Customer set decidedzoneId = null where decidedzoneId = ?").setParameter(0, decidedZone_id).executeUpdate();

	}


	@Override
	public Customer findCustomerByTelephone(String telephone) {
		List<Customer> list = getHibernateTemplate().find("from Customer where telephone = ?",telephone);
		return list.isEmpty()?null:list.get(0);
	}


	@Override
	public Customer save(Customer customer) {
		Integer id = (Integer) getHibernateTemplate().save(customer);
		Customer customer2 = new Customer();
		customer2.setId(id);
		return customer2;
		//为何不能直接使用customer
	}


	@Override
	public Customer findCustomerByAddr(String addr) {
		List<Customer> list = getHibernateTemplate().find("from Customer where address = ?",addr);
		return list.isEmpty()?null:list.get(0);
	}


	@Override
	public void updateAddreByCustomerId(String addr, String cid) {
		getSession().createQuery("update Customer set address = ? where id = ?").setParameter(0, addr).setParameter(1, Integer.parseInt(cid)).executeUpdate();
	}


}
