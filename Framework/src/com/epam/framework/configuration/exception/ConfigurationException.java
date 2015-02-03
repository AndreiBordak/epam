package com.epam.framework.configuration.exception;

import com.epam.framework.exception.FrameworkException;

public class ConfigurationException extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
	}

	public ConfigurationException(String arg0) {
		super(arg0);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
