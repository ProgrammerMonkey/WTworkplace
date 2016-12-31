package com.wt.bos.service;

import java.util.List;

import com.wt.bos.domain.user.User;

public interface UserService {
	public void save(User user);

	public void deleteUser(User user);

	public User findUserById(Integer id);

	public List<User> findAll();

	public void updateUser(User user);

	// 登录
	public User login(String name, String password);

	public void editPassword(String password, Integer id);
}
