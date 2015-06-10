package com.mediateka.dao.statement;

public class BookLanguageStatements {

	public static final String INSERT_BOOK_LANGUAGE = 
			"INSERT INTO "
			+ "book_language "
			+ "(name, state) "
			+ "VALUES "
			+ "(?, ?) ";
	public static final String[] INSERT_BOOK_LANGUAGE_ORDER = {"name", "state"};
	
	
	
	public static final String UPDATE_BOOK_LANGUAGE = 
			"UPDATE book_language "
			+ "SET "
			+ "name = ? "
			+ "state = ? "
			+ "WHERE "
			+ "id = ? ";
	public static final String[] UPDATE_BOOK_LANGUAGE_ORDER = {"name", "state", "id"};
	
	
	
	public static final String SELECT_BOOK_LANGUAGE_BY_ID = 
			"SELECT * "
			+ "FROM "
			+ "book_language "
			+ "WHERE "
			+ "id = ? ";
	public static final String[] SELECT_BOOK_LANGUAGE_BY_ID_ORDER = {"id"};
	
	
	
	public static final String SELECT_BOOK_LANGUAGE_BY_NAME = 
			"SELECT * "
			+ "FROM "
			+ "book_language "
			+ "WHERE "
			+ "name = ? ";
	public static final String[] SELECT_BOOK_LANGUAGE_BY_NAME_ORDER = {"name"};
}
