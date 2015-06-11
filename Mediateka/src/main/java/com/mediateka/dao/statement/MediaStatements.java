package com.mediateka.dao.statement;

public class MediaStatements {
	public static final String INSERT_MEDIA = "INSERT INTO media "
			+ "(type, path, description, content_group_id, state) VALUES (?,?,?,?,?)";
	public static final String[] INSERT_MEDIA_ORDER = { "type", "path",
			"description", "content_group_id", "state" };

	public static final String UPDATE_MEDIA_BY_ID = "UPDATE madia "
			+ "SET type=?, path=?, description=?, content_group_id=?, state=?"
			+ " WHERE  id =?";
	public static final String[] UPDATE_MEDIA_BY_ID_ORDER = { "type", "path",
			"description", "content_group_id", "state", "id" };

	public static final String SELECT_MEDIA_BY_ID = "SELECT * FROM media WHERE id =?";
	public static final String[] SELECT_MEDIA_BY_ID_ORDER = { "id" };

	public static final String SELECT_MEDIA_BY_CONTENT_GROUP_ID = "SELECT * FROM media WHERE content_group_id =?";
	public static final String[] SELECT_MEDIA_BY_CONTENT_GROUP_ID_ORDER = { "content_group_id" };

	public static final String SELECT_MEDIA_BY_TYPE = "SELECT * FROM media WHERE type=?";
	public static final String[] SELECT_MEDIA_BY_TYPE_ORDER = { "type" };

	public static final String SELECT_MEDIA_BY_STATE = "SELECT * FROM media WHERE state=?";
	public static final String[] SELECT_MEDIA_BY_STATE_ORDER = { "state" };

	public static final String SELECT_MEDIA_ALL = "SELECT * FROM media";
}