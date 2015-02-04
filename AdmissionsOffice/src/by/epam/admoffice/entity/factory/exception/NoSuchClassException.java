package by.epam.admoffice.entity.factory.exception;

import by.epam.admoffice.exception.ProjectException;

public class NoSuchClassException extends ProjectException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchClassException() {
	}

	public NoSuchClassException(String message) {
		super(message);
	}

	public NoSuchClassException(String message, Exception e) {
		super(message, e);
	}

	public NoSuchClassException(Throwable cause) {
		super(cause);
	}

}
