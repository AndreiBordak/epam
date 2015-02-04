package by.epam.admoffice.dao.exception;

public class DuplicateEntityException extends DAOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateEntityException() {
	}

	public DuplicateEntityException(String message) {
		super(message);
	}

	public DuplicateEntityException(String message, Exception e) {
		super(message, e);
	}

	public DuplicateEntityException(Throwable cause) {
		super(cause);
	}

}
