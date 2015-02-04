package by.epam.admoffice.exception;

public class ProjectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectException() {
	}

	public ProjectException(String message) {
		super(message);
	}

	public ProjectException(String message, Exception e) {
		super(message, e);
	}

	public ProjectException(Throwable cause) {
		super(cause);
	}

}
