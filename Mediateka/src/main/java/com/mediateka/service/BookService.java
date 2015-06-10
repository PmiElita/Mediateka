package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.BookDAO;
import com.mediateka.model.Book;

public class BookService {
	private BookDAO bookDAO = new BookDAO();

	public void saveBook(Book book) throws SQLException,
			ReflectiveOperationException {
		bookDAO.saveBook(book);
	}

	public void updateBook(Book book) throws SQLException,
			ReflectiveOperationException {
		bookDAO.updateBook(book);
	}

	public Book getBookById(Integer bookId)
			throws ReflectiveOperationException, SQLException {
		return bookDAO.getBookById(bookId);
	}

	public Book getBookByName(String name) throws SQLException,
			ReflectiveOperationException {
		return bookDAO.getBookByName(name);
	}

	public Book getBookByAuthor(String author) throws SQLException,
			ReflectiveOperationException {
		return bookDAO.getBookByAuthor(author);
	}

	public Book getBookByType(String type) throws SQLException,
			ReflectiveOperationException {
		return bookDAO.getBookByType(type);
	}

	public Book getBookByMeaning(String meaning) throws SQLException,
			ReflectiveOperationException {
		return bookDAO.getBookByMeaning(meaning);
	}

	public Book getBookByLanguage(String language) throws SQLException,
			ReflectiveOperationException {
		return bookDAO.getBookByLanguage(language);
	}

}
