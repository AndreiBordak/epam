package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.constant.Message;
import by.epam.admoffice.constant.Parameter;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.logic.UserLogic;
import by.epam.admoffice.logic.exception.LogicalException;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe unknown controllers action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class UnregisterCommand implements ActionCommand {

	/**
	 * The object implements execute method to process unregister action
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return page
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		if (session == null) {
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.LOGIN_PAGE_PATH);
		}
		User user = (User) session.getAttribute(Attributes.LOGIN);
		String password = request.getParameter(Parameter.PARAM_NAME_PASSWORD);
		password = String.valueOf(password.hashCode());
		if (!password.equals(user.getPassword())) {
			request.setAttribute(Attributes.PASSWORD_ERROR_KEY,
					Message.PASSWORD_ERROR_MESSAGE);
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.UNREGISTER_PAGE_PATH);
		}
		try {
			UserLogic.deleteUser(user);
			session.invalidate();
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.LOGIN_PAGE_PATH);
		} catch (LogicalException e) {
			throw new CommandException();
		}
	}

}
