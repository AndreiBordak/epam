package by.epam.admoffice.entity.exception;

import by.epam.admoffice.logic.exception.LogicalException;

public class InvalidLoginException extends LogicalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLoginException() {
	}

	public InvalidLoginException(String message) {
		super(message);
	}

	public InvalidLoginException(String message, Exception e) {
		super(message, e);
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
	}
}
