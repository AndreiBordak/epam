package by.epam.admoffice.logic.exception;

import by.epam.admoffice.exception.ProjectException;

public class LogicalException extends ProjectException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicalException() {
	}

	public LogicalException(String message) {
		super(message);
	}

	public LogicalException(String message, Exception e) {
		super(message, e);
	}

	public LogicalException(Throwable cause) {
		super(cause);
	}
}
