package com.mediateka.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.statement.BookStatements;
import com.mediateka.model.Book;
import com.mediateka.model.BookLanguage;
import com.mediateka.model.BookMeaning;
import com.mediateka.model.BookType;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookDAO {
	public static void saveBook(Book book) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.INSERT_BOOK);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.INSERT_BOOK_ORDER);

			statement.executeUpdate();

		}
	}

	public static void updateBook(Book book) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.UPDATE_BOOK);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.UPDATE_BOOK_ORDER);

			statement.executeUpdate();

		}
	}

	public static Book getBookById(Integer id) throws SQLException,
			ReflectiveOperationException {

		Book book = new Book();
		book.setId(id);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Book.class);

		}
	}

	public static List<Book> getBookByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {

		Book book = new Book();
		book.setName(name);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_NAME_REGEX);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_NAME_REGEX_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);

		}
	}

	public static List<Book> getBookByAuthorRegex(String author)
			throws SQLException, ReflectiveOperationException {

		Book book = new Book();
		book.setAuthor(author);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_AUTHOR_REGEX);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_AUTHOR_REGEX_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);

		}
	}

	public static List<Book> getBookByType(String type) throws SQLException,
			ReflectiveOperationException {

		BookType bookType = new BookType();
		bookType.setName(type);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_TYPE);

			Transformer.valueIntoPreparedStatement(statement, bookType,
					BookStatements.SELECT_BOOK_BY_TYPE_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);

		}
	}

	public static List<Book> getBookByMeaning(String meaning)
			throws SQLException, ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setName(meaning);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_MEANING);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookStatements.SELECT_BOOK_BY_MEANING_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);

		}
	}

	public static List<Book> getBookByLanguage(String language)
			throws SQLException, ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setName(language);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_LANGUAGE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookStatements.SELECT_BOOK_BY_LANGUAGE_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);

		}
	}

	public static List<Book> getBookByState(State state) throws SQLException,
			ReflectiveOperationException {

		Book book = new Book();
		book.setState(state);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_STATE);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_STATE_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);

		}
	}

	public static List<Book> getBookAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_ALL);
			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Book.class);
		}
	}

	public static List<Book> getBooksByParameters(String regexp,
			Integer typeId, Integer meaningId, Integer languageId,
			String order, Integer offset, Integer limit) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(BookStatements.CALL_GET_BOOKS_BY_PARAMETERS);
			statement.setString(1, regexp);
			statement.setInt(2, typeId);
			statement.setInt(3, meaningId);
			statement.setInt(4, languageId);
			statement.setString(5, order);
			statement.setInt(6, offset);
			statement.setInt(7, limit);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, Book.class);
		}
	}

	public static List<Book> getBooksByNameAndAuthor(String name, String author)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_NAME_AND_AUTHOR);
			Book book = new Book();
			book.setName(name);
			book.setAuthor(author);
			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_NAME_AND_AUTHOR_ORDER);
			;
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, Book.class);
		}
	}

	public static List<Book> getBooksByRegexp(String regexp)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(BookStatements.CALL_GET_BOOKS_BY_REGEXP);
			statement.setString(1, regexp);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, Book.class);
		}
	}

	public static Book getBookByLibraryBookId(String libraryBookId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_LIBRARY_BOOK_ID);
			Book book = new Book();
			book.setLibraryBookId(libraryBookId);
			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_LIBRARY_BOOK_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Book.class);
		}
	}

	public static List<Book> getBooksByLibraryBookIdRegexp(String regexp)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOKS_BY_LIBRARY_BOOK_ID_REGEXP);
			statement.setString(1, regexp);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, Book.class);
		}
	}
}