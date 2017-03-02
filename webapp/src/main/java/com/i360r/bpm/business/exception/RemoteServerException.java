package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class RemoteServerException extends ParamedException {

	private static final long serialVersionUID = -498442938337763721L;

	public RemoteServerException(String errorMsg) {
        super(errorMsg);
        setParams(new Object[]{errorMsg});
    }

    public RemoteServerException(Throwable cause, String errorMsg) {
        super(errorMsg, cause);
        setParams(new Object[]{errorMsg});
    }
}
