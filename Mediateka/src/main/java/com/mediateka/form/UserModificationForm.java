package com.mediateka.form;

import com.mediateka.annotation.DateField;
import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class UserModificationForm {

	@Validation(regexp=RegExps.ONLY_CHARS, maxLength=45, minLength=1)
	private String firstName;
	
	@Validation (regexp=RegExps.ONLY_CHARS, maxLength=45, minLength=1)
	private String lastName;
	
	@Validation(regexp=RegExps.ONLY_CHARS, maxLength=45, minLength=1)
	private String middleName;
	
	@DateField(format="dd.MM.yyyy")
	private String birthDate;
	
	@Validation(regexp=RegExps.ONLY_CHARS, maxLength=45, minLength=1)
	private String nationality;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, maxLength=8, minLength=1)
	private String profession;
	
	@Validation(regexp=RegExps.ONLY_CHARS, maxLength=8, minLength=1)
	private String education;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength=200, minLength=0)
	private String institution;

	@Validation(regexp=RegExps.ONLY_DIGITS, maxLength=45, minLength=0)
	private String phone;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength=200, minLength=0)
	private String address;

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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserModificationForm [firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", birthDate="
				+ birthDate + ", nationality=" + nationality + ", profession="
				+ profession + ", education=" + education + ", institution="
				+ institution + ", phone=" + phone + ", address=" + address
				+ "]";
	}
	
	
	
	

}
