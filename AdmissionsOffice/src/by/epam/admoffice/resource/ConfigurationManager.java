package by.epam.admoffice.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private static final String RESOURCE = "configuration.config";

	public static final String ERROR_PAGE_PATH = "path.page.error";

	/**
	 * Bundle's key to read login page path
	 */
	public static final String LOGIN_PAGE_PATH = "path.page.login";

	public static final String UNREGISTER_PAGE_PATH="path.page.unregister";
	public static final String MAIN_PAGE_PATH = "path.page.main";
	public static final String ADMIN_PAGE_PATH = "path.page.admin";
	public static final String ADMIN_STUDENTS_PAGE_PATH="path.page.admin.students";
	public static final String ADMIN_SEARCH_PAGE_PATH="path.page.admin.search";
	public static final String ADMIN_SEARCH_RESULT_PAGE_PATH="path.page.admin.search.result";
	/**
	 * Bundle's key to read student's registration page path
	 */

	public static final String REGISTER_USER_PAGE_PATH = "path.page.registration";

	/**
	 * Bundle's key to id faculty
	 */
	public static final String ID_FITR = "id.faculty.fitr";
	public static final String ID_MSF = "id.faculty.msf";
	public static final String ID_FMMP = "id.faculty.fmmp";
	public static final String ID_EF = "id.faculty.ef";

	/**
	 * Bundle's key to read fatal logger name
	 */

	public static final String FATAL_LOGGER_NAME = "fatal.logger.name";

	/**
	 * Bundle's key to read error logger name
	 */
	public static final String ERROR_LOGGER_NAME = "error.logger.name";
	/**
	 * Bundle's key to read users activity
	 */
	public static final String ACTIVITY_LOGGER_NAME = "listener.logger.name";
	private static final ResourceBundle resourceBundle = ResourceBundle
			.getBundle(RESOURCE);

	private static ConfigurationManager instance;

	private ConfigurationManager() {
	}

	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
		}

		return instance;
	}

	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
