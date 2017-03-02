package com.i360r.bpm.service.rs.process.validator.impl;

import java.util.Date;

import com.i360r.bpm.business.exception.TimeRangeConflictException;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.api.AbstractCreatorUniqueTimeRangeRequest;
import com.i360r.bpm.service.rs.process.validator.IProcessValidator;
import com.i360r.bpm.service.rs.process.validator.ProcessValidatorType;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.service.api.exception.IllegalRequestException;

/**
 * 流程有一个时间段，同类型同一创建者创建的流程时间段不能冲突
 * 
 * @author liyong
 */
public class ProcessCreatorUniqueTimeRangeValidator extends AbstractEngineHandler implements IProcessValidator<AbstractCreatorUniqueTimeRangeRequest> {

	@Override
	public boolean validate(EmployeeCO employee, String processDefinitionKey, 
			String processInstanceId, AbstractCreatorUniqueTimeRangeRequest model) {
		
		Date beginTime = model.getBeginTime();
		Date endTime = model.getEndTime();
		
		if (beginTime == null || endTime == null || beginTime.after(endTime)) {
			throw new IllegalRequestException("begintime endtime null or out of order,employeee or delivery staff code :[" + employee.getCode() + "]");
		}
		//注释.or() 和  .variableValueEquals(ProcessConstants.KEY_PASS, true) 代表请假时间端重复 不再校验 已通过的流程
		//已通过时间段由service项目 校验
		long count = getHistoryService().createHistoricProcessInstanceQuery()
				.processDefinitionKey(processDefinitionKey)
				.variableValueEquals(ProcessConstants.KEY_CREATED_BY_CODE, employee.getCode())
				.variableValueLessThan(ProcessConstants.KEY_BEGIN_TIME, endTime)
				.variableValueGreaterThan(ProcessConstants.KEY_END_TIME, beginTime)
//				.or()
				.unfinished()
//				.variableValueEquals(ProcessConstants.KEY_PASS, true)
				.count();
		if (count > 0) {
			throw new TimeRangeConflictException("employeee or delivery staff code :[" + employee.getCode() + "]");
		}
		
		return true;
	}

	@Override
	public ProcessValidatorType getType() {
		return ProcessValidatorType.CREATOR_UNIQUE_TIME_RANGE;
	}

}
