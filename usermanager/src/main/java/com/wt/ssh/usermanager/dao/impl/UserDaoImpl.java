package com.wt.ssh.usermanager.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wt.ssh.usermanager.dao.UserDao;
import com.wt.ssh.usermanager.domain.User;
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Autowired
	public void setXxx(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	public User findUserById(Integer id) {
		User user = getHibernateTemplate().get(User.class, id);
		return user;
	}

}
