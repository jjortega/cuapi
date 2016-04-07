package com.jjortega.cuapi.requestcore;

public class NotAllowedRequestBodyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6059743897050143753L;

	public NotAllowedRequestBodyException() {
		super();
	}

	public NotAllowedRequestBodyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotAllowedRequestBodyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedRequestBodyException(String message) {
		super(message);
	}

	public NotAllowedRequestBodyException(Throwable cause) {
		super(cause);
	}

}
