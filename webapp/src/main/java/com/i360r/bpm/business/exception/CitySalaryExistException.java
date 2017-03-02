package com.i360r.bpm.business.exception;

public class CitySalaryExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CitySalaryExistException() {
        super();
    }

    public CitySalaryExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CitySalaryExistException(String message) {
        super(message);
    }

    public CitySalaryExistException(Throwable cause) {
        super(cause);
    }

}
