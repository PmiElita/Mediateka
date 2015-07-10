package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.BookDAO;
import com.mediateka.model.Book;
import com.mediateka.model.enums.State;

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

	public static List<Book> getBookByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return BookDAO.getBookByNameRegex(name);
	}

	public static List<Book> getBookByAuthorRegex(String author)
			throws SQLException, ReflectiveOperationException {
		return BookDAO.getBookByAuthorRegex(author);
	}

	public static List<Book> getBookByType(String type) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBookByType(type);
	}

	public static List<Book> getBookByMeaning(String meaning)
			throws SQLException, ReflectiveOperationException {
		return BookDAO.getBookByMeaning(meaning);
	}

	public static List<Book> getBookByLanguage(String language)
			throws SQLException, ReflectiveOperationException {
		return BookDAO.getBookByLanguage(language);
	}

	public static List<Book> getBookByState(State state)
			throws ReflectiveOperationException, SQLException {
		return BookDAO.getBookByState(state);
	}

	public static List<Book> getBookAll() throws ReflectiveOperationException,
			SQLException {
		return BookDAO.getBookAll();
	}

	public static List<Book> getBooksByParameters(String regexp,
			Integer typeId, Integer meaningId, Integer languageId,
			String order, Integer offset, Integer limit) throws SQLException,
			ReflectiveOperationException {
		return BookDAO.getBooksByParameters(regexp, typeId, meaningId,
				languageId, order, offset, limit);
	}

	public static List<Book> getBooksByNameAndAuthor(String name, String author)
			throws SQLException, ReflectiveOperationException {
		return BookDAO.getBooksByNameAndAuthor(name, author);
	}

	public static List<Book> getBooksByRegexp(String regexp)
			throws SQLException, ReflectiveOperationException {

		return BookDAO.getBooksByRegexp(regexp);
	}

	public static Book getBookByLibraryBookId(String libraryBookId)
			throws ReflectiveOperationException, SQLException {
		return BookDAO.getBookByLibraryBookId(libraryBookId);
	}

	public static List<Book> getBooksByLibraryBookIdRegexp(String regexp)
			throws SQLException, ReflectiveOperationException {
		return BookDAO.getBooksByLibraryBookIdRegexp(regexp);
	}
}