package by.epam.admoffice.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.admoffice.command.exception.CommandException;

/**
 * ActionCommand is used by controller to execute any action such as login,
 * logout, etc.
 *
 * @author Andrei Bordak
 * @version 1.10
 */
public interface ActionCommand {
	/**
	 * The object implements execute method to process any action
	 *
	 * @param request
	 *            provide request information for HTTP servlets. For example, it
	 *            has methods to access HTTP headers and cookies.
	 */
	String execute(HttpServletRequest request) throws CommandException;
}
