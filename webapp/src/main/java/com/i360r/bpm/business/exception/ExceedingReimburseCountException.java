package com.i360r.bpm.business.exception;

public class ExceedingReimburseCountException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceedingReimburseCountException() {
        super();
    }

    public ExceedingReimburseCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceedingReimburseCountException(String message) {
        super(message);
    }

    public ExceedingReimburseCountException(Throwable cause) {
        super(cause);
    }
}
