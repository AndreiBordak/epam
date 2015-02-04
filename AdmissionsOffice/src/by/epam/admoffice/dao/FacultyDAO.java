package by.epam.admoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.admoffice.connectionpool.ConnectionPool;
import by.epam.admoffice.connectionpool.exception.ConnectionPoolException;
import by.epam.admoffice.constant.SQLField;
import by.epam.admoffice.constant.SQLQuery;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Faculty;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.entity.User;

public class FacultyDAO extends AbstractDAO<Entrant, Faculty, User, Student> {


	@Override
	public List<Entrant> getAllEntrants() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Faculty> getAllFaculties() throws DAOException {
		String sql = SQLQuery.ALL_FACULTIES;
		List<Faculty> faculties = new ArrayList<Faculty>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				Faculty faculty = new Faculty();
				faculty.setId(rs.getInt(SQLField.ID));
				faculty.setName(rs.getString(SQLField.NAME));
				faculty.setGovSubsidizedCount(rs.getInt(SQLField.GOV));
				faculty.setFeePayingCount(rs.getInt(SQLField.FEE));
				faculty.setdLearningCount(rs.getInt(SQLField.DLEARN));
				faculties.add(faculty);
			}
			
		}catch(SQLException | ConnectionPoolException e){
			throw new DAOException();
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException();
			}
		}
		return faculties;
	}



	@Override
	public boolean isUserRegistered(String login)
			throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean checkUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Entrant insert(Entrant entrant, User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public User findUserByLogin(String login, String password) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entrant> getAllEntrantsByFaculty(int entId) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Student> insertStudents(List<Entrant> entrants) throws DAOException {
		throw new UnsupportedOperationException();
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
