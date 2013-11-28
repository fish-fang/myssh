package org.login.common;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.Assert;

public class LogAdvisor implements MethodBeforeAdvice{
	
    private Logger logger;
    
    private String loggerName; 
    
	public void before(Method method, Object[] objs, Object target)throws Throwable {
		
		LogOperationAnnotation anno = method.getAnnotation(LogOperationAnnotation.class);
		//
	    if (anno == null) return;
	    String username = "anonymous";
	    String defMsg = anno.value();
	    Map<String, Object> session = ((BaseSessionAware)target).getSession();
	    if(session != null){	    	
	    	username = (String)session.get("username")== null ? "anonymous"    
                    : (String)session.get("username");
	    }
	    String methodName = target.getClass().getName() + "." + method.getName(); 
	    
	    String logline = buildLogLine(username,methodName,anno.types(),defMsg);
	    logger.info(logline);
	}
    
    protected String buildLogLine(String username, String methodName,OperateType[] operateTypes,    
            String description) {    
        StringBuilder sb = new StringBuilder(); 
        String typeStr = "";
        if(operateTypes != null){
        	for(OperateType ot:operateTypes){
        		typeStr = typeStr + ot.toString() + "/";
        	}
        }
        sb.append(username).append(" - ").append(methodName).append("-")
        .append(typeStr).append(" - ").append(description);    
        return sb.toString();    
    } 
    
    @PostConstruct    
    public void init() {    
        Assert.notNull(loggerName);    
        logger = Logger.getLogger(loggerName);    
    }  
    
    public void setLoggerName(String loggerName) {    
        this.loggerName = loggerName;    
    }
}
