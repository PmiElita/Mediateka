package com.mediateka.model;

import java.sql.Date;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;

public class User {

	@Column(name = "id")
	private Integer id;

	@Column(name = "form_id")
	private Integer formId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "education")
	private String education;

	@Column(name = "profession_id")
	private Integer professionId;

	@Column(name = "edu_institution")
	private String eduInstitution;

	@Column(name = "phone")
	private String phone;

	@Column(name = "adress")
	private String adress;

	@Column(name = "join_date")
	private Date joinDate;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private Role role;

	@Column(name = "state")
	private State state;

	@Column(name = "is_form_active")
	private Boolean isFormActive;

	@Column(name = "salt")
	private String salt;

	@Column(name = "password_changing_token")
	private String passwordChangingToken;

	@Override
	public String toString() {
		return "User [id=" + id + ", formId=" + formId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", birthDate=" + birthDate + ", nationality="
				+ nationality + ", education=" + education + ", professionId="
				+ professionId + ", eduInstitution=" + eduInstitution
				+ ", phone=" + phone + ", adress=" + adress + ", joinDate="
				+ joinDate + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", state=" + state + ", isFormActive="
				+ isFormActive + ", salt=" + salt + ", passwordChangingToken="
				+ passwordChangingToken + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}

	public String getEduInstitution() {
		return eduInstitution;
	}

	public void setEduInstitution(String eduInstitution) {
		this.eduInstitution = eduInstitution;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Boolean getIsFormActive() {
		return isFormActive;
	}

	public void setIsFormActive(Boolean isFormActive) {
		this.isFormActive = isFormActive;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPasswordChangingToken() {
		return passwordChangingToken;
	}

	public void setPasswordChangingToken(String passwordChangingToken) {
		this.passwordChangingToken = passwordChangingToken;
	}

}
