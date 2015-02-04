package by.epam.admoffice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.admoffice.command.ActionCommand;
import by.epam.admoffice.command.exception.CommandException;
import by.epam.admoffice.command.factory.ActionFactory;
import by.epam.admoffice.resource.ConfigurationManager;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String page;
		String errorPagePath = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ERROR_PAGE_PATH);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(errorPagePath);
		try {

			ActionFactory actFactory = ActionFactory.getInstance();
			ActionCommand actionCommand = actFactory.defineCommand(req);
			page = actionCommand.execute(req);
			if (page != null) {
				dispatcher = getServletContext().getRequestDispatcher(page);
				dispatcher.forward(req, resp);
			} else {
				dispatcher.forward(req, resp);
			}
		} catch (CommandException e) {
			dispatcher.forward(req, resp);
		}

	}
}
