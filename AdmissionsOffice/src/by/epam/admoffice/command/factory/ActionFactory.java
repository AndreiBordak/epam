package by.epam.admoffice.command.factory;

import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.admoffice.command.ActionCommand;
import by.epam.admoffice.command.client.CommandEnum;
import by.epam.admoffice.constant.Parameter;

public class ActionFactory {

	private static final Logger logger = Logger.getLogger(ActionFactory.class);
	private static ActionFactory instance;
	private static final ReentrantLock lock = new ReentrantLock();

	private ActionFactory() {
	}

	public static ActionFactory getInstance() {
		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ActionFactory();
				}
			} finally {
				lock.unlock();
			}
		}

		return instance;
	}

	public ActionCommand defineCommand(HttpServletRequest req) {
		CommandEnum currentEnum = CommandEnum.valueOf(Parameter.EMPTY_COMMAND
				.toUpperCase());
		ActionCommand current = currentEnum.getCurrentCommand();
		String action = req.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}

		try {
			currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
		return current;
	}
}
