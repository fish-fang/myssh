package org.login.service;

import java.util.List;


import org.login.vo.Users;

public interface IUserService {
	
	List<Users> getAllUser();
	
	Users getUserById(Integer id);
	
	Users isLogin(Users user);
	
	boolean insertUser(Users user);
	
	boolean updateUser(Users user);
	
	boolean deleteUser(Integer id);
}
