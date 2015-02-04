package by.epam.admoffice.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import by.epam.admoffice.connectionpool.ConnectionPool;
import by.epam.admoffice.connectionpool.exception.ConnectionPoolException;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger logger = Logger
			.getLogger(ContextListener.class);

	/**
	 * Default constructor.
	 */
	public ContextListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			logger.error("Connection poll are not disposed", e);
		}
	}

}
