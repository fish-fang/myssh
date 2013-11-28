package org.login.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.login.service.IUserService;
import org.login.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionSupport;

@Service("addUserSearch")
public class AddUserSeacherAction   extends ActionSupport implements ServletRequestAware{

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

	private Integer userid;
	private String result;
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String addUserSearch() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		Users user = getUserService().getUserById(userid);
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("<td align=\"center\">");
		sb.append(user.getId());
		sb.append("</td>");
		sb.append("<td align=\"center\">");
		sb.append(user.getUserName());
		sb.append("</td>");
		sb.append("<td align=\"center\">");
		sb.append(user.getPassword());
		sb.append("</td>");
		sb.append("<td align=\"center\">");
		sb.append(user.getAuthority());
		sb.append("</td>");
		sb.append("</tr>");
		map.put("LINE", sb.toString());
		System.out.println("@@@@@@@@@@@@@@@@@@@" + sb.toString());
		JSONObject jo = JSONObject.fromObject(map);
		this.result = jo.toString();
		return SUCCESS;
	}
}
