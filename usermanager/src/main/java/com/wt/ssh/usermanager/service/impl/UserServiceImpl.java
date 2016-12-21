package com.wt.ssh.usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.ssh.usermanager.dao.UserDao;
import com.wt.ssh.usermanager.domain.User;
import com.wt.ssh.usermanager.service.UserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
@Autowired
private UserDao userDao;

	public void save(User user) {
		userDao.save(user);
	}

	public User findUserById(Integer id) {
		User user = userDao.findUserById(id);
		return user;
	}

}
