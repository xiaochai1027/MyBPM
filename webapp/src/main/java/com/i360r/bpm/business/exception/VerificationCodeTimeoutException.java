package com.i360r.bpm.business.exception;

public class VerificationCodeTimeoutException extends RuntimeException {

	private static final long serialVersionUID = -498442938337763721L;

    public VerificationCodeTimeoutException() {
        super();
    }

    public VerificationCodeTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationCodeTimeoutException(String message) {
        super(message);
    }

    public VerificationCodeTimeoutException(Throwable cause) {
        super(cause);
    }
	
}
