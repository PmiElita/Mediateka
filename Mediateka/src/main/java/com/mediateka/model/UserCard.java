package com.mediateka.model;

import com.mediateka.annotation.Column;

public class UserCard {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "email")
	private String email;

	@Column(name = "path")
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path.replace('\\', '/');
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserCard [firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", email=" + email + "]";
	}

}
