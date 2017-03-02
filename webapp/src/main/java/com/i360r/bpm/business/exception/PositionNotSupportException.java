package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class PositionNotSupportException extends ParamedException {

	private static final long serialVersionUID = 1L;

	public PositionNotSupportException() {
	}

	public PositionNotSupportException(String message, Object...params) {
		super(message);
		setParams(params);
	}

	public PositionNotSupportException(Throwable cause) {
		super(cause);
	}

	public PositionNotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

}
