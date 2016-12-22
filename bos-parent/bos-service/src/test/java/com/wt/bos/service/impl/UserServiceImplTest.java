package com.wt.bos.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wt.bos.domain.user.User;
import com.wt.bos.service.facade.FacadeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-service.xml",
								 "classpath:applicationContext-domain.xml",
								 "classpath:applicationContext-dao.xml"})
public class UserServiceImplTest {
@Autowired
private FacadeService facadeService;
	@Test
	public void testSave() {
		User user = new User();
		user.setPassword("123");
		user.setUsername("zhangsan");
		facadeService.getUserService().save(user);
	}

	@Test
	public void testDeleteUser() {
	}

	@Test
	public void testFindUserById() {
	}

	@Test
	public void testFindAll() {
	}

	@Test
	public void testUpdateUser() {
	}

	@Test
	public void testLogin() {
		User user = facadeService.getUserService().login("zhangsan", "123");
		System.out.println(user);
	}

}
