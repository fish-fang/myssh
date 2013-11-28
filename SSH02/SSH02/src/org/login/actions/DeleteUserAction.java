package org.login.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionSupport;

@Service("deleteUser")
public class DeleteUserAction   extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -7777178019831981194L;
	private HttpServletRequest request = null;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Autowired(required = false)
	@Qualifier("userService")
	private IUserService userService = null;
	
	@JSON(serialize=false)
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private Integer id;
	private String username;
	private String password;
	private String result;
	
//	private String jasonResult;
//	@JSON(name="JSONRESULT")
//	public String getJasonResult() {
//		return jasonResult;
//	}
//
//	public void setJasonResult(String jasonResult) {
//		this.jasonResult = jasonResult;
//	}
	
//	@JSON(serialize=false)
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

//	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String delete() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		if(getUserService().deleteUser(id)){
			map.put("OK", "SUCCESS");
			JSONObject jo = JSONObject.fromObject(map);
			this.result = jo.toString();
			return SUCCESS;
		} 
		
		map.put("error", "Error");
		JSONObject jo = JSONObject.fromObject(map);
		this.result = jo.toString();
		return ERROR;
	}
}
