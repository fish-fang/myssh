package org.login.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.login.common.AccessAnotation;
import org.login.common.AuthorityLevel;
import org.login.common.BaseSessionAware;
import org.login.common.LogOperationAnnotation;
import org.login.common.OperateType;
import org.login.service.IUserService;
import org.login.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;

@Service("login")
public class Login  extends ActionSupport implements ServletRequestAware,SessionAware,BaseSessionAware {

	private static final long serialVersionUID = 8944940063609727902L;
	
	private HttpServletRequest request = null;
	Map<String, Object> session = null;
	private static final String UPDATE = "update";
	
	@Autowired(required = false)
	@Qualifier("userService")
	private IUserService userService = null;
	
//	public IUserService getUserService() {
//		return userService;
//	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private String id = null;
	private String name = null;
	private String password = null;
	private String authority = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	@LogOperationAnnotation(value="注解日志",types={OperateType.SELECT},key="日志注解")
	public String execute() throws Exception {
		Users user = new Users();
		user.setUserName(name);
		user.setPassword(password);
		user = userService.isLogin(user);
		if (user != null) {
			session.put("username", user.getUserName());
			session.put("authority", user.getAuthority());
			return SUCCESS;
		}
		return INPUT;
	}
	@LogOperationAnnotation(value="注解日志",types={OperateType.SELECT},key="日志注解")
	@AccessAnotation(levels={AuthorityLevel.LEVEL1,AuthorityLevel.LEVEL2,AuthorityLevel.LEVEL3,AuthorityLevel.LEVEL4})
	public String findAllUsers() throws Exception {
		List<Users> list = userService.getAllUser(); 
		if (list.size() > 0) {
			request.setAttribute("list", list);
			return SUCCESS;
		}
		return ERROR;
	}
	
	@LogOperationAnnotation(value="注解日志",types={OperateType.ADD},key="日志注解")
	@AccessAnotation(levels={AuthorityLevel.LEVEL1})
	public String save() throws Exception {
		Users user = new Users();
		user.setUserName(name);
		user.setPassword(password);
		user.setAuthority(authority);
		if (userService.insertUser(user)) {
			return SUCCESS;
		}
		return ERROR;
	}

	@LogOperationAnnotation(value="注解日志",types={OperateType.UPDATE},key="日志注解")
	@AccessAnotation(levels={AuthorityLevel.LEVEL1,AuthorityLevel.LEVEL2,AuthorityLevel.LEVEL3,AuthorityLevel.LEVEL4})
	public String update() throws Exception {
		Users user = null;
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if ("loading".equals(action)) {
			user = new Users();
			user = userService.getUserById(Integer.valueOf(id));
			if (null != user) {
				request.setAttribute("user", user);
			}
			return UPDATE;
			
		} else {
			user = new Users();
			user.setUserName(name);
			user.setPassword(password);
			user.setAuthority(authority);
			user.setId(Integer.valueOf(id));
			if (userService.updateUser(user)) {
				return SUCCESS;
			}
		}
		
		return ERROR;
	}
	@LogOperationAnnotation(value="注解日志",types={OperateType.DELETE},key="日志注解")
	@AccessAnotation(levels={AuthorityLevel.LEVEL1})
	public String delete() throws Exception {
		String id = request.getParameter("id");
		if (userService.deleteUser(Integer.valueOf(id))) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public Map<String, Object> getSession() {
		return session;
	}
}
