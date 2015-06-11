package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.statement.BookMeaningStatements;
import com.mediateka.model.BookMeaning;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookMeaningDAO {
	public static void saveBookMeaning(BookMeaning bookMeaning)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.INSERT_BOOK_MEANING);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.INSERT_BOOK_MEANING_ORDER);

			statement.executeUpdate();

		}
	}

	public static void updateBookMeaning(BookMeaning bookMeaning)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.UPDATE_BOOK_MEANING);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.UPDATE_BOOK_MEANING_ORDER);

			statement.executeUpdate();

		}
	}

	public static BookMeaning getBookMeaningById(Integer id)
			throws SQLException, ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setId(id);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.SELECT_BOOK_MEANING_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.SELECT_BOOK_MEANING_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();

			return Transformer.transformResultSetIntoObject(rs,
					BookMeaning.class);

		}
	}

	public static List<BookMeaning> getBookMeaningByState(State state)
			throws SQLException, ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setState(state);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.SELECT_BOOK_MEANING_BY_STATE);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.SELECT_BOOK_MEANING_BY_STATE_ORDER);

			ResultSet rs = statement.executeQuery();

			return Transformer
					.transformResultSetIntoList(rs, BookMeaning.class);

		}
	}

	public static List<BookMeaning> getBookMeaningByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setName(name);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.SELECT_BOOK_MEANING_BY_NAME_REGEX);

			Transformer
					.valueIntoPreparedStatement(
							statement,
							bookMeaning,
							BookMeaningStatements.SELECT_BOOK_MEANING_BY_NAME_REGEX_ORDER);

			ResultSet rs = statement.executeQuery();

			return Transformer
					.transformResultSetIntoList(rs, BookMeaning.class);

		}
	}

	public static List<BookMeaning> getBookMeaningAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.SELECT_BOOK_MEANING_ALL);
			ResultSet rs = statement.executeQuery();

			return Transformer
					.transformResultSetIntoList(rs, BookMeaning.class);

		}
	}
}