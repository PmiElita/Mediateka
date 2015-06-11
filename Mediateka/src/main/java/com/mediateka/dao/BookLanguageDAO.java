package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.statement.BookLanguageStatements;
import com.mediateka.model.BookLanguage;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookLanguageDAO {
	public static void saveBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.INSERT_BOOK_LANGUAGE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.INSERT_BOOK_LANGUAGE_ORDER);

			statement.executeUpdate();

		}
	}

	public static void updateBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.UPDATE_BOOK_LANGUAGE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.UPDATE_BOOK_LANGUAGE_ORDER);

			statement.executeUpdate();

		}
	}

	public static BookLanguage getBookLanguageById(Integer id)
			throws SQLException, ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setId(id);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();

			return Transformer.transformResultSetIntoObject(rs,
					BookLanguage.class);

		}
	}

	public static List<BookLanguage> getBookLanguageByState(State state)
			throws SQLException, ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setState(state);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_STATE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_STATE_ORDER);

			ResultSet rs = statement.executeQuery();

			return Transformer.transformResultSetIntoList(rs,
					BookLanguage.class);

		}
	}

	public static List<BookLanguage> getBookLanguageByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setName(name);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_NAME_REGEX);

			Transformer
					.valueIntoPreparedStatement(
							statement,
							bookLanguage,
							BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_NAME_REGEX_ORDER);

			ResultSet rs = statement.executeQuery();

			return Transformer.transformResultSetIntoList(rs,
					BookLanguage.class);

		}
	}

	public static List<BookLanguage> getBookLanguageAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.SELECT_BOOK_LANGUAGE_ALL);

			ResultSet rs = statement.executeQuery();

			return Transformer.transformResultSetIntoList(rs,
					BookLanguage.class);

		}
	}
}