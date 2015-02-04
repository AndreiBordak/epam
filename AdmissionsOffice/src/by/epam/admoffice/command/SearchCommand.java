package by.epam.admoffice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.constant.Parameter;
import by.epam.admoffice.entity.Student;
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
public class SearchCommand implements ActionCommand {

	/**
	 * The object implements execute method to process seacrh action
	 *
	 * @param request
	 *            provide request information for HTTP servlets.
	 * @return search page
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		List<Student> students;
		String surname;
		surname = request.getParameter(Parameter.PARAM_NAME_SURNAME);
		try {
			students = StudentLogic.getStudentsBySurname(surname);
		} catch (LogicalException e) {
			throw new CommandException();
		}
		session.setAttribute(Attributes.STUDENT, students);
		session.setAttribute(
				Attributes.PAGE,
				ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ADMIN_SEARCH_RESULT_PAGE_PATH));
		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ADMIN_SEARCH_RESULT_PAGE_PATH);
	}

}
