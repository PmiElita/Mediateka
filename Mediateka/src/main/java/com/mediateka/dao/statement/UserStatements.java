package com.mediateka.dao.statement;

public class UserStatements {
	public static final String INSERT_USER = "INSERT INTO user "
			+ "(form_id, first_name, last_name, middle_name, birth_date,"
			+ " nationality, education, profession_id, edu_institution,"
			+ " phone, adress, join_date, email, password, role, state, is_form_active, salt, password_changing_token, social_id , ava_id)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static final String[] INSERT_USER_ORDER = { "form_id", "first_name",
			"last_name", "middle_name", "birth_date", "nationality",
			"education", "profession_id", "edu_institution", "phone", "adress",
			"join_date", "email", "password", "role", "state",
			"is_form_active", "salt", "password_changing_token","social_id", "ava_id" };

	public static final String UPDATE_USER_BY_ID = "UPDATE user SET form_id=?, first_name=?, last_name=?, middle_name=?, birth_date=?,"
			+ " nationality=?, education=?, profession_id=?, edu_institution=?,"
			+ " phone=?, adress=?, join_date=?, email=?, password=?, role=?, state=?, is_form_active=?,salt=?, password_changing_token=?,"
			+ " social_id=?, ava_id=? WHERE id =?";
	public static final String[] UPDATE_USER_BY_ID_ORDER = { "form_id",
			"first_name", "last_name", "middle_name", "birth_date",
			"nationality", "education", "profession_id", "edu_institution",
			"phone", "adress", "join_date", "email", "password", "role",
			"state", "is_form_active", "salt", "password_changing_token","social_id","ava_id", "id" };

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

	public static final String SELECT_USER_BY_FORM_ID = "SELECT * FROM user WHERE form_id=? AND is_form_active = 1";
	public static final String[] SELECT_USER_BY_FORM_ID_ORDER = { "form_id" };

	public static final String SELECT_USER_BY_PROFESSION = "SELECT user.* FROM user, profession WHERE user.proffesion_id = proffesion.id AND profession.name=?";
	public static final String[] SELECT_USER_BY_PROFESSION_ORDER = { "name" };

	public static final String SELECT_USER_BY_NATIONALITY = "SELECT * FROM user WHERE nationality=?";
	public static final String[] SELECT_USER_BY_NATIONALITY_ORDER = { "nationality" };
	
	public static final String SELECT_USER_BY_TOKEN = "SELECT * FROM user WHERE password_changing_token=?";
	public static final String[] SELECT_USER_BY_TOKEN_ORDER = {"password_changing_token"};
	

	public static final String SELECT_USER_ALL = "SELECT * FROM user";
	
	public static final String CALL_GET_USERS_BY_ONE_REGEXP = "CALL getUsersByOneRegexp(?,?,?)";
	
	public static final String CALL_GET_USERS_BY_TWO_REGEXP = "CALL getUsersByTwoRegexp(?,?,?,?)";
	
	public static final String CALL_GET_USERS_BY_THREE_REGEXP = "CALL getUsersByThreeRegexp(?,?,?,?,?)";
	
	public static final String SELECT_USER_BY_SOCIAL_ID="SELECT * FROM user WHERE social_id=?";
	
	public static final String[] SELECT_USER_BY_SOCIAL_ID_ORDER ={"social_id"};
	
	public static final String SELECT_USERS_LIMITED = "SELECT * FROM user WHERE state<>'DELETED' LIMIT ?,?";

}