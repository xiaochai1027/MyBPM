package com.i360r.bpm.business.exception;

public class InvalidDeliveryStaffException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDeliveryStaffException() {
		super();
	}

	public InvalidDeliveryStaffException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDeliveryStaffException(String message) {
		super(message);
	}

	public InvalidDeliveryStaffException(Throwable cause) {
		super(cause);
	}
}
