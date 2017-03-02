package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class ProcessExistedException extends ParamedException {

	private static final long serialVersionUID = -498442938337763721L;

	public ProcessExistedException(String variableName) {
		 super();
	     setParams(new Object[]{variableName});
	}
	
	public ProcessExistedException(Throwable cause, String variableName) {
		super(cause);
       setParams(new Object[]{variableName});
	}
	
}
