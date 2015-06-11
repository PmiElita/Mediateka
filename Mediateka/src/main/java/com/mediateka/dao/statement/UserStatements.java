package com.mediateka.dao.statement;

public class UserStatements {
	public static final String INSERT_USER = "INSERT INTO user "
			+ "(form_id, first_name, last_name, middle_name, birth_date,"
			+ " nationality, education, profession_id, edu_institution,"
			+ " phone, adress, join_date, email, password, role, state, is_form_active, salt)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static final String[] INSERT_USER_ORDER = { "form_id", "first_name",
			"last_name", "middle_name", "birth_date", "nationality",
			"education", "profession_id", "edu_institution", "phone", "adress",
			"join_date", "email", "password", "role", "state",
			"is_form_active", "salt" };

	public static final String UPDATE_USER_BY_ID = "UPDATE user SET form_id=?, first_name=?, last_name=?, middle_name=?, birth_date=?,"
			+ " nationality=?, education=?, profession_id=?, edu_institution=?,"
			+ " phone=?, adress=?, join_date=?, email=?, password=?, role=?, state=?, is_form_active=?,salt=? WHERE id =?";
	public static final String[] UPDATE_USER_BY_ID_ORDER = { "form_id",
			"first_name", "last_name", "middle_name", "birth_date",
			"nationality", "education", "profession_id", "edu_institution",
			"phone", "adress", "join_date", "email", "password", "role",
			"state", "is_from_active", "salt", "id" };

	public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id =?";
	public static final String[] SELECT_USER_BY_ID_ORDER = { "id" };

	public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email =?";
	public static final String[] SELECT_USER_BY_EMAIL_ORDER = { "email" };

	public static final String SELECT_USER_BY_STATE = "SELECT * FROM user WHERE state=?";
	public static final String[] SELECT_USER_BY_STATE_ORDER = { "state" };

	public static final String SELECT_USER_BY_FORM_ACTIVITY = "SELECT * FROM user WHERE is_form_active=?";
	public static final String[] SELECT_USER_BY_FORM_ACTIVITY_ORDER = { "is_form_active" };

	public static final String SELECT_USER_BY_ROLE = "SELECT * FROM user WHERE role=?";
	public static final String[] SELECT_USER_BY_ROLE_ORDER = { "role" };

	public static final String SELECT_USER_BY_FORM_ID = "SELECT * FROM user WHERE form_id=?";
	public static final String[] SELECT_USER_BY_FORM_ID_ORDER = { "form_id" };

	public static final String SELECT_USER_BY_PROFESSION = "SELECT user.* FROM user, profession WHERE user.proffesion_id = proffesion.id AND profession.name=?";
	public static final String[] SELECT_USER_BY_PROFESSION_ORDER = { "name" };

	public static final String SELECT_USER_BY_NATIONALITY = "SELECT * FROM user nationality=?";
	public static final String[] SELECT_USER_BY_NATIONALITY_ORDER = { "nationality" };

	public static final String SELECT_USER_ALL = "SELECT * FROM user";
}