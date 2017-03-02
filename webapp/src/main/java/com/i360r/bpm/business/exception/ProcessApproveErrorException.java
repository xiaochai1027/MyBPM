package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class ProcessApproveErrorException extends ParamedException {

	private static final long serialVersionUID = 1L;

	public ProcessApproveErrorException() {
	}

	public ProcessApproveErrorException(String message, Object... params) {
		super(message);
		setParams(params);
	}

	public ProcessApproveErrorException(Throwable cause) {
		super(cause);
	}

	public ProcessApproveErrorException(String message, Throwable cause) {
		super(message, cause);
	}

}
