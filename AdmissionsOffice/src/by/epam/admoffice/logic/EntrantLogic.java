package by.epam.admoffice.logic;

import java.util.ArrayList;
import java.util.List;
import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.dao.factory.DAOFactory;
import by.epam.admoffice.dao.factory.MySQLDAOFactory;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.logic.exception.LogicalException;

public final class EntrantLogic {

	public static boolean insert(Entrant entrant, User user)
			throws LogicalException {
		AbstractDAO dao = null;

		try {
			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Entrant.class);
			dao.insert(entrant, user);

		} catch (DAOException e) {
			throw new LogicalException();
		}
		return true;
	}

	public static List<Entrant> findAllEntrants(int entrantId)
			throws LogicalException {
		AbstractDAO dao = null;

		List<Entrant> entrants = new ArrayList<Entrant>();
		try {
			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Entrant.class);
			entrants = dao.getAllEntrantsByFaculty(entrantId);

		} catch (DAOException e) {
			throw new LogicalException();
		}
		return entrants;
	}

	public static List<Entrant> getAllEntrants() throws LogicalException {
		AbstractDAO dao = null;
		List<Entrant> entrants = new ArrayList<Entrant>();
		try {

			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Entrant.class);
			entrants = dao.getAllEntrants();

		} catch (DAOException e) {
			throw new LogicalException();
		}

		return entrants;
	}

	public static List<Student> matriculation(List<Entrant> entrants)
			throws LogicalException {
		AbstractDAO dao = null;

		List<Student> students = null;
		try {

			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Entrant.class);
			students = dao.insertStudents(entrants);

		} catch (DAOException e) {
			throw new LogicalException();
		}
		return students;
	}
}
