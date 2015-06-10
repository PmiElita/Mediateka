package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.dao.statement.BookStatements;
import com.mediateka.model.Book;
import com.mediateka.model.BookLanguage;
import com.mediateka.model.BookMeaning;
import com.mediateka.model.BookType;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookDAO {

	public void saveBook(Book book) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.INSERT_BOOK);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.INSERT_BOOK_ORDER);

			statement.executeUpdate();

		}
	}

	public void updateBook(Book book) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.UPDATE_BOOK);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.UPDATE_BOOK_ORDER);

			statement.executeUpdate();

		}
	}

	public Book getBookById(Integer id) throws SQLException,
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

	public Book getBookByName(String name) throws SQLException,
			ReflectiveOperationException {

		Book book = new Book();
		book.setName(name);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_NAME);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_NAME_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Book.class);

		}
	}

	public Book getBookByAuthor(String author) throws SQLException,
			ReflectiveOperationException {

		Book book = new Book();
		book.setAuthor(author);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_AUTHOR);

			Transformer.valueIntoPreparedStatement(statement, book,
					BookStatements.SELECT_BOOK_BY_AUTHOR_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Book.class);

		}
	}

	public Book getBookByType(String type) throws SQLException,
			ReflectiveOperationException {

		BookType bookType = new BookType();
		bookType.setName(type);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_TYPE);

			Transformer.valueIntoPreparedStatement(statement, bookType,
					BookStatements.SELECT_BOOK_BY_TYPE_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Book.class);

		}
	}

	public Book getBookByMeaning(String meaning) throws SQLException,
			ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setName(meaning);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_MEANING);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookStatements.SELECT_BOOK_BY_MEANING_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Book.class);

		}
	}

	public Book getBookByLanguage(String language) throws SQLException,
			ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setName(language);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookStatements.SELECT_BOOK_BY_LANGUAGE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookStatements.SELECT_BOOK_BY_LANGUAGE_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Book.class);

		}
	}

}
