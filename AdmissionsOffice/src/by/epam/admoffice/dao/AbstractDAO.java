package by.epam.admoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import by.epam.admoffice.connectionpool.ConnectionPool;
import by.epam.admoffice.connectionpool.exception.ConnectionPoolException;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.entity.Entity;
import by.epam.admoffice.entity.User;

/**
 * Abstract class gives base realization of CRUD operations using JDBC.
 *
 * @param <E>
 *            type of entrant object
 * @param <F>
 *            type of faculty object
 * @param <U>
 *            type of user object
 * @param <s>
 *            type of student object
 */
public abstract class AbstractDAO<E, F, U, S extends Entity> {

	/**
	 * Inserts students in database
	 *
	 * @param entrants
	 *            is list of entrants who are enrolled id university
	 * @return list of the students
	 */
	public abstract List<S> insertStudents(List<E> entrants)
			throws DAOException;

	/**
	 * Returns student from database with the specified surname
	 *
	 * @param surname
	 *            is the surname of the student
	 * @return list of the students with the specified surname
	 */
	public abstract List<S> getStudentsBySurname(String surname)
			throws DAOException;

	/**
	 * Insert entrant and user in appropriate tables
	 *
	 * @param entrant
	 *            is entrant who registered is nte system
	 * @param user
	 *            contains information about user who registered in system
	 * @return entrant who registered in the system
	 */
	public abstract E insert(E entrant, U user) throws DAOException;

	/**
	 * Get all entrants in admissions office
	 * 
	 * @return list of all entrants
	 */
	public abstract List<E> getAllEntrants() throws DAOException;

	/**
	 * Returns all entrants who field documents in certain faculty
	 *
	 * @param entId
	 *            is entrants id
	 * @return entrants list
	 */
	public abstract List<E> getAllEntrantsByFaculty(int entId)
			throws DAOException;

	/**
	 * Returns list of all faculties
	 *
	 * @return faculties list
	 */
	public abstract List<F> getAllFaculties() throws DAOException;

	/**
	 * Returns user by his login and password
	 *
	 * @param login
	 *            is users login
	 * @param password
	 *            is users password
	 * @return user
	 */
	public abstract U findUserByLogin(String login, String password)
			throws DAOException;

	/**
	 * Checks is registered user with specified login
	 *
	 * @param login
	 *            is users login
	 * @return true if users is registered
	 */
	public abstract boolean isUserRegistered(String login) throws DAOException;

	/**
	 * Checks user in the system, in particular his login, password and role
	 *
	 * @param user
	 *            is user
	 * @return true if user are registered
	 */
	public abstract boolean checkUser(User user) throws DAOException;

	/**
	 * Method for deleting user from the system
	 *
	 * @param user
	 *            is user
	 */
	public abstract void deleteUser(User user) throws DAOException;

	/**
	 * Method for deleting all students from database
	 */
	public abstract void deleteAllStudents() throws DAOException;

	/**
	 * Close connection, statement and result set
	 */
	protected void closeConnection(Connection connection,
			java.sql.PreparedStatement statement, ResultSet rs)
			throws ConnectionPoolException {
		ConnectionPool.getInstance().closeConnection(connection, statement, rs);

	}

	/**
	 * Close connection ant statement
	 */
	protected void closeConnecion(Connection connection,
			java.sql.PreparedStatement ps) throws ConnectionPoolException {
		ConnectionPool.getInstance().closeConnection(connection, ps);
	}

	/**
	 * Returns connection fromn connection pool
	 */
	protected Connection getConnection() throws ConnectionPoolException {
		return ConnectionPool.getInstance().takeConnection();
	}

}
