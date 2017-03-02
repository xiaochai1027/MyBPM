package com.i360r.bpm.business.exception;

public class DuplicatedIdentityCardNumberException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4266507730555432248L;

	public DuplicatedIdentityCardNumberException() {
		super();
	}

	public DuplicatedIdentityCardNumberException(String message) {
		super(message);
	}

	public DuplicatedIdentityCardNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedIdentityCardNumberException(Throwable cause) {
		super(cause);
	}

}
