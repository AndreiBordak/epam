package by.epam.admoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.admoffice.connectionpool.exception.ConnectionPoolException;
import by.epam.admoffice.constant.SQLField;
import by.epam.admoffice.constant.SQLQuery;
import by.epam.admoffice.dao.exception.DAOException;
import by.epam.admoffice.entity.Entrant;
import by.epam.admoffice.entity.Faculty;
import by.epam.admoffice.entity.Student;
import by.epam.admoffice.entity.User;

public class StudentDAO extends AbstractDAO<Entrant, Faculty, User, Student> {

	@Override
	public List<Student> insertStudents(List<Entrant> entrants)
			throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Student> getStudentsBySurname(String surname)
			throws DAOException {
		String sql = SQLQuery.GET_STUDENTS_BY_SURNAME;
		List<Student> students = new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, surname);
			rs = statement.executeQuery();
			while(rs.next()){
				Student student = new Student();
				student.setId(rs.getInt(SQLField.ID));
				student.setName(rs.getString(SQLField.NAME));
				student.setSurname(rs.getString(SQLField.SURNAME));
				student.setBirthDate(rs.getString(SQLField.BIRTHDATE));
				student.setLearningform(rs.getString(SQLField.LEARNING_FORM));
				student.setFacName(rs.getString(SQLField.FACULTY_NAME));
				students.add(student);
			}
		}catch(SQLException | ConnectionPoolException e){
			throw new DAOException();
		} finally {
			try {
				closeConnection(connection, statement, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException();
			}
		}
		return students;
	}

	@Override
	public Entrant insert(Entrant entrant, User user) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entrant> getAllEntrants() throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entrant> getAllEntrantsByFaculty(int entId) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Faculty> getAllFaculties() throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public User findUserByLogin(String login, String password)
			throws DAOException {
		throw new UnsupportedOperationException();
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
	public void deleteUser(User user) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAllStudents() throws DAOException {
		String sql = SQLQuery.DELETE_STUDENTS;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}catch(ConnectionPoolException | SQLException e){
			throw new DAOException();
		}finally{
			try {
				closeConnecion(connection, statement);
			} catch (ConnectionPoolException e) {
				throw new DAOException();
			}
		}
		
	}

}
