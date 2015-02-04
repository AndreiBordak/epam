package by.epam.admoffice.command.exception;

import by.epam.admoffice.exception.ProjectException;

public class CommandException extends ProjectException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandException() {
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(String message, Exception e) {
		super(message, e);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}

}
