package org.login.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)    
@Documented 
public @interface AccessAnotation {

    /**  
     * 用户操作名称  
     * @return 用户操作名称，默认为空串  
     */    
    String value() default "无访问权限！";    
        
    /**  
     * LEVEL1 - 权限类型1 <br/>  
     * LEVEL2 - 权限类型2 <br/>  
     * LEVEL3 - 权限类型3 <br/>  
     * LEVEL4 - 权限类型4 <br/>  
     * @return 权限类型  
     */    
    AuthorityLevel[] levels() default AuthorityLevel.LEVEL1; 
}
