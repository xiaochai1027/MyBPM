package com.i360r.bpm.service.rs.process.employee.global.salary.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.TaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class GlobalSalaryRequest extends TaskRequest {
	
	@ProcessVariable(isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="该月工资审核")
	@NotNull
	private Integer globalSalaryId;
	
	@ProcessVariable(isKeyword=true)
	private String name;
	
	@ProcessVariable(isKeyword=true, isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="该月工资审核")
	private String month;

	public Integer getGlobalSalaryId() {
		return globalSalaryId;
	}

	public void setGlobalSalaryId(Integer globalSalaryId) {
		this.globalSalaryId = globalSalaryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
