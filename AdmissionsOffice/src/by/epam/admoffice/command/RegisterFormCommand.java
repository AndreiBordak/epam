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
public class RegisterFormCommand implements ActionCommand {

	/**
	 * The object implements execute method to process register form action
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return page
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Attributes.PAGE) == null) {
			session.setAttribute(
					Attributes.PAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.REGISTER_USER_PAGE_PATH));
		}
		session.setAttribute(
				Attributes.PAGE,
				ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.REGISTER_USER_PAGE_PATH));
		String page;
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.REGISTER_USER_PAGE_PATH);

		return page;
	}

}
