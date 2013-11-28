package org.login.dao.Impl;

import java.util.List;

import org.login.dao.IUserDAO;
import org.login.dao.MyHibernateSupport;
import org.login.vo.Users;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;


@SuppressWarnings("unchecked")
@Service("userDAO")
public class UserDAO extends MyHibernateSupport implements IUserDAO {

	public List<Users> getAllUser() {
		
		HibernateTemplate  ht = this.getHibernateTemplate();	
		return ht.find("from Users");
	}
	
	public Users getUserById(Integer id) {
		
		HibernateTemplate  ht = this.getHibernateTemplate();	
		return ht.get(Users.class, id);
	}
	
	public boolean insertUser(Users user) {
		HibernateTemplate  ht = this.getHibernateTemplate();
		try {
			ht.save(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Users isLogin(Users user) {
		HibernateTemplate  ht = this.getHibernateTemplate();
		String hqs = "from Users where userName = '" 
			+ user.getUserName() + "'" + " and password = '" + user.getPassword() + "'";
		List<Users> list = ht.find(hqs);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public boolean updateUser(Users user) {
		HibernateTemplate  ht = this.getHibernateTemplate();
		try {
			ht.update(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteUser(Integer id) {
	
		HibernateTemplate  ht = this.getHibernateTemplate();
		try {
			Object u = ht.load(Users.class, id);
			ht.delete(u);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
}
