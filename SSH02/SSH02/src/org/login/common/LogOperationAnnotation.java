package org.login.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)    
@Documented 
public @interface LogOperationAnnotation {
    /**  
     * 用户操作名称  
     * @return 用户操作名称，默认为空串  
     */    
    String value() default "";    
        
    /**  
     * 用户操作类型，默认类型为0<br/>  
     * else - 其他操作 <br/>  
     * select - 查询 <br/>  
     * add - 新增 <br/>  
     * update - 修改 <br/>  
     * delete - 删除  
     * @return 用户操作类型  
     */    
    OperateType[] types() default OperateType.ANYTHING_ELSE;    
        
    /**  
     * 用户操作名称对应的key,可以通过该key值在属性文件中查找对应的value  
     * @return key  
     */    
    String key() default ""; 
}
