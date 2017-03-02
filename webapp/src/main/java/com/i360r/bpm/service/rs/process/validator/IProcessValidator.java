package com.i360r.bpm.service.rs.process.validator;

import com.i360r.framework.extension.context.model.EmployeeCO;

public interface IProcessValidator<T extends INeedValidate> {
	
	public boolean validate(EmployeeCO employee, String processDefinitionKey, String processInstanceId, T request);
	
	public ProcessValidatorType getType();
	
}
