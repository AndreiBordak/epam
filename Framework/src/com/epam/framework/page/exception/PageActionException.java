package com.epam.framework.page.exception;

import com.epam.framework.exception.FrameworkException;

public class PageActionException extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageActionException() {
		
	}

	public PageActionException(String arg0) {
		super(arg0);	
	}

	public PageActionException(Throwable cause) {
		super(cause);		
	}

	public PageActionException(String message, Throwable cause) {
		super(message, cause);		
	}

	public PageActionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
