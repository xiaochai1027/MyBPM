package com.i360r.bpm.business.exception;

public class TransferForbidenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TransferForbidenException() {
		super();
	}

	public TransferForbidenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransferForbidenException(String message) {
		super(message);
	}

	public TransferForbidenException(Throwable cause) {
		super(cause);
	}
}
