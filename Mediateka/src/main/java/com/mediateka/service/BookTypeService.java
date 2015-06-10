package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.BookTypeDAO;
import com.mediateka.model.BookType;

public class BookTypeService {


	public void saveBookType(BookType bookType) throws SQLException,
			ReflectiveOperationException {
		BookTypeDAO.saveBookType(bookType);
	}

	public void updateBookType(BookType bookType) throws SQLException,
			ReflectiveOperationException {
		BookTypeDAO.updateBookType(bookType);
	}

	public BookType getBookTypeById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return BookTypeDAO.getBookTypeById(id);
	}

	public List<BookType> getBookTypeByName(String name) throws SQLException,
			ReflectiveOperationException {
		return BookTypeDAO.getBookTypeByName(name);
	}

}
