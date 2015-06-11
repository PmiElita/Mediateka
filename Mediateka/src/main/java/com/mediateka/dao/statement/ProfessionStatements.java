package com.mediateka.dao.statement;

public class ProfessionStatements {
	public static final String INSERT_PROFESSION = "INSERT INTO profession"
			+ "(name, state) VALUES (?,?)";
	public static final String[] INSERT_PROFESSION_ORDER = { "name", "state" };

	public static final String SELECT_PROFESSION_BY_ID = "SELECT * FROM profession WHERE id = ?";
	public static final String[] SELECT_PROFESSION_BY_ID_ORDER = { "id" };

	public static final String SELECT_PROFESSION_BY_NAME_REGEX = "SELECT * FROM profession WHERE name REGEXP ?";
	public static final String[] SELECT_PROFESSION_BY_NAME_REGEX_ORDER = { "name" };

	public static final String SELECT_PROFESSION_BY_STATE = "SELECT * FROM profession WHERE state=?";
	public static final String[] SELECT_PROFESSION_BY_STATE_ORDER = { "state" };

	public static final String UPDATE_PROFESSION_BY_ID = "UPDATE profession SET name=?, state=?"
			+ "WHERE id = ?";
	public static final String[] UPDATE_PROFESSION_BY_ID_ORDER = { "name",
			"state", "id" };

	public static final String SELECT_PROFESSION_ALL = "SELECT * FROM profession";
}