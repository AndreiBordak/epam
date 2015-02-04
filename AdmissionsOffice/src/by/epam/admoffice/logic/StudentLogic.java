package by.epam.admoffice.logic;

import java.util.List;
import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.dao.factory.DAOFactory;
import by.epam.admoffice.dao.factory.MySQLDAOFactory;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.logic.exception.LogicalException;

public final class StudentLogic {

	public static List<Student> getStudentsBySurname(String surname)
			throws LogicalException {
		List<Student> students = null;
		AbstractDAO dao = null;

		try {

			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Student.class);
			students = dao.getStudentsBySurname(surname);

		} catch (DAOException e) {
			throw new LogicalException();
		}
		return students;
	}

	public static void deleteAllStudents() throws LogicalException {
		AbstractDAO dao = null;

		try {
			DAOFactory daoFactory = MySQLDAOFactory.getInstance();
			dao = daoFactory.getDao(Student.class);
			dao.deleteAllStudents();
			
		} catch (DAOException e) {
			throw new LogicalException();
		}
	}

}
