package org.login.common;

import java.lang.reflect.Method;
import java.util.Map;

import org.login.common.util.StringUtil;
import org.springframework.aop.MethodBeforeAdvice;
public class AccessAdvisor  implements MethodBeforeAdvice{

	public void before(Method method, Object[] objs, Object target)
			throws Throwable {
		AccessAnotation anno = method.getAnnotation(AccessAnotation.class);
		if (anno == null) return;
		
		String value = anno.value();
		AuthorityLevel[] levels = anno.levels();
		Map<String, Object> session = ((BaseSessionAware)target).getSession();
		String authority = null;
		if(session != null){	    	
			authority = (String)session.get("authority");
			if(StringUtil.isBlank(authority)){
				session.clear();
				session.put("noaccess", value);
				throw new NoAccessException();
			}
			
			for (AuthorityLevel authorityLevel : levels) {
				if (!authorityLevel.equals(getAuthorityLevel(authority))) {
					continue;
				} else {
					return;
				}
			}
			session.put("noaccess", value);
			throw new NoAccessException();
	    }
		
	}
	
	private AuthorityLevel getAuthorityLevel(String authority) {
		if(authority.equals("1")){
			return AuthorityLevel.LEVEL1;
		}else if (authority.equals("2")) {
			return AuthorityLevel.LEVEL2;
		}else if (authority.equals("3")) {
			return AuthorityLevel.LEVEL3;
		}else {
			return AuthorityLevel.LEVEL4;
		}
	}
}
