package com.i360r.bpm.business.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 流程变量，被注解的请求变量自动生成为流程变量
 * 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessVariable {
	
	/**
	 * 流程变量名，默认为字段名
	 */
	String key() default "";
	
	/**
	 * 变量显示名，用于系统提示等
	 */
	String showName() default "";
	
	/**
	 * 是否是必须字段
	 */
	boolean required() default false;
	
	/**
	 * 变量值是否生成为关键字
	 */
	boolean isKeyword() default false;
	
	/**
	 * 关键字的显示顺序，默认放在最后面
	 * 
	 * 按1,2,3,4,……的顺序排列，即1放在最前面
	 * 
	 */
	int keywordOrder() default Integer.MAX_VALUE;
	
	/**
	 * 是否为唯一变量
	 */
	boolean isUnique() default false;
	
	/**
	 * 唯一变量范围
	 */
	ProcessUniqueScope uniqueScope() default ProcessUniqueScope.UNFINISHED_PASS;
}
