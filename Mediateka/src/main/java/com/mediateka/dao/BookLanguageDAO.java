package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.dao.statement.BookLanguageStatements;
import com.mediateka.model.BookLanguage;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookLanguageDAO {

	public void saveBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.INSERT_BOOK_LANGUAGE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.INSERT_BOOK_LANGUAGE_ORDER);

			statement.executeUpdate();

		}
	}

	
	
	
	public void updateBookLanguage(BookLanguage bookLanguage)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.UPDATE_BOOK_LANGUAGE);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.UPDATE_BOOK_LANGUAGE_ORDER);

			statement.executeUpdate();

		}
	}

	
	
	public BookLanguage getBookLanguageById(Integer id)
			throws SQLException, ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setId(id);
		
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			
			return Transformer.transformResultSetIntoObject(rs, BookLanguage.class);

		}
	}

	
	
	
	
	public BookLanguage getBookLanguageByName(String name)
			throws SQLException, ReflectiveOperationException {

		BookLanguage bookLanguage = new BookLanguage();
		bookLanguage.setName(name);
		
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_NAME);

			Transformer.valueIntoPreparedStatement(statement, bookLanguage,
					BookLanguageStatements.SELECT_BOOK_LANGUAGE_BY_NAME_ORDER);

			ResultSet rs = statement.executeQuery();
			
			return Transformer.transformResultSetIntoObject(rs, BookLanguage.class);

		}
	}

	
	
	
		
	
}
