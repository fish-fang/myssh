package org.login.common;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.login.common.util.PropertiesUtil;

public class JspUrlListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PropertiesUtil.URL_PRO = new Properties();
		try{
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			FileInputStream fis = new FileInputStream(path + "jspurl.properties");
			PropertiesUtil.URL_PRO.load(fis);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
