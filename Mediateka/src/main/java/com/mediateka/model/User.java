package com.mediateka.model;

import java.sql.Date;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.Role;

public class User {
	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "form_id")
	private Integer formId;
	
	@Column(name = "first_name")
	private String fistName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "birth_date")
	private Date birthDate; 
	
	@Column(name ="nationality")
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
	

}
