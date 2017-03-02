package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class UniqueVariableRequestException extends ParamedException {

	private static final long serialVersionUID = -498442938337763721L;

    public UniqueVariableRequestException(String variableName) {
        super();
        setParams(new Object[]{variableName});
    }

    public UniqueVariableRequestException(Throwable cause, String variableName) {
        super(cause);
        setParams(new Object[]{variableName});
    }
	
}
