package com.mediateka.dao.statement;

public class ClubStatements {

	public static final String INSERT_CLUB = "INSERT INTO " + "club"
			+ "(name, description, ava_id, state) " + "VALUES "
			+ "(?, ?, ?, ?) ";
	public static final String[] INSERT_CLUB_ORDER = { "name", "description",
			"ava_id", "state" };

	public static final String CALL_INSERT_CLUB = "CALL InsertClub(?,?,?,?)";

	public static final String[] CALL_INSERT_CLUB_ORDER = { "name",
			"description", "ava_id", "state" };

	public static final String UPDATE_CLUB = "UPDATE club " + "SET "
			+ "name = ?, " + "description = ?, " + "ava_id = ?, " + "state = ? "
			+ "WHERE " + "id = ? ";
	public static final String[] UPDATE_CLUB_ORDER = { "name", "description",
			"ava_id", "state", "id" };

	public static final String SELECT_CLUB_BY_ID = "SELECT * " + "FROM "
			+ "club " + "WHERE " + "id = ? ";
	public static final String[] SELECT_CLUB_BY_ID_ORDER = { "id" };

	public static final String SELECT_CLUB_BY_STATE = "SELECT * " + "FROM "
			+ "club " + "WHERE " + "state = ? ";
	public static final String[] SELECT_CLUB_BY_STATE_ORDER = { "state" };

	public static final String SELECT_CLUB_BY_NAME_REGEX = "SELECT * FROM content_group WHERE name REGEXP ?";
	public static final String[] SELECT_CLUB_BY_NAME_REGEX_ORDER = { "name" };

	public static final String SELECT_CLUB_ALL = "SELECT * FROM club";
	
	public static final String SELECT_COUNT_OF_REQUESTED_CLUBS = "SELECT count(*) FROM club WHERE state = 'REQUESTED'";

	public static final String SELECT_ALL_NOT_DELETED_CLUBS ="SELECT * FROM club WHERE state<>'DELETED'";
}