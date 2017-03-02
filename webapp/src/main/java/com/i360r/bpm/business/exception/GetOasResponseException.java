package com.i360r.bpm.business.exception;

public class GetOasResponseException extends RuntimeException {

	private static final long serialVersionUID = 719428274592783334L;

	public GetOasResponseException() {
		super();
	}

	public GetOasResponseException(String message) {
		super(message);
	}

	public GetOasResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public GetOasResponseException(Throwable cause) {
		super(cause);
	}

}
