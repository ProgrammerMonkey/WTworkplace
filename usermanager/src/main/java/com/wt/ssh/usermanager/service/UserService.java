package com.wt.ssh.usermanager.service;

import com.wt.ssh.usermanager.domain.User;

public interface UserService {
	public void save(User user);
	public User findUserById(Integer id);
}
