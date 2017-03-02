package com.i360r.bpm.business.exception;

public class AttendanceRawDateErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AttendanceRawDateErrorException() {
		super();
	}

	public AttendanceRawDateErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public AttendanceRawDateErrorException(String message) {
		super(message);
	}

	public AttendanceRawDateErrorException(Throwable cause) {
		super(cause);
	}

}
