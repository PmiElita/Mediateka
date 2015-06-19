package com.mediateka.form;

import com.mediateka.annotation.DateField;
import com.mediateka.annotation.EnumField;
import com.mediateka.annotation.Validation;
import com.mediateka.model.enums.EducationType;
import com.mediateka.util.RegExps;

public class AnonymousUserRegistrationForm {

	@Validation(regexp=RegExps.PASSWORD, maxLength=45, minLength=1)
	private String password;

	@Validation(regexp=RegExps.PASSWORD, maxLength=45, minLength=1)
	private String confirmPassword;
	
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
	
//	@Validation(regexp=RegExps.ONLY_CHARS, maxLength=8, minLength=1)
	@EnumField(enumClass = EducationType.class)
	private String education;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength=200, minLength=0)
	private String institution;

	@Validation(regexp=RegExps.EMAIL, maxLength=45, minLength=1)
	private String email;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, maxLength=45, minLength=0)
	private String phone;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength=200, minLength=0)
	private String address;
	
	@Override
	public String toString() {
		return "AnonymousUserRegistrationForm [password=" + password
				+ ", confirmPassword=" + confirmPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", birthDate=" + birthDate + ", nationality="
				+ nationality + ", profession=" + profession + ", education="
				+ education + ", institution=" + institution + ", email="
				+ email + ", phone=" + phone + ", address=" + address + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


}
