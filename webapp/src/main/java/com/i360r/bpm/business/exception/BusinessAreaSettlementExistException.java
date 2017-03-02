package com.i360r.bpm.business.exception;

public class BusinessAreaSettlementExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BusinessAreaSettlementExistException() {
        super();
    }

    public BusinessAreaSettlementExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessAreaSettlementExistException(String message) {
        super(message);
    }

    public BusinessAreaSettlementExistException(Throwable cause) {
        super(cause);
    }
}
