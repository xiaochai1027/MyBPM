package com.i360r.bpm.business.exception;

public class ChangeGradeExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ChangeGradeExistException() {
        super();
    }

    public ChangeGradeExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeGradeExistException(String message) {
        super(message);
    }

    public ChangeGradeExistException(Throwable cause) {
        super(cause);
    }
}
