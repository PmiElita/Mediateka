package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.dao.statement.BookTypeStatements;
import com.mediateka.model.BookType;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookTypeDAO {

	public void saveBookType(BookType BookType)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookTypeStatements.INSERT_BOOK_TYPE);

			Transformer.valueIntoPreparedStatement(statement, BookType,
					BookTypeStatements.INSERT_BOOK_TYPE_ORDER);

			statement.executeUpdate();

		}
	}

	
	
	
	public void updateBookType(BookType BookType)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookTypeStatements.UPDATE_BOOK_TYPE);

			Transformer.valueIntoPreparedStatement(statement, BookType,
					BookTypeStatements.UPDATE_BOOK_TYPE_ORDER);

			statement.executeUpdate();

		}
	}

	
	
	public BookType getBookTypeById(Integer id)
			throws SQLException, ReflectiveOperationException {

		BookType BookType = new BookType();
		BookType.setId(id);
		
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookTypeStatements.SELECT_BOOK_TYPE_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, BookType,
					BookTypeStatements.SELECT_BOOK_TYPE_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			
			return Transformer.transformResultSetIntoObject(rs, BookType.class);

		}
	}

	
	
	
	
	public BookType getBookTypeByName(String name)
			throws SQLException, ReflectiveOperationException {

		BookType BookType = new BookType();
		BookType.setName(name);
		
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookTypeStatements.SELECT_BOOK_TYPE_BY_NAME);

			Transformer.valueIntoPreparedStatement(statement, BookType,
					BookTypeStatements.SELECT_BOOK_TYPE_BY_NAME_ORDER);

			ResultSet rs = statement.executeQuery();
			
			return Transformer.transformResultSetIntoObject(rs, BookType.class);

		}
	}

	
	
	
		
	
}
