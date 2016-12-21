package com.wt.ssh.usermanager.dao;

import com.wt.ssh.usermanager.domain.User;

public interface UserDao{
	public void save(User user);
	public User findUserById(Integer id);
}
