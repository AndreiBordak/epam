package by.epam.admoffice.constant;

/**
 * Class of regex constants
 *
 * @author Andrei Bordak
 * @version 1.10
 */

public final class Regex {

	private Regex() {
	}

	/**
	 * Pattern for user's login
	 */
	public static String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

	/**
	 * Pattern fo user's password
	 */
	public static String PASSWORD = "(?=^.{4,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

	/**
	 * Pattern for user's first name, second name
	 */
	public static String NAME = "^[à-ÿÀ-ß¸¨a-zA-Z]+$";

	/**
	 * Pattern for learning form
	 */

	public static String LEARNING = "gov|fee|dlearn";

	/**
	 * Pattern for faculty
	 */

	public static String FACULTY = "fitr|msf|fmmp|ef";

}
