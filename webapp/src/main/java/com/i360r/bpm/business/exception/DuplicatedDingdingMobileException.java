package com.i360r.bpm.business.exception;

public class DuplicatedDingdingMobileException extends RuntimeException {

	private static final long serialVersionUID = 719428274592783334L;

	public DuplicatedDingdingMobileException() {
		super();
	}

	public DuplicatedDingdingMobileException(String message) {
		super(message);
	}

	public DuplicatedDingdingMobileException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedDingdingMobileException(Throwable cause) {
		super(cause);
	}

}
