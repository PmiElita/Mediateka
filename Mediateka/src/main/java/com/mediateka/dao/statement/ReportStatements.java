package com.mediateka.dao.statement;

public class ReportStatements {
	public static final String INSERT_REPORT = "INSERT INTO report "
			+ "(email, text, name, date, state) "
			+ " VALUES (?,?,?,?,?) ";
	public static final String[] INSERT_REPORT_ORDER = { "email", "text",
			"name", "date", "state" };

	public static final String UPDATE_REPORT = "UPDATE report SET email=?, text=?, name=?, date=?, state=?"
			+ " WHERE id = ?";
	public static final String[] UPDATE_REPORT_ORDER = { "email",
			"text", "name", "date", "state", "id" };

	public static final String SELECT_REPORT_BY_ID = "SELECT * FROM report WHERE id = ? and state <> 'DELETED'";
	public static final String[] SELECT_REPORT_BY_ID_ORDER = { "id" };


}
