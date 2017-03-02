package com.i360r.bpm.business.exception;

public class TimeRangeConflictException extends RuntimeException {

	private static final long serialVersionUID = -498442938337763721L;

    public TimeRangeConflictException() {
        super();
    }

    public TimeRangeConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeRangeConflictException(String message) {
        super(message);
    }

    public TimeRangeConflictException(Throwable cause) {
        super(cause);
    }
	
}
