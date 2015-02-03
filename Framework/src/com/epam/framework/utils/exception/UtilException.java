package com.epam.framework.utils.exception;

import com.epam.framework.exception.FrameworkException;

public class UtilException extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilException() {
	}

	public UtilException(String arg0) {
		super(arg0);
	}

	public UtilException(Throwable cause) {
		super(cause);
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
