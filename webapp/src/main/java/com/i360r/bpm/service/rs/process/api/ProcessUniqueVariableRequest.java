package com.i360r.bpm.service.rs.process.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariableEntity;
import com.i360r.bpm.service.rs.process.validator.INeedValidate;
import com.i360r.bpm.service.rs.process.validator.ProcessValidatorType;

public class ProcessUniqueVariableRequest implements INeedValidate {

	private List<ProcessVariableEntity> uniqueVariables;
	
	public List<ProcessVariableEntity> getUniqueVariables() {
		return uniqueVariables;
	}

	public void setUniqueVariables(List<ProcessVariableEntity> uniqueVariables) {
		this.uniqueVariables = uniqueVariables;
	}

	@Override
	public ProcessValidatorType getType() {
		return ProcessValidatorType.UNIQUE_VARIABLE;
	}	
}
