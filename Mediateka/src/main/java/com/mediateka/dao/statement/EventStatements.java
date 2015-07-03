package com.mediateka.dao.statement;

public class EventStatements {

	public static final String INSERT_EVENT = "INSERT INTO event "
			+ "(type, name, date_from, date_till, club_id, state, description)"
			+ "VALUES (?,?,?,?,?,?,?,?)";
	public static final String[] INSERT_EVENT_ORDER = { "type", "name",
			"date_from", "date_till", "club_id", "state", "description",
			"ava_id" };

	public static final String SELECT_EVENT_BY_ID = "SELECT  * FROM event WHERE id = ? AND state <> 'DELETED'";
	public static final String[] SELECT_EVENT_BY_ID_ORDER = { "id" };

	public static final String SELECT_EVENT_BY_NAME_REGEX = "SELECT  * FROM event WHERE name REGEXP ? AND state <> 'DELETED'";
	public static final String[] SELECT_EVENT_BY_NAME_REGEX_ORDER = { "name" };

	public static final String SELECT_EVENT_BY_TYPE = "SELECT  * FROM event WHERE type = ? AND state <> 'DELETED'";
	public static final String[] SELECT_EVENT_BY_TYPE_ORDER = { "type" };

	public static final String SELECT_EVENT_BY_CLUB_ID = "SELECT * FROM event "
			+ "WHERE club_id = ? AND state <> 'DELETED'";
	public static final String[] SELECT_EVENT_BY_CLUB_ID_ORDER = { "club_id" };

	public static final String UPDATE_EVENT_BY_ID = "UPDATE event SET type=?, name=?,"
			+ " date_from=?, date_till=?, "
			+ "club_id=?, state=?, description=?, ava_id=? WHERE id = ?";
	public static final String[] UPDATE_EVENT_BY_ID_ORDER = { "type", "name",
			"date_from", "date_till", "club_id", "state", "description",
			"ava_id", "id" };

	public static final String SELECT_EVENT_BY_STATE = "SELECT * FROM event WHERE state=?";
	public static final String[] SELECT_EVENT_BY_STATE_ORDER = { "state" };

	public static final String SELECT_EVENT_ALL = "SELECT * FROM event WHERE state <> 'DELETED'";

	public static final String CALL_GET_EVENTS_BY_DATE = "CALL GetEventsByDate(?)";

	public static final String CALL_INSERT_EVENT = "CALL insertEvent (?,?,?,?,?,?,?,?)";
	public static final String[] CALL_INSERT_EVENT_ORDER = { "type", "name",
			"date_from", "date_till", "club_id", "state", "description",
			"ava_id" };

	public static final String SELECT_COUNT_OF_REQUESTED_EVENTS = "SELECT count(*) FROM event WHERE state = 'REQUESTED'";

}