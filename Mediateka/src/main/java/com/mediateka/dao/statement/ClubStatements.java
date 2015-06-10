package com.mediateka.dao.statement;

public class ClubStatements {

	public static final String INSERT_CLUB = 
			"INSERT INTO "
			+ "club"
			+ "(name, description, ava_id, state) "
			+ "VALUES "
			+ "(?, ?, ?, ?) ";
	public static final String[] INSERT_CLUB_ORDER = {"name", "description", "ava_id", "state"};
	
	
	
	public static final String UPDATE_CLUB = 
			"UPDATE club "
			+ "SET "
			+ "name = ? "
			+ "description = ? "
			+ "ava_id = ? "
			+ "state = ? "
			+ "WHERE "
			+ "id = ? ";
	public static final String[] UPDATE_CLUB_ORDER = {"name", "description", "ava_id", "state", "id"};
	
	
	
	public static final String SELECT_CLUB_BY_ID = 
			"SELECT * "
			+ "FROM "
			+ "club "
			+ "WHERE "
			+ "id = ? ";
	public static final String[] SELECT_CLUB_BY_ID_ORDER = {"id"};
	
	
	
	public static final String SELECT_CLUB_BY_NAME = 
			"SELECT * "
			+ "FROM "
			+ "club "
			+ "WHERE "
			+ "name = ? ";
	public static final String[] SELECT_CLUB_BY_NAME_ORDER = {"name"};
	
	
	
}
