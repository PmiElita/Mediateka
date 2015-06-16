package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.BookTypeDAO;
import com.mediateka.model.BookType;
import com.mediateka.model.enums.State;

public class BookTypeService {
	public static void saveBookType(BookType bookType) throws SQLException,
			ReflectiveOperationException {
		BookTypeDAO.saveBookType(bookType);
	}

	public static void updateBookType(BookType bookType) throws SQLException,
			ReflectiveOperationException {
		BookTypeDAO.updateBookType(bookType);
	}

	public static BookType getBookTypeById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return BookTypeDAO.getBookTypeById(id);
	}

	public static List<BookType> getBookTypeByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return BookTypeDAO.getBookTypeByNameRegex(name);
	}

	public static List<BookType> getBookTypeByState(State state)
			throws SQLException, ReflectiveOperationException {
		return BookTypeDAO.getBookTypeByState(state);
	}

	public static List<BookType> getBookTypeAll() throws SQLException,
			ReflectiveOperationException {
		return BookTypeDAO.getBookTypeAll();
	}
}