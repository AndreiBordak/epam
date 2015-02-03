package com.epam.framework.entity.exception;

import com.epam.framework.exception.FrameworkException;

public class TooShortLoginException extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooShortLoginException() {
	}

	public TooShortLoginException(String arg0) {
		super(arg0);
	}

	public TooShortLoginException(Throwable cause) {
		super(cause);
	}

	public TooShortLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooShortLoginException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
