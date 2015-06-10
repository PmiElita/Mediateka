package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.BookTypeDAO;
import com.mediateka.model.BookType;

public class BookTypeService {

	private BookTypeDAO bookTypeDAO = new BookTypeDAO();

	public void saveBookType(BookType bookType) throws SQLException,
			ReflectiveOperationException {
		bookTypeDAO.saveBookType(bookType);
	}

	public void updateBookType(BookType bookType) throws SQLException,
			ReflectiveOperationException {
		bookTypeDAO.updateBookType(bookType);
	}

	public BookType getBookTypeById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return bookTypeDAO.getBookTypeById(id);
	}

	public BookType getBookTypeByName(String name) throws SQLException,
			ReflectiveOperationException {
		return bookTypeDAO.getBookTypeByName(name);
	}

}
