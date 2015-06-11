package com.mediateka.dao.statement;

public class BookTypeStatements {
	public static final String INSERT_BOOK_TYPE = "INSERT INTO " + "book_type "
			+ "(name, state) " + "VALUES " + "(?, ?) ";
	public static final String[] INSERT_BOOK_TYPE_ORDER = { "name", "state" };

	public static final String UPDATE_BOOK_TYPE = "UPDATE book_type " + "SET "
			+ "name = ? " + "state = ? " + "WHERE " + "id = ? ";
	public static final String[] UPDATE_BOOK_TYPE_ORDER = { "name", "state",
			"id" };

	public static final String SELECT_BOOK_TYPE_BY_ID = "SELECT * " + "FROM "
			+ "book_type " + "WHERE " + "id = ? ";
	public static final String[] SELECT_BOOK_TYPE_BY_ID_ORDER = { "id" };

	public static final String SELECT_BOOK_TYPE_BY_NAME_REGEX = "SELECT * "
			+ "FROM " + "book_type " + "WHERE " + "name REGEXP ?";
	public static final String[] SELECT_BOOK_TYPE_BY_NAME_REGEX_ORDER = { "name" };

	public static final String SELECT_BOOK_TYPE_BY_STATE = "SELECT * "
			+ "FROM " + "book_type " + "WHERE " + "state=?";
	public static final String[] SELECT_BOOK_TYPE_BY_STATE_ORDER = { "state" };

	public static final String SELECT_BOOK_TYPE_ALL = "SELECT * FROM book_type";
}