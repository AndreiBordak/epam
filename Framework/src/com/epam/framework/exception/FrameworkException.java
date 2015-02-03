package com.epam.framework.exception;

public class FrameworkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameworkException() {

	}

	public FrameworkException(String arg0) {
		super(arg0);

	}

	public FrameworkException(Throwable cause) {
		super(cause);

	}

	public FrameworkException(String message, Throwable cause) {
		super(message, cause);

	}

	public FrameworkException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
