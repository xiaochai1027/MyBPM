package com.i360r.bpm.service.rs.process.api;

import java.util.Date;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.validator.INeedValidate;
import com.i360r.bpm.service.rs.process.validator.ProcessValidatorType;

public class AbstractCreatorUniqueTimeRangeRequest implements INeedValidate {

	@ProcessVariable
	private Date beginTime;
	
	@ProcessVariable
	private Date endTime;
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public ProcessValidatorType getType() {
		return ProcessValidatorType.CREATOR_UNIQUE_TIME_RANGE;
	}
}
