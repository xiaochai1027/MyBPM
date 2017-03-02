package com.i360r.bpm.service.rs.process.validator;

/**
 * 流程验证器类型
 * 
 * @author liyong
 */
public enum ProcessValidatorType {

	/** 某些流程变量的值在同一流程中唯一 */
	UNIQUE_VARIABLE,
	/** 流程有一个时间段，同类型同一创建者发起的时间段不能冲突 */
	CREATOR_UNIQUE_TIME_RANGE;
	
}
