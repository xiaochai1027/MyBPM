package com.i360r.bpm.business.exception;

public class DimissionExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DimissionExistException() {
        super();
    }

    public DimissionExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DimissionExistException(String message) {
        super(message);
    }

    public DimissionExistException(Throwable cause) {
        super(cause);
    }

}
