package by.epam.admoffice.entity.exception;

import by.epam.admoffice.logic.exception.LogicalException;

public class TooShortPasswordException extends LogicalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooShortPasswordException() {
	}

	public TooShortPasswordException(String message) {
		super(message);
	}

	public TooShortPasswordException(String message, Exception e) {
		super(message, e);
	}

	public TooShortPasswordException(Throwable cause) {
		super(cause);
	}
}
