package by.epam.admoffice.entity;

import java.io.Serializable;

public class Student extends Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String surname;
	private String learningform;
	private String birthDate;
	private String facName;
	private String login;
	private double sum;
	
	public Student(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLearningform() {
		return learningform;
	}

	public void setLearningform(String learningform) {
		this.learningform = learningform;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((facName == null) ? 0 : facName.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((learningform == null) ? 0 : learningform.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (facName == null) {
			if (other.facName != null)
				return false;
		} else if (!facName.equals(other.facName))
			return false;
		if (id != other.id)
			return false;
		if (learningform == null) {
			if (other.learningform != null)
				return false;
		} else if (!learningform.equals(other.learningform))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=").append(id).append(", name=")
				.append(name).append(", surname=").append(surname)
				.append(", learningform=").append(learningform)
				.append(", birthDate=").append(birthDate).append(", facName=")
				.append(facName).append(", login=").append(login)
				.append(", getId()=").append(getId()).append("]");
		return builder.toString();
	}
	
	
}
