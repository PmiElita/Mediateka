package com.mediateka.dao.statement;

public class LikeRecordStatements {

	public static final String INSERT_LIKE_RECORD = "INSERT INTO like_record "
			+ "(user_id, content_group_id, state) VALUES (?,?,?)";

	public static final String[] INSERT_LIKE_RECORD_ORDER = { "user_id",
			"content_group_id", "state" };

	public static final String UPDATE_LIKE_RECORD = "UPDATE like_record SET "
			+ "user_id = ?, content_group_id = ?, state = ? WHERE id = ?";

	public static final String[] UPDATE_LIKE_RECORD_ORDER = { "user_id",
			"content_group_id", "state", "id" };

	public static final String SELECT_LIKE_RECORD_BY_ID = "SELECT * FROM like_record "
			+ "WHERE id = ?";

	public static final String[] SELECT_LIKE_RECORD_BY_ID_ORDER = { "id" };

	public static final String SELECT_LIKE_RECORD_BY_USER_ID = "SELECT * FROM like_record "
			+ "WHERE user_id = ?";

	public static final String[] SELECT_LIKE_RECORD_BY_USER_ID_ORDER = { "user_id" };

	public static final String SELECT_LIKE_RECORD_BY_CONTENT_GROUP_ID = "SELECT * "
			+ "FROM like_record "
			+ "WHERE content_group_id = ?";	

	public static final String[] SELECT_LIKE_RECORD_BY_CONTENT_GROUP_ID_ORDER = { "content_group_id" };
	
	
	public static final String SELECT_LIKE_RECORD_BY_USER_ID_AND_CONTENT_GROUP_ID = "SELECT * "
			+ "from like_record WHERE user_id = ? AND content_group_id = ?";
	
	public static final String[] SELECT_LIKE_RECORD_BY_USER_ID_AND_CONTENT_GROUP_ID_ORDER = 
		{"user_id", "content_group_id"};

}
