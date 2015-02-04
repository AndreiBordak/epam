package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.logic.StudentLogic;
import by.epam.admoffice.logic.exception.LogicalException;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Implements {@link ActionCommand} to describe unknown controllers action
 *
 * @author Andrei Bordak
 * @version 1.10
 * @see ActionCommand
 */
public class DeleteStudentsCommand implements ActionCommand {
	/**
	 * The object implements execute method to process delete students action
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

		try {
			StudentLogic.deleteAllStudents();
			session.removeAttribute(Attributes.STUDENTS);
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		} catch (LogicalException e) {
			throw new CommandException(e);
		}
	}

}
