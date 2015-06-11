package com.mediateka.dao.statement;

public class BookMeaningStatements {
	public static final String INSERT_BOOK_MEANING = "INSERT INTO "
			+ "book_meaning " + "(name, state) " + "VALUES " + "(?, ?) ";
	public static final String[] INSERT_BOOK_MEANING_ORDER = { "name", "state" };

	public static final String UPDATE_BOOK_MEANING = "UPDATE book_meaning "
			+ "SET " + "name = ? " + "state = ? " + "WHERE " + "id = ? ";
	public static final String[] UPDATE_BOOK_MEANING_ORDER = { "name", "state",
			"id" };

	public static final String SELECT_BOOK_MEANING_BY_ID = "SELECT * "
			+ "FROM " + "book_meaning " + "WHERE " + "id = ? ";
	public static final String[] SELECT_BOOK_MEANING_BY_ID_ORDER = { "id" };

	public static final String SELECT_BOOK_MEANING_BY_STATE = "SELECT * "
			+ "FROM " + "book_meaning " + "WHERE " + "state= ? ";
	public static final String[] SELECT_BOOK_MEANING_BY_STATE_ORDER = { "state" };

	public static final String SELECT_BOOK_MEANING_BY_NAME_REGEX = "SELECT * "
			+ "FROM " + "book_meaning " + "WHERE " + "name REGEXP ?";
	public static final String[] SELECT_BOOK_MEANING_BY_NAME_REGEX_ORDER = { "name" };

	public static final String SELECT_BOOK_MEANING_ALL = "SELECT * FROM book_meaning";
}