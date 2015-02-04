package by.epam.admoffice.logic;

import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.dao.factory.DAOFactory;
import by.epam.admoffice.dao.factory.MySQLDAOFactory;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.logic.exception.LogicalException;

public final class UserLogic {

	public static void deleteUser(User user) throws LogicalException{
		AbstractDAO dao = null;
		try{
			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(User.class);
			dao.deleteUser(user);
		}catch(DAOException e){
			throw new LogicalException();
		}
	}
}
