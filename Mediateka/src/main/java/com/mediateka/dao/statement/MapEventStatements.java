package com.mediateka.dao.statement;

public class MapEventStatements {
	public static final String INSERT_MAP_EVENT = "INSERT INTO map_event "
			+ "(name,description,adress, state, admin_id, latitude, longitude) "
			+ "VALUES(?,?,?,?,?,?,?)";
	public static final String[] INSERT_MAP_EVENT_ORDER = {"name", "description" ,"adress", "state","admin_id", "latitude", "longitude"};
	
	public static final String UPDATE_MAP_EVENT ="UPDATE map_event SET name=?, description=?, adress=?, state=?, admin_id=?, latitude=?, longitude=? WHERE id =?";
	public static final String[] UPDATE_MAP_EVENT_ORDER = {"name", "description" ,"adress", "state","admin_id", "latitude", "longitude", "id"}; 
	
	public static final String SELECT_ALL_ACTIVE_MAP_EVENTS = "SELECT * FROM map_event WHERE state ='ACTIVE'";

}
