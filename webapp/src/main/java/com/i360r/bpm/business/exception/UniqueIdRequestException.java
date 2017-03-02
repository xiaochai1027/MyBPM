package com.i360r.bpm.business.exception;

public class UniqueIdRequestException extends RuntimeException {

	private static final long serialVersionUID = -498442938337763721L;

    public UniqueIdRequestException() {
        super();
    }

    public UniqueIdRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueIdRequestException(String message) {
        super(message);
    }

    public UniqueIdRequestException(Throwable cause) {
        super(cause);
    }
	
}
