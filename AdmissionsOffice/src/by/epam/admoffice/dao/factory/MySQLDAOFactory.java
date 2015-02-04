package by.epam.admoffice.dao.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import by.epam.admoffice.dao.AbstractDAO;
import by.epam.admoffice.dao.EntrantDAO;
import by.epam.admoffice.dao.FacultyDAO;
import by.epam.admoffice.dao.StudentDAO;
import by.epam.admoffice.dao.UserDAO;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Faculty;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.entity.User;

public class MySQLDAOFactory implements DAOFactory {

	/**
	 * constant used to lock monitor
	 */

	private static final ReentrantLock lock = new ReentrantLock();

	/**
	 * instance of dao factory
	 */

	private static MySQLDAOFactory instance;

	/**
	 * contains Class object of entity and dao crator pair
	 */

	private Map<Class, DaoCreator> creators;

	private MySQLDAOFactory() {
		creators = new HashMap<>();
		creators.put(User.class, new DaoCreator() {

			@Override
			public AbstractDAO create() {
				return new UserDAO();
			}
		});

		creators.put(Faculty.class, new DaoCreator() {

			@Override
			public AbstractDAO create() {
				return new FacultyDAO();
			}
		});

		creators.put(Student.class, new DaoCreator() {

			@Override
			public AbstractDAO create() {
				return new StudentDAO();
			}
		});

		creators.put(Entrant.class, new DaoCreator() {

			@Override
			public AbstractDAO create() {
				return new EntrantDAO();
			}
		});
	}

	public static MySQLDAOFactory getInstance() {
		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new MySQLDAOFactory();
				}
			} finally {
				lock.unlock();
			}
		}

		return instance;
	}

	@Override
	public AbstractDAO getDao(Class dtoClass)
			throws DAOException {
		DaoCreator creator = creators.get(dtoClass);
		if (creator == null) {
			throw new DAOException("Dao object for " + dtoClass + " not found.");
		}
		return creator.create();
	}

}
