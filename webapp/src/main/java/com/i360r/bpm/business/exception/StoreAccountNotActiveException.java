package com.i360r.bpm.business.exception;

public class StoreAccountNotActiveException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public StoreAccountNotActiveException() {
        super();
    }

    public StoreAccountNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreAccountNotActiveException(String message) {
        super(message);
    }

    public StoreAccountNotActiveException(Throwable cause) {
        super(cause);
    }

}
