package com.mediateka.dao.statement;

public class FormRecordStatements {

	public static final String INSERT_FORM_RECORD = "INSERT INTO form_record"
			+ "(date_from, date_till, book_id, goal, event_id, "
			+ "comment, user_id, admin_id, state)"
			+ "VALUES(?,?,?,?,?,?,?,?,?)";

	public static final String[] INSERT_FORM_RECORD_ORDER = { "date_from",
			"date_till", "book_id", "goal", "event_id", "comment", "user_id",
			"admin_id", "state" };

	public static final String SELECT_FORM_RECORD_BY_ID = "SELECT * FROM form_record WHERE id = ?";

	public static final String[] SELECT_FORM_RECORD_BY_ID_ORDER = { "id" };

	public static final String SELECT_FORM_RECORD_BY_USER_ID = "SELECT * FROM form_record WHERE user_id = ?";

	public static final String[] SELECT_FORM_RECORD_BY_USER_ID_ORDER = { "user_id" };

	public static final String SELECT_FORM_RECORD_BY_ADMIN_ID = "SELECT * FROM form_record WHERE admin_id = ?";

	public static final String[] SELECT_FORM_RECORD_BY_ADMIN_ID_ORDER = { "admin_id" };

	public static final String UPDATE_FORM_RECORD_BY_ID = "UPDATE event SET date_from=?,"
			+ " date_till=?, book_id=?, goal=?, "
			+ "event_id=?, comment=?, user_id=?, admin_id=?, state=? WHERE id = ?";
	public static final String[] UPDATE_FORM_RECORD_BY_ID_ORDER = {
			"date_from", "date_till", "book_id", "goal", "event_id", "comment",
			"user_id", "admin_id", "state", "id" };

}
