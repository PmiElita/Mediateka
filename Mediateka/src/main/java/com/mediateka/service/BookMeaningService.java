package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.BookMeaningDAO;
import com.mediateka.model.BookMeaning;

public class BookMeaningService {

	private BookMeaningDAO bookMeaningDAO = new BookMeaningDAO();

	public void saveBookMeaning(BookMeaning bookMeaning) throws SQLException,
			ReflectiveOperationException {
		bookMeaningDAO.saveBookMeaning(bookMeaning);
	}

	public void updateBookMeaning(BookMeaning bookMeaning) throws SQLException,
			ReflectiveOperationException {
		bookMeaningDAO.updateBookMeaning(bookMeaning);
	}

	public BookMeaning getBookMeaningById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return bookMeaningDAO.getBookMeaningById(id);
	}

	public BookMeaning getBookMeaningByName(String name) throws SQLException,
			ReflectiveOperationException {
		return bookMeaningDAO.getBookMeaningByName(name);
	}

}
