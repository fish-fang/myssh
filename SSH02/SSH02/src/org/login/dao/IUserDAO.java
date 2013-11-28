package org.login.dao;

import java.util.List;

import org.login.vo.Users;

public interface IUserDAO {

	List<Users> getAllUser();
	
	Users getUserById(Integer id);
	
	Users isLogin(Users user);
	
	boolean insertUser(Users user);
	
	boolean updateUser(Users user);
	
	boolean deleteUser(Integer id);
}
