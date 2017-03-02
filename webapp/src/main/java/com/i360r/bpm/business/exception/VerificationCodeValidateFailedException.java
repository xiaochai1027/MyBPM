package com.i360r.bpm.business.exception;

public class VerificationCodeValidateFailedException extends RuntimeException {

	private static final long serialVersionUID = -498442938337763721L;

    public VerificationCodeValidateFailedException() {
        super();
    }

    public VerificationCodeValidateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationCodeValidateFailedException(String message) {
        super(message);
    }

    public VerificationCodeValidateFailedException(Throwable cause) {
        super(cause);
    }
	
}
