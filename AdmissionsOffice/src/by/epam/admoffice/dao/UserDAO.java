package by.epam.admoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.admoffice.connectionpool.exception.ConnectionPoolException;
import by.epam.admoffice.constant.SQLQuery;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.dao.exception.NoSuchRecordException;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Faculty;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.entity.User;
import by.epam.admoffice.logic.exception.LogicalException;

public class UserDAO extends AbstractDAO<Entrant, Faculty, User, Student> {

	private static final Logger logger = Logger.getLogger(UserDAO.class);

	@Override
	public Entrant insert(Entrant entrant, User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entrant> getAllEntrants() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Faculty> getAllFaculties() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isUserRegistered(String login) throws DAOException {

		String sql = SQLQuery.CHECK_LOGIN;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
				closeConnection(connection, ps, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException();
			}
		}
		return false;
	}

	@Override
	public boolean checkUser(User user) throws DAOException {
		String sql = getCheckUserQuery();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String role;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			rs = statement.executeQuery();
			if (rs.next()) {
				role = rs.getString("role");
				user.setRole(role);
				return true;
			}

		} catch (SQLException | NullPointerException | ConnectionPoolException e) {
			logger.fatal(e);
			throw new DAOException();
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException();
			}
		}
		return false;
	}

	private String getCheckUserQuery() {
		return SQLQuery.CHECK_USER;
	}

	@Override
	public User findUserByLogin(String login, String password)
			throws DAOException {
		String sql = getCheckUserQuery();
		User user = new User();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			rs = statement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setConvertedPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setEntrantId(rs.getInt("ent_id"));

			}
		} catch (SQLException e) {
			throw new DAOException("", e);
		} catch (LogicalException e) {
			logger.error(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException();
			}
		}
		if (user.getLogin() == null) {
			throw new NoSuchRecordException("User with login " + login
					+ " not found");
		}
		return user;
	}

	@Override
	public List<Entrant> getAllEntrantsByFaculty(int entId) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Student> insertStudents(List<Entrant> entrants)
			throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Student> getStudentsBySurname(String surname)
			throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteUser(User user) throws DAOException {
		String deleteUserQuery = SQLQuery.DELETE_USER;
		String deleteEntrantQuery = SQLQuery.DELETE_ENTRANT;
		Connection connection = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		try {
			connection = getConnection();
			statement1 = connection.prepareStatement(deleteUserQuery);
			statement1.setString(1, user.getLogin());
			statement1.setString(2, user.getPassword());
			statement1.executeUpdate();
			statement2 = connection.prepareStatement(deleteEntrantQuery);
			statement2.setString(1, user.getLogin());
			statement2.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			logger.fatal(e);
			throw new DAOException();
		} finally {
			try {
				closeConnecion(connection, statement1);
				statement2.close();
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException();
			}
		}

	}

	@Override
	public void deleteAllStudents() throws DAOException {
		throw new UnsupportedOperationException();
	}

}
