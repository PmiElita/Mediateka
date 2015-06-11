package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.BookMeaningDAO;
import com.mediateka.model.BookMeaning;
import com.mediateka.model.enums.State;

public class BookMeaningService {

	public static void saveBookMeaning(BookMeaning bookMeaning)
			throws SQLException, ReflectiveOperationException {
		BookMeaningDAO.saveBookMeaning(bookMeaning);
	}

	public static void updateBookMeaning(BookMeaning bookMeaning)
			throws SQLException, ReflectiveOperationException {
		BookMeaningDAO.updateBookMeaning(bookMeaning);
	}

	public static BookMeaning getBookMeaningById(Integer id)
			throws SQLException, ReflectiveOperationException {
		return BookMeaningDAO.getBookMeaningById(id);
	}

	public static List<BookMeaning> getBookMeaningByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return BookMeaningDAO.getBookMeaningByNameRegex(name);
	}

	public static List<BookMeaning> getBookMeaningByState(State state)
			throws SQLException, ReflectiveOperationException {
		return BookMeaningDAO.getBookMeaningByState(state);
	}

	public static List<BookMeaning> getBookMeaningAll() throws SQLException,
			ReflectiveOperationException {
		return BookMeaningDAO.getBookMeaningAll();
	}
}