package org.login.common;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.login.common.util.PropertiesUtil;

public class JSPFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request; 
		HttpSession session=req.getSession();
		Enumeration<Object>  jspurls = PropertiesUtil.URL_PRO.elements();
		String url = req.getRequestURI();
		if(url.contains(".action")){
			chain.doFilter(request, response);
			return;
		}
		while (jspurls.hasMoreElements()) {
			String jspurl = (String) jspurls.nextElement();
			if(url.contains(jspurl)){
				System.out.println("URL : " + url);
				chain.doFilter(request, response);
				return;
			}
		}
		request.getRequestDispatcher("login.jsp").forward(request, response); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
