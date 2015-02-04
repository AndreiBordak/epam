package by.epam.admoffice.dao.exception;

import by.epam.admoffice.exception.ProjectException;

public class DAOException extends ProjectException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}
