package by.epam.admoffice.dao.factory;

import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.exception.DAOException;

public interface DAOFactory {

	public interface DaoCreator {

		public AbstractDAO create();
	}

	public AbstractDAO getDao(Class dtoClass)
			throws DAOException;
}
