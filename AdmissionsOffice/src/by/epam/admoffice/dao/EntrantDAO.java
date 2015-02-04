package by.epam.admoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.epam.admoffice.connectionpool.exception.ConnectionPoolException;
import by.epam.admoffice.constant.SQLField;
import by.epam.admoffice.constant.SQLQuery;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Faculty;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.logic.exception.LogicalException;

public class EntrantDAO extends AbstractDAO<Entrant, Faculty, User, Student> {

	@Override
	public List<Entrant> getAllEntrants() throws DAOException {
		String sql = SQLQuery.GET_ALL_ENTRANTS;
		String facultyName;
		List<Entrant> entrantList = new ArrayList<Entrant>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Entrant entrant = new Entrant();
				facultyName = getFacultyNameById(rs.getInt(SQLField.FAC_ID));
				entrant.setId(rs.getInt(SQLField.ID));
				entrant.setName(rs.getString(SQLField.NAME));
				entrant.setSurname(rs.getString(SQLField.SURNAME));
				entrant.setBirthDate(rs.getString(SQLField.BIRTHDATE));
				entrant.setFacultyName(facultyName);
				entrant.setMath(rs.getInt(SQLField.MATH));
				entrant.setPhysics(rs.getInt(SQLField.PHYSICS));
				entrant.setRussian(rs.getInt(SQLField.RUSSIAN));
				entrant.setCertificate(rs.getDouble(SQLField.CERTIFICATE));
				entrant.setLearningForm(rs.getString(SQLField.LEARNING_FORM));
				entrant.setLogin(rs.getString(SQLField.LOGIN));
				entrantList.add(entrant);
			}

		} catch (SQLException | LogicalException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

		return entrantList;
	}

	@Override
	public List<Faculty> getAllFaculties() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isUserRegistered(String login) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns faculty name by id
	 *
	 * @param id
	 *            is faculty id
	 * @return faculty name
	 */
	private String getFacultyNameById(int id) throws DAOException {
		String sql = SQLQuery.GET_FACULTY_BY_ID;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getString("name");
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return null;
	}

	/**
	 * Returns faculty id by name
	 *
	 * @param name
	 *            is faculty name
	 * @return faculty id
	 */
	private int getFacultyIdByName(String name) throws DAOException {
		String sql = SQLQuery.GET_FACULTY_ID_BY_NAME;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int id = 1;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
				return id;
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

		return id;
	}

	/**
	 * Returns user id by his login
	 *
	 * @param user
	 *            is user
	 * @return users id by his login
	 */
	private int getUserIdByLogin(User user) throws DAOException {
		int id = 0;
		String login = user.getLogin();
		String sql = SQLQuery.ENTRANT_ID;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
				return id;
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return id;
	}

	/**
	 * Returns faculty id by entrant id
	 *
	 * @param entId
	 *            is entrants id
	 * @return faculty id
	 */
	private int getFacIdByEntId(int entId) throws DAOException {
		String sql = SQLQuery.FACULTY_ID;
		int facId = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, entId);
			rs = statement.executeQuery();
			if (rs.next()) {
				facId = rs.getInt("fac_id");
				return facId;
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return facId;
	}

	@Override
	public boolean checkUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Entrant insert(Entrant entrant, User user) throws DAOException {
		String sql = SQLQuery.INSERT_ENTRANT;
		int facId;
		int entId;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			facId = getFacultyIdByName(entrant.getFacultyName());
			statement = connection.prepareStatement(sql);
			statement.setInt(1, facId);
			statement.setString(2, entrant.getName());
			statement.setString(3, entrant.getSurname());
			statement.setString(4, entrant.getBirthDate());
			statement.setDouble(5, entrant.getCertificate());
			statement.setDouble(6, entrant.getMath());
			statement.setDouble(7, entrant.getPhysics());
			statement.setDouble(8, entrant.getRussian());
			statement.setString(9, entrant.getLearningForm());
			statement.setString(10, user.getLogin());
			statement.executeUpdate();

			entId = getUserIdByLogin(user);
			sql = SQLQuery.INSERT_USER;
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			ps.setInt(4, entId);
			ps.executeUpdate();
			user.setEntrantId(entId);
		} catch (SQLException | DAOException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnecion(connection, statement);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return null;
	}

	@Override
	public User findUserByLogin(String login, String password) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entrant> getAllEntrantsByFaculty(int entId) throws DAOException {
		List<Entrant> entrants = new ArrayList<Entrant>();
		int facId = getFacIdByEntId(entId);
		String sql = SQLQuery.ENTRANT_BY_FACULTY;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, facId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Entrant entrant = new Entrant();
				entrant.setName(rs.getString(SQLField.NAME));
				entrant.setSurname(rs.getString(SQLField.SURNAME));
				entrant.setBirthDate(rs.getString(SQLField.BIRTHDATE));
				entrant.setLearningForm(rs.getString(SQLField.LEARNING_FORM));
				entrants.add(entrant);
			}
		} catch (SQLException | LogicalException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return Collections.unmodifiableList(entrants);
	}

	@Override
	public List<Student> insertStudents(List<Entrant> entrants)
			throws DAOException {
		List<Student> students = new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		for (Entrant entrant : entrants) {
			Student student = new Student();
			student.setId(entrant.getId());
			student.setName(entrant.getName());
			student.setSurname(entrant.getSurname());
			student.setBirthDate(entrant.getBirthDate());
			student.setLearningform(entrant.getLearningForm());
			student.setFacName(entrant.getFacultyName());
			student.setLogin(entrant.getLogin());
			student.setSum(entrant.getSum());
			students.add(student);
		}
		String sql = getInsertStudentQuery();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);

			for (Student student : students) {
				statement.setInt(1, student.getId());
				statement.setString(2, student.getName());
				statement.setString(3, student.getSurname());
				statement.setString(4, student.getBirthDate());
				statement.setString(5, student.getLearningform());
				statement.setString(6, student.getFacName());
				statement.setString(7, student.getLogin());
				statement.executeUpdate();
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				closeConnecion(connection, statement);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

		return students;
	}

	/**
	 * Returns query for insert student in database
	 *
	 * @return query
	 */
	private String getInsertStudentQuery() {
		return SQLQuery.INSERT_STUDENT;
	}

	@Override
	public List<Student> getStudentsBySurname(String surname)
			throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteUser(User user) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAllStudents() throws DAOException {
		throw new UnsupportedOperationException();
	}
}
