package com.i360r.bpm.business.exception;

public class TransferExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TransferExistException() {
        super();
    }

    public TransferExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferExistException(String message) {
        super(message);
    }

    public TransferExistException(Throwable cause) {
        super(cause);
    }

}
