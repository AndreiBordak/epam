package by.epam.admoffice.entity;

import java.io.Serializable;

import by.epam.admoffice.constant.Regex;
import by.epam.admoffice.entity.exception.InvalidLoginException;
import by.epam.admoffice.entity.exception.TooShortPasswordException;

public class User extends Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * const describe min password length
	 */
	private static final byte MIN_PASSWORD_LENGTH = 4;

	private String login;
	private String password;
	private int entrantId;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws InvalidLoginException {
		if (login.matches(Regex.LOGIN)) {
			this.login = login;
		} else {
			throw new InvalidLoginException();
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws TooShortPasswordException {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new TooShortPasswordException();
		} else {
			password = String.valueOf(password.hashCode());
			this.password = password;

		}
	}

	public void setConvertedPassword(String password) {
		this.password = password;
	}

	public int getEntrantId() {
		return entrantId;
	}

	public void setEntrantId(int entrantId) {
		this.entrantId = entrantId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + entrantId;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (entrantId != other.entrantId) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [login=").append(login).append(", password=")
				.append(password).append(", entrantId=").append(entrantId)
				.append(", role=").append(role).append(", getId()=")
				.append(getId()).append("]");
		return builder.toString();
	}

}
