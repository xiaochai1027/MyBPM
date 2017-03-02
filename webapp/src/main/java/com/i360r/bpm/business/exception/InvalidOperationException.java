package com.i360r.bpm.business.exception;

public class InvalidOperationException extends RuntimeException {

	private static final long serialVersionUID = -498442938337763721L;

    public InvalidOperationException() {
        super();
    }

    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(Throwable cause) {
        super(cause);
    }
	
}
