package com.i360r.bpm.business.exception;

public class InvalidDimissionDateException extends RuntimeException {

    private static final long serialVersionUID = -498442938337763721L;

    public InvalidDimissionDateException() {
        super();
    }

    public InvalidDimissionDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDimissionDateException(String message) {
        super(message);
    }

    public InvalidDimissionDateException(Throwable cause) {
        super(cause);
    }

}
