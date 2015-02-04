package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.constant.Message;
import by.epam.admoffice.constant.Parameter;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.entity.exception.InvalidLoginException;
import by.epam.admoffice.entity.exception.TooShortPasswordException;
import by.epam.admoffice.logic.EntrantLogic;
import by.epam.admoffice.logic.LoginLogic;
import by.epam.admoffice.logic.exception.LogicalException;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe unknown controllers action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class LoginCommand implements ActionCommand {

	private static final Logger logger = Logger.getLogger(LoginCommand.class);

	/**
	 * The object implements execute method to process login action
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return page
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		try {

			if (request.getSession().getAttribute(Attributes.LOGIN) != null) {
				request.getSession().invalidate();
			}
			User user = readUser(request);
			HttpSession session = request.getSession();
			if (!LoginLogic.checkUser(user)) {
				request.setAttribute(Attributes.ERROR_LOGIN_PASS_MESSAGE,
						Message.ERROR_LOGIN_PASS_MESSAGE);
				request.setAttribute(
						Attributes.PAGE,
						ConfigurationManager.getInstance().getProperty(
								ConfigurationManager.LOGIN_PAGE_PATH));

			} else {
				rememberUser(user, request);
				switch (user.getRole()) {
				case "admin":
					request.setAttribute(
							Attributes.PAGE,
							ConfigurationManager.getInstance().getProperty(
									ConfigurationManager.ADMIN_PAGE_PATH));
					session.setAttribute(
							Attributes.PAGE,
							ConfigurationManager.getInstance().getProperty(
									ConfigurationManager.ADMIN_PAGE_PATH));
					return ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.ADMIN_PAGE_PATH);

				case "entrant":
					request.setAttribute(
							Attributes.PAGE,
							ConfigurationManager.getInstance().getProperty(
									ConfigurationManager.MAIN_PAGE_PATH));
					session.setAttribute(
							Attributes.PAGE,
							ConfigurationManager.getInstance().getProperty(
									ConfigurationManager.MAIN_PAGE_PATH));
					return ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.MAIN_PAGE_PATH);

				default:
					return ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.REGISTER_USER_PAGE_PATH);
				}
			}

			return (String) request.getAttribute("page");

		} catch (InvalidLoginException | TooShortPasswordException e) {
			request.setAttribute(Attributes.ERROR_LOGIN_PASS_MESSAGE,
					Message.INCORRECT_LOGIN_PASS_MESSAGE);
			logger.error(e);

		} catch (LogicalException e) {
			throw new CommandException();
		}

		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.LOGIN_PAGE_PATH);

	}

	/**
	 * Reads login from request parameters
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return login from request
	 * @see by.epam.admoffice.entity.User
	 */

	private User readUser(HttpServletRequest request)
			throws InvalidLoginException, TooShortPasswordException {

		User user = new User();

		user.setLogin(request.getParameter(Parameter.PARAM_NAME_LOGIN)
				.toLowerCase());
		user.setPassword(request.getParameter(Parameter.PARAM_NAME_PASSWORD));

		return user;
	}

	private void rememberUser(User user, HttpServletRequest req)
			throws LogicalException {
		HttpSession session = req.getSession();
		User usr = LoginLogic.getFullUser(user.getLogin(), user.getPassword());
		session.setAttribute(Attributes.LOGIN, usr);
		switch (usr.getRole()) {
		case "admin":
			session.setAttribute(Attributes.ENTRANTS,
					EntrantLogic.getAllEntrants());
			break;
		case "entrant":
			session.setAttribute(Attributes.ENTRANTS,
					EntrantLogic.findAllEntrants(usr.getEntrantId()));
			break;
		default:
			break;
		}
	}

}