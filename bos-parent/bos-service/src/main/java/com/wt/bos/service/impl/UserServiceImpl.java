package com.wt.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.UserDao;
import com.wt.bos.domain.user.User;
import com.wt.bos.service.UserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public User findUserById(Integer id) {
		return userDao.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void updateUser(User user) {
		userDao.save(user);
	}

	@Override
	public User login(String name, String password) {
		return userDao.login1(name, password);
//		return userDao.findByUsernameAndPassword(name, password);
	}

	@Override
	public void editPassword(String password, Integer id) {
		userDao.editPassword(password,id);
	}

}
