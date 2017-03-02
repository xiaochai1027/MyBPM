package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class RequestParameterValidateException extends ParamedException {

	private static final long serialVersionUID = -498442938337763721L;

	public RequestParameterValidateException(String variableName) {
		 super();
	     setParams(new Object[]{variableName});
	}
	
	public RequestParameterValidateException(Throwable cause, String variableName) {
		super(cause);
        setParams(new Object[]{variableName});
	}

}
