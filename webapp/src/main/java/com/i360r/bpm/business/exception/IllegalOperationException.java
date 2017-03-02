package com.i360r.bpm.business.exception;

public class IllegalOperationException extends RuntimeException {

	private static final long serialVersionUID = -498442938337763721L;

    public IllegalOperationException() {
        super();
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(Throwable cause) {
        super(cause);
    }
	
}
