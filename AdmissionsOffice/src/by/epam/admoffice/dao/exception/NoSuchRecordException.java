package by.epam.admoffice.dao.exception;

public class NoSuchRecordException extends DAOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchRecordException(){}
	
	public NoSuchRecordException(String message){
		super(message);
	}
	
	public NoSuchRecordException(String message, Exception e){
		super(message, e);
	}
	
	public NoSuchRecordException(Throwable cause){
		super(cause);
	}
}

