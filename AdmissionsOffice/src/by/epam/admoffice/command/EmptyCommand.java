package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe unknown controllers action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class EmptyCommand implements ActionCommand {

	/**
	 * The object implements execute method to process unknown action
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return logins page
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.LOGIN_PAGE_PATH);
		return page;
	}

}
