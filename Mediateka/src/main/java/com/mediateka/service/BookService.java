package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.BookDAO;
import com.mediateka.model.Book;

public class BookService {


	public static void saveBook(Book book) throws SQLException,
			ReflectiveOperationException {
		BookDAO.saveBook(book);
	}

	public static void updateBook(Book book) throws SQLException,
			ReflectiveOperationException {
		BookDAO.updateBook(book);
	}

	public static Book getBookById(Integer bookId)
			throws ReflectiveOperationException, SQLException {
		return BookDAO.getBookById(bookId);
	}

	public static List<Book> getBookByName(String name) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBookByName(name);
	}

	public static List<Book> getBookByAuthor(String author) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBookByAuthor(author);
	}

	public static List<Book> getBookByType(String type) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBookByType(type);
	}

	public static List<Book> getBookByMeaning(String meaning) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBookByMeaning(meaning);
	}

	public static List<Book> getBookByLanguage(String language) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBookByLanguage(language);
	}

}
