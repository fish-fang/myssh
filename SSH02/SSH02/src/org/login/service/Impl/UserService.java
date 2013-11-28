package org.login.service.Impl;

import java.util.List;

import org.login.dao.IUserDAO;
import org.login.service.IUserService;
import org.login.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {

	@Autowired(required=false)
	@Qualifier("userDAO")
	private IUserDAO userDAO = null;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean deleteUser(Integer id) {
		
		return userDAO.deleteUser(id);
	}

	@Override
	public List<Users> getAllUser() {
		
		return userDAO.getAllUser();
	}

	@Override
	public boolean insertUser(Users user) {
		
		return userDAO.insertUser(user);
	}

	@Override
	public Users isLogin(Users user) {
		
		return userDAO.isLogin(user);
	}

	@Override
	public boolean updateUser(Users user) {
		
		return userDAO.updateUser(user);
	}

	@Override
	public Users getUserById(Integer id) {
		
		return userDAO.getUserById(id);
	}

}
