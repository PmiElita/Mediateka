package com.mediateka.dao.statement;

public class ClubEventMemberStatements {

	public static final String INSERT_CLUB_EVENT_MEMBER = "INSERT INTO club_event_member"
			+ " (user_id, club_id, event_id, type, state) VALUES (?,?,?,?,?)";
	public static final String[] INSERT_CLUB_EVENT_MEMBER_ORDER = { "user_id",
			"club_id", "event_id", "type", "state" };

	public static final String UPDATE_CLUB_EVENT_MEMBER_BY_ID = "UPDATE club_event_member SET"
			+ " user_id=?, club_id=?, event_id=?, type=?, state=? WHERE id =?";
	public static final String[] UPDATE_CLUB_EVENT_MEMBER_BY_ID_ORDER = {
			"user_id", "club_id", "event_id", "type", "state", "id" };

	public static final String SELECT_CLUB_EVENT_MEMBER_BY_ID = "SELECT * "
			+ "FROM club_event_member WHERE  id =?";
	public static final String[] SELECT_CLUB_EVENT_MEMBER_BY_ID_ORDER = {"id"};
	
	public static final String SELECT_CLUB_EVENT_MEMBER_BY_USER_ID = "SELECT * "
			+ "FROM club_event_member WHERE  user_id =?";
	public static final String[] SELECT_CLUB_EVENT_MEMBER_BY_USER_ID_ORDER = {"user_id"};
	
	public static final String SELECT_CLUB_EVENT_MEMBER_BY_CLUB_ID = "SELECT * "
			+ "FROM club_event_member WHERE  club_id =?";
	public static final String[] SELECT_CLUB_EVENT_MEMBER_BY_CLUB_ID_ORDER = {"club_id"};
	
	public static final String SELECT_CLUB_EVENT_MEMBER_BY_EVENT_ID = "SELECT * "
			+ "FROM club_event_member WHERE  event_id =?";
	public static final String[] SELECT_CLUB_EVENT_MEMBER_BY_EVENT_ID_ORDER = {"event_id"};
}
