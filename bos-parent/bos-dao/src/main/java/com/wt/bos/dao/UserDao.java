package com.wt.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wt.bos.domain.user.User;

public interface UserDao extends JpaRepository<User, Integer> {
//	public User findByUsernameAndPassword(String username,String password);
//	public User login(String username,String password);
	@Query("from User where username =? and password = ?")
	public User login1(String username,String password);
	@Query(nativeQuery=true,value="select * from t_user where username =? and password =?")
	public User login2(String username,String password);
	@Query("from User where username =:name and password =:password")
	public User login3(@Param("name")String username,@Param("password")String password);
	@Modifying
	@Query("update User set password = ?1 where id= ?2")
	public void editPassword(String password, Integer id);
}
