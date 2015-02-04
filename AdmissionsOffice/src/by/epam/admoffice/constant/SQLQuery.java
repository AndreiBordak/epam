package by.epam.admoffice.constant;
/**
 * Class of DML queries
 *
 * @author Andrei Bordak
 * @version 1.10
 */
public final class SQLQuery {

	private SQLQuery() {
	}

	public static final String GET_ALL_ENTRANTS = "Select * from entrant";
	public static final String GET_FACULTY_BY_ID = "Select name from faculty where id=?";
	public static final String GET_FACULTY_ID_BY_NAME = "Select id from faculty where name=?";
	public static final String INSERT_ENTRANT = "INSERT INTO entrant (fac_id, name, surname, birthdate, certificate, math, physics, russian, learningform, login) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String ENTRANT_ID = "Select id from entrant where login=?";
	public static final String INSERT_USER = "Insert into user (login, password, role, ent_id) values(?, ?, ?, ?)";
	public static final String FACULTY_ID = "Select * from entrant where id=?";
	public static final String ENTRANT_BY_FACULTY = "Select * from entrant where fac_id =?";
	public static final String INSERT_STUDENT = "Insert into student(id, name, surname, birthdate, learningform, fac_name, login) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String ALL_FACULTIES = "Select * from faculty";
	public static final String DELETE_STUDENTS = "Delete from student";
	public static final String GET_STUDENTS_BY_SURNAME = "Select * from student where surname =?";
	public static final String CHECK_USER="Select * from user where login=? and password=?";
	public static final String CHECK_LOGIN="Select * from user where login=?";
	public static final String DELETE_USER ="Delete from user where login=? and password=?";
	public static final String DELETE_ENTRANT="Delete from entrant where login=?";
}
