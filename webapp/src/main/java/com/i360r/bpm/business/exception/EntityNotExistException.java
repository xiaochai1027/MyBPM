package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class EntityNotExistException extends ParamedException {

	private static final long serialVersionUID = -498442938337763721L;

    public EntityNotExistException(String msg, String name) {
        super(msg);
        setParams(new Object[]{name});
    }

    public EntityNotExistException(Throwable cause, String name) {
        super(cause);
        setParams(new Object[]{name});
    }
	
}
