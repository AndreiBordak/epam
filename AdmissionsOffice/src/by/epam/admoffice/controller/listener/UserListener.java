package by.epam.admoffice.controller.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import by.epam.admoffice.resource.ConfigurationManager;

@WebListener
public class UserListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		Logger.getLogger(
				ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ACTIVITY_LOGGER_NAME)).info(arg0);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		Logger.getLogger(
				ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ACTIVITY_LOGGER_NAME)).info(arg0);

	}

}
