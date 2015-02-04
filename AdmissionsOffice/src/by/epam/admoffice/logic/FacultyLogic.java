package by.epam.admoffice.logic;

import java.util.List;
import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.dao.factory.DAOFactory;
import by.epam.admoffice.dao.factory.MySQLDAOFactory;
import by.epam.admoffice.entity.Faculty;
import by.epam.admoffice.logic.exception.LogicalException;

public final class FacultyLogic {

	public static List<Faculty> getFaculties() throws LogicalException {
		List<Faculty> faculties = null;
		AbstractDAO dao = null;
		try {

			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Faculty.class);
			faculties = dao.getAllFaculties();

		} catch (DAOException e) {
			throw new LogicalException();
		}
		return faculties;
	}

}
