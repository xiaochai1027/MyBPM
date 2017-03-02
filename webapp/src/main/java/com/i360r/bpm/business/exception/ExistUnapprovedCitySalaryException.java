package com.i360r.bpm.business.exception;

public class ExistUnapprovedCitySalaryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4282605818017252760L;

	public ExistUnapprovedCitySalaryException() {
		super();
	}
	
	public ExistUnapprovedCitySalaryException(String message) {
		super(message);
	}
	
	public ExistUnapprovedCitySalaryException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistUnapprovedCitySalaryException(Throwable cause) {
		super(cause);
	}
}
