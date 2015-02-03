package com.epam.framework.entity.exception;

import com.epam.framework.exception.FrameworkException;

public class TooShortPasswordEception extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooShortPasswordEception() {
	}

	public TooShortPasswordEception(String arg0) {
		super(arg0);
	}

	public TooShortPasswordEception(Throwable cause) {
		super(cause);
	}

	public TooShortPasswordEception(String message, Throwable cause) {
		super(message, cause);
	}

	public TooShortPasswordEception(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
