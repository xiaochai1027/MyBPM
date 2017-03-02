package com.i360r.bpm.business.exception;

public class GetCdsResponseException extends RuntimeException {

	private static final long serialVersionUID = 719428274592783334L;

	public GetCdsResponseException() {
		super();
	}

	public GetCdsResponseException(String message) {
		super(message);
	}

	public GetCdsResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public GetCdsResponseException(Throwable cause) {
		super(cause);
	}

}
