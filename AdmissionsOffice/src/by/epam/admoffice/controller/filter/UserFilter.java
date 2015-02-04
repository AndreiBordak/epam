package by.epam.admoffice.controller.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.admoffice.constant.Attributes;
import by.epam.admoffice.constant.Message;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.resource.ConfigurationManager;

public abstract class UserFilter extends AbstractFilter {

	protected boolean isForward(HttpServletRequest request,
			HttpServletResponse response, String role) throws ServletException,
			IOException {

		User user = (User) request.getSession().getAttribute(Attributes.LOGIN);
		if (user == null) {
			request.getRequestDispatcher(
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.LOGIN_PAGE_PATH)).forward(
					request, response);
			return true;
		} else {
			if (!user.getRole().equals(role)) {
				request.setAttribute(Attributes.ALERT_DANGER_KEY,
						Message.PAGE_NOT_FOUND);
				request.getRequestDispatcher(ConfigurationManager.getInstance()
						.getProperty(ConfigurationManager.ERROR_PAGE_PATH));
				return true;
			}
		}

		return false;
	}

}
