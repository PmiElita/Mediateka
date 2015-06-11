package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.BookLanguageDAO;
import com.mediateka.model.BookLanguage;
import com.mediateka.model.enums.State;

public class BookLanguageService {

	public static void saveBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {
		BookLanguageDAO.saveBookLanguage(bookLanguage);
	}

	public static void updateBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {
		BookLanguageDAO.updateBookLanguage(bookLanguage);
	}

	public static BookLanguage getBookLanguageById(Integer id)
			throws SQLException, ReflectiveOperationException {
		return BookLanguageDAO.getBookLanguageById(id);
	}

	public static List<BookLanguage> getBookLanguageByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return BookLanguageDAO.getBookLanguageByNameRegex(name);
	}

	public static List<BookLanguage> getBookLanguageByState(State state)
			throws SQLException, ReflectiveOperationException {
		return BookLanguageDAO.getBookLanguageByState(state);
	}

	public static List<BookLanguage> getBookLanguageAll() throws SQLException,
			ReflectiveOperationException {
		return BookLanguageDAO.getBookLanguageAll();
	}
}