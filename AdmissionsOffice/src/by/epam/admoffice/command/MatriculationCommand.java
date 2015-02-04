package by.epam.admoffice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.constant.Message;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.logic.EntrantLogic;
import by.epam.admoffice.logic.exception.LogicalException;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe unknown controllers action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class MatriculationCommand implements ActionCommand {

	/**
	 * The object implements execute method to process matriculate action
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
		@SuppressWarnings("unchecked")
		List<Entrant> entrants = (List<Entrant>) session
				.getAttribute(Attributes.ENTRANTS);
		List<Student> students = null;
		try {
			students = EntrantLogic.matriculation(entrants);
		} catch (LogicalException e) {
			throw new CommandException();
		}
		if (students == null) {
			session.setAttribute(Attributes.ALERT_DANGER_KEY, Message.ALRT_DANGER);
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} else {
			session.setAttribute(Attributes.STUDENTS, students);
			session.setAttribute(
					Attributes.PAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.ADMIN_STUDENTS_PAGE_PATH));
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_STUDENTS_PAGE_PATH);
		}

	}

}
