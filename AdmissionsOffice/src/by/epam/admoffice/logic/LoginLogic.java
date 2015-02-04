package by.epam.admoffice.logic;

import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.dao.factory.DAOFactory;
import by.epam.admoffice.dao.factory.MySQLDAOFactory;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.logic.exception.LogicalException;

public final class LoginLogic {

	public static boolean checkUser(User user) throws LogicalException {

		AbstractDAO dao = null;

		try {

			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(User.class);

			if (dao.checkUser(user)) {
				return true;
			}

		} catch (DAOException e) {
			throw new LogicalException();
		}
		return false;

	}

	public static User getFullUser(String login, String password)
			throws LogicalException {
		AbstractDAO dao = null;
		try {

			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(User.class);

			return (User) dao.findUserByLogin(login, password);

		} catch (DAOException e) {
			throw new LogicalException();
		}

	}

	public static boolean checkLogin(User user) {
		String login = user.getLogin();
		AbstractDAO dao = null;

		return false;
	}
}
