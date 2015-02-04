package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.constant.Message;
import by.epam.admoffice.constant.Parameter;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.entity.exception.InvalidLoginException;
import by.epam.admoffice.entity.exception.TooShortPasswordException;
import by.epam.admoffice.logic.EntrantLogic;
import by.epam.admoffice.logic.LoginLogic;
import by.epam.admoffice.logic.exception.LogicalException;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe persons registration action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class RegisterCommand implements ActionCommand {

	private static final Logger logger = Logger
			.getLogger(RegisterCommand.class);

	/**
	 * The object implements execute method to process user registration action
	 *
	 * @param request
	 *            provide request information for HTTP servlets. For example, it
	 *            has methods to access HTTP headers and cookies.
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		try {
			if (!isPasswordAndConfirmValid(request)) {

				request.setAttribute(Attributes.PASSWORD_ERROR_KEY,
						Message.PASSWORDS_NOT_MATCH);
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.REGISTER_USER_PAGE_PATH);
			}

			Entrant entrant = readEntrant(request);
			User user = readUser(request);
			if (LoginLogic.checkUser(user)) {
				request.setAttribute(Attributes.LOGIN_ERROR_KEY,
						Message.LOGIN_NON_FREE);
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.REGISTER_USER_PAGE_PATH);
			}
			if (EntrantLogic.insert(entrant, user)) {

				request.setAttribute(Attributes.REGISTRATION_SUCCES_KEY,
						Message.SUCCESS_REGISTRATION_MESSAGE);
				request.getSession().setAttribute(
						Attributes.PAGE,
						ConfigurationManager.getInstance().getProperty(
								ConfigurationManager.MAIN_PAGE_PATH));
				request.getSession().setAttribute(Attributes.LOGIN, user);
				request.getSession().setAttribute(Attributes.ENTRANTS, EntrantLogic.findAllEntrants(user.getEntrantId()));
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.MAIN_PAGE_PATH);
			} else {
				request.setAttribute(Attributes.ALERT_DANGER_KEY,
						Message.SOMETHING_WRONG_TRY_AGAIN);
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ERROR_PAGE_PATH);
			}

		} catch (InvalidLoginException e) {
			logger.error(e);
			request.setAttribute(Attributes.ERROR_LOGIN_PASS_MESSAGE,
					Message.INCORRECT_LOGIN_PASS_MESSAGE);
		} catch (TooShortPasswordException e) {
			logger.error(e);
			request.setAttribute(Attributes.ERROR_LOGIN_PASS_MESSAGE,
					Message.INCORRECT_LOGIN_PASS_MESSAGE);
		} catch (LogicalException e) {
			logger.error(e);
			request.setAttribute(Attributes.LOGICAL_ERROR_KEY,
					Message.LOGICAL_ERROR_MESSAGE);
		}

		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.REGISTER_USER_PAGE_PATH);
	}

	private boolean isPasswordAndConfirmValid(HttpServletRequest request) {
		String password = request.getParameter(Parameter.PARAM_NAME_PASSWORD);
		String confirm = request.getParameter(Parameter.PARAM_NAME_CONFIRM);

		if (password.isEmpty() || confirm.isEmpty()) {
			return false;
		}

		if (password.equals(confirm)) {
			return true;
		}

		return false;
	}

	private Entrant readEntrant(HttpServletRequest request)
			throws LogicalException {

		Entrant entrant = new Entrant();
		initEntrant(request, entrant);
		return entrant;
	}

	private User readUser(HttpServletRequest request)
			throws InvalidLoginException, TooShortPasswordException {
		User user = new User();
		initUser(request, user);
		return user;
	}

	private void initEntrant(HttpServletRequest request, Entrant entrant)
			throws LogicalException {
		entrant.setLogin(request.getParameter(Parameter.PARAM_NAME_LOGIN));
		entrant.setName(request.getParameter(Parameter.PARAM_NAME_NAME));
		entrant.setSurname(request.getParameter(Parameter.PARAM_NAME_SURNAME));
		entrant.setBirthDate(request
				.getParameter(Parameter.PARAM_NAME_BIRTHDATE));
		entrant.setCertificate(Double.parseDouble(request
				.getParameter(Parameter.PARAM_NAME_CERTIFICATE)));
		entrant.setMath(Double.parseDouble(request
				.getParameter(Parameter.PARAM_NAME_MATH)));
		entrant.setPhysics(Double.parseDouble(request
				.getParameter(Parameter.PARAM_NAME_PHYSICS)));
		entrant.setRussian(Double.parseDouble(request
				.getParameter(Parameter.PARAM_NAME_RUSSIAN)));
		entrant.setLearningForm(request
				.getParameter(Parameter.PARAM_NAME_LEARNING));
		entrant.setFacultyName(request
				.getParameter(Parameter.PARAM_NAME_FACULTY));

	}

	private void initUser(HttpServletRequest request, User user)
			throws InvalidLoginException, TooShortPasswordException {
		user.setLogin(request.getParameter(Parameter.PARAM_NAME_LOGIN));
		user.setPassword(request.getParameter(Parameter.PARAM_NAME_PASSWORD));
		user.setRole(Parameter.SIMPLE_TYPE);
	}
}
