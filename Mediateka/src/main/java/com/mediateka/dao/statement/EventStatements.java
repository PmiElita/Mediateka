package com.mediateka.dao.statement;

public class EventStatements {

	public static final String INSER_EVENT = "INSERT INTO event "
			+ "(type, name, date_from, date_till, club_id, state, description)"
			+ "VALUES (?,?,?,?,?,?,?)";

	public static final String[] INSERT_EVENT_ORDER = { "type", "name",
			"date_from", "date_till", "club_id", "state", "description" };

	public static final String SELECT_EVENT_BY_ID = "SELECT  * FROM event WHERE id = ?";

	public static final String[] SELECT_EVENT_BY_ID_ORDER = { "id" };

	public static final String SELECT_EVENT_BY_CLUB_ID = "SELECT * FROM event "
			+ "WHERE club_id = ?";

	public static final String[] SELECT_EVENT_BY_CLUB_ID_ORDER = { "club_id" };

	public static final String UPDATE_EVENT_BY_ID = "UPDATE event SET type=?, name=?,"
			+ " date_from=?, date_till=?, "
			+ "club_id=?, state=?, description=? WHERE id = ?";
	
	public static final String[] UPDATE_EVENT_BY_ID_ORDER = {"type", "name",
		"date_from", "date_till", "club_id", "state", "description", "id"};
}