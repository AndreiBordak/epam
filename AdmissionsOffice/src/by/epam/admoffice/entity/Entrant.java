package by.epam.admoffice.entity;

import java.io.Serializable;

import by.epam.admoffice.constant.Regex;
import by.epam.admoffice.entity.exception.InvalidLoginException;
import by.epam.admoffice.logic.exception.LogicalException;

public class Entrant extends Entity implements Serializable {

	private static final long serialVersionUID = -5731874616824889949L;

	private String facultyName;
	private String name;
	private String surname;
	private String birthDate;
	private double certificate;
	private double math;
	private double physics;
	private double russian;
	private String learningForm;
	private String login;

	public Entrant() {
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) throws LogicalException {
		if ((math <= 100) && (math >= 0)) {
			this.math = math;
		} else {
			throw new LogicalException();
		}
	}

	public double getPhysics() {
		return physics;
	}

	public void setPhysics(double physics) throws LogicalException {
		if ((physics <= 100) && (physics >= 0)) {
			this.physics = physics;
		} else {
			throw new LogicalException();
		}
	}

	public double getRussian() {
		return russian;
	}

	public void setRussian(double russian) throws LogicalException {
		if ((russian <= 100) && (russian >= 0)) {
			this.russian = russian;
		} else {
			throw new LogicalException();
		}
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

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) throws LogicalException {
		if (facultyName.matches(Regex.FACULTY)) {
			this.facultyName = facultyName;
		} else {
			throw new LogicalException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws LogicalException {
		if (name.matches(Regex.NAME)) {
			this.name = name;
		} else {
			throw new LogicalException();
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) throws LogicalException {
		if (name.matches(Regex.NAME)) {
			this.surname = surname;
		} else {
			throw new LogicalException();
		}
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public double getCertificate() {
		return certificate;
	}

	public void setCertificate(double certificate) throws LogicalException {
		if ((certificate < 10) && (certificate > 0)) {
			this.certificate = certificate;
		} else {
			throw new LogicalException();
		}
	}

	public String getLearningForm() {
		return learningForm;
	}

	public void setLearningForm(String learningForm) throws LogicalException {
		if (learningForm.matches(Regex.LEARNING)) {
			this.learningForm = learningForm;
		} else {
			throw new LogicalException();
		}
	}

	public double getSum() {
		return this.certificate * 10 + this.math + this.physics + this.russian;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(certificate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((facultyName == null) ? 0 : facultyName.hashCode());
		result = prime * result
				+ ((learningForm == null) ? 0 : learningForm.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		temp = Double.doubleToLongBits(math);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(physics);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(russian);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		if (!(obj instanceof Entrant)) {
			return false;
		}
		Entrant other = (Entrant) obj;
		if (birthDate == null) {
			if (other.birthDate != null) {
				return false;
			}
		} else if (!birthDate.equals(other.birthDate)) {
			return false;
		}
		if (Double.doubleToLongBits(certificate) != Double
				.doubleToLongBits(other.certificate)) {
			return false;
		}
		if (facultyName == null) {
			if (other.facultyName != null) {
				return false;
			}
		} else if (!facultyName.equals(other.facultyName)) {
			return false;
		}
		if (learningForm == null) {
			if (other.learningForm != null) {
				return false;
			}
		} else if (!learningForm.equals(other.learningForm)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (Double.doubleToLongBits(math) != Double
				.doubleToLongBits(other.math)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (Double.doubleToLongBits(physics) != Double
				.doubleToLongBits(other.physics)) {
			return false;
		}
		if (Double.doubleToLongBits(russian) != Double
				.doubleToLongBits(other.russian)) {
			return false;
		}
		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entrant [facultyName=").append(facultyName)
				.append(", name=").append(name).append(", surname=")
				.append(surname).append(", birthDate=").append(birthDate)
				.append(", certificate=").append(certificate).append(", math=")
				.append(math).append(", physics=").append(physics)
				.append(", russian=").append(russian).append(", learningForm=")
				.append(learningForm).append(", login=").append(login)
				.append(", getId()=").append(getId()).append("]");
		return builder.toString();
	}

}
