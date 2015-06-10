package com.mediateka.dao.statement;

public class UserStatements {
	public static final String INSERT_USER = "INSERT INTO user "
			+ "(form_id, first_name, last_name, middle_name, birth_date,"
			+ " nationality, education, profession_id, edu_institution,"
			+ " phone, adress, join_date, email, password, role, state)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static final String[] INSERT_USER_ORDER = { "form_id", "first_name",
			"last_name", "middle_name", "birth_date", "nationality",
			"education", "profession_id", "edu_institution", "phone", "adress",
			"join_date", "email", "password", "role", "state" };

	public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id =?";
	public static final String[] SELECT_USER_BY_ID_ORDER = { "id" };

	public static final String UPDATE_USER_BY_ID = "UPDATE user SET form_id=?, first_name=?, last_name=?, middle_name=?, birth_date=?,"
			+ " nationality=?, education=?, profession_id=?, edu_institution=?,"
			+ " phone=?, adress=?, join_date=?, email=?, password=?, role=?, state=? WHERE id =?";
	public static final String[] UPDATE_USER_BY_ID_ORDER = { "form_id",
			"first_name", "last_name", "middle_name", "birth_date",
			"nationality", "education", "profession_id", "edu_institution",
			"phone", "adress", "join_date", "email", "password", "role",
			"state", "id" };

	public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email =?";
	public static final String[] SELECT_USER_BY_EMAIL_ORDER = { "email" };
}
