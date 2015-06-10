package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.BookLanguageDAO;
import com.mediateka.model.BookLanguage;

public class BookLanguageService {

	private BookLanguageDAO bookLanguageDAO = new BookLanguageDAO();

	public void saveBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {
		bookLanguageDAO.saveBookLanguage(bookLanguage);
	}

	public void updateBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {
		bookLanguageDAO.updateBookLanguage(bookLanguage);
	}

	public BookLanguage getBookLanguageById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return bookLanguageDAO.getBookLanguageById(id);
	}

	public BookLanguage getBookLanguageByName(String name) throws SQLException,
			ReflectiveOperationException {
		return bookLanguageDAO.getBookLanguageByName(name);
	}

}
