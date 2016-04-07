package com.jjortega.cuapi.requestcore;

public class NotValidHttpRestMethodExpection extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1297741672789131733L;

	public NotValidHttpRestMethodExpection() {
		super();
	}

	public NotValidHttpRestMethodExpection(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotValidHttpRestMethodExpection(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidHttpRestMethodExpection(String message) {
		super(message);
	}

	public NotValidHttpRestMethodExpection(Throwable cause) {
		super(cause);
	}

}
