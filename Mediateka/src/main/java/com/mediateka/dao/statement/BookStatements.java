package com.mediateka.dao.statement;

public class BookStatements {
	public static final String INSERT_BOOK = "INSERT INTO "
			+ "book "
			+ "(name, author, state, type_id, meaning_id, language_id, media_id) "
			+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?) ";
	public static final String[] INSERT_BOOK_ORDER = { "name", "author",
			"state", "type_id", "meaning_id", "language_id", "media_id" };

	public static final String UPDATE_BOOK = "UPDATE book " + "SET "
			+ "name = ?, " + "author = ?, " + "state = ?, " + "type_id = ?, "
			+ "meaning_id = ?, " + "language_id = ?, " + "media_id = ? "
			+ "WHERE " + "id = ?";
	public static final String[] UPDATE_BOOK_ORDER = { "name", "author",
			"state", "type_id", "meaning_id", "language_id", "media_id", "id" };

	public static final String SELECT_BOOK_BY_ID = "SELECT * " + "FROM "
			+ "book " + "WHERE id = ?";
	public static final String[] SELECT_BOOK_BY_ID_ORDER = { "id" };

	public static final String SELECT_BOOK_BY_NAME_REGEX = "SELECT * "
			+ "FROM " + "book " + "WHERE " + "name REGEXP ?";
	public static final String[] SELECT_BOOK_BY_NAME_REGEX_ORDER = { "name" };

	public static final String SELECT_BOOK_BY_AUTHOR_REGEX = "SELECT *"
			+ "FROM " + "book " + "WHERE " + "author REGEXP ?";
	public static final String[] SELECT_BOOK_BY_AUTHOR_REGEX_ORDER = { "author" };

	public static final String SELECT_BOOK_BY_TYPE = "SELECT book.* " + "FROM "
			+ "book, book_type " + "WHERE " + "book.type_id = book_type.id "
			+ "AND " + "book_type.name = ?";
	public static final String[] SELECT_BOOK_BY_TYPE_ORDER = { "name" };

	public static final String SELECT_BOOK_BY_MEANING = "SELECT book.* "
			+ "FROM " + "book, book_meaning " + "WHERE "
			+ "book.meaning_id = book_meaning.id " + "AND "
			+ "book_meaning.name = ?";
	public static final String[] SELECT_BOOK_BY_MEANING_ORDER = { "name" };

	public static final String SELECT_BOOK_BY_LANGUAGE = "SELECT book.* "
			+ "FROM " + "book, book_language " + "WHERE "
			+ "book.language_id = book_language.id " + "AND "
			+ "book_language.name = ?";
	public static final String[] SELECT_BOOK_BY_LANGUAGE_ORDER = { "name" };

	public static final String SELECT_BOOK_BY_STATE = "SELECT * " + "FROM "
			+ "book " + "WHERE state=?";
	public static final String[] SELECT_BOOK_BY_STATE_ORDER = { "state" };

	public static final String SELECT_BOOK_ALL = "SELECT * FROM book";
	
	public static final String CALL_GET_BOOKS_BY_REGEXP = "CALL getBooksByRegexp(?)";
	
	public static final String SELECT_BOOK_BY_NAME_AND_AUTHOR="SELECT * FROM book WHERE name=? AND author=?";

	public static final String[] SELECT_BOOK_BY_NAME_AND_AUTHOR_ORDER = {"name","author"};
}