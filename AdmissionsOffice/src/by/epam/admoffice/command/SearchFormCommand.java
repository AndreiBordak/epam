package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe unknown controllers action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class SearchFormCommand implements ActionCommand {

	/**
	 * The object implements execute method to process search form action
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return admins page
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		if (session == null) {
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.LOGIN_PAGE_PATH);
		}
		session.setAttribute(
				Attributes.PAGE,
				ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ADMIN_SEARCH_PAGE_PATH));
		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ADMIN_SEARCH_PAGE_PATH);
	}

}
