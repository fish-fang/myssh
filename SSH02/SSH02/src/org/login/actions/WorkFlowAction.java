package org.login.actions;

import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.login.common.AccessAnotation;
import org.login.common.AuthorityLevel;
import org.login.common.BaseSessionAware;
import org.login.common.LogOperationAnnotation;
import org.login.common.OperateType;
import org.login.common.util.XmlUtil;
import org.login.service.IUserService;
import org.login.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;

@Service("workflow")
public class WorkFlowAction  extends ActionSupport implements ServletRequestAware,SessionAware,BaseSessionAware {

	private static final long serialVersionUID = 8944940063609727902L;
	
	private HttpServletRequest request = null;
	Map<String, Object> session = null;
	private static final String UPDATE = "update";
	private String xmlstr;
	private Integer satus;
	
	public String getXmlstr() {
		return xmlstr;
	}


	public void setXmlstr(String xmlstr) {
		this.xmlstr = xmlstr;
	}

	public Integer getSatus() {
		return satus;
	}


	public void setSatus(Integer satus) {
		this.satus = satus;
	}

	@Autowired(required = false)
	@Qualifier("userService")
	private IUserService userService = null;
	
//	public IUserService getUserService() {
//		return userService;
//	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	public String workflow() throws Exception {
		xmlstr = "member";
		satus = 2;
		System.out.println("#############" + xmlstr);
		return SUCCESS;
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
