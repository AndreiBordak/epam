package by.epam.admoffice.connectionpool.exception;

import by.epam.admoffice.exception.ProjectException;

public class ConnectionPoolException extends ProjectException {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

}
