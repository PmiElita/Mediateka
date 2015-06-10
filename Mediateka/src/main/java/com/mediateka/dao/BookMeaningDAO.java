package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.dao.statement.BookMeaningStatements;
import com.mediateka.model.BookMeaning;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class BookMeaningDAO {

	public void saveBookMeaning(BookMeaning bookMeaning)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.INSERT_BOOK_MEANING);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.INSERT_BOOK_MEANING_ORDER);

			statement.executeUpdate();

		}
	}

	
	
	
	public void updateBookMeaning(BookMeaning bookMeaning)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.UPDATE_BOOK_MEANING);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.UPDATE_BOOK_MEANING_ORDER);

			statement.executeUpdate();

		}
	}

	
	
	public BookMeaning getBookMeaningById(Integer id)
			throws SQLException, ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setId(id);
		
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.SELECT_BOOK_MEANING_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.SELECT_BOOK_MEANING_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			
			return Transformer.transformResultSetIntoObject(rs, BookMeaning.class);

		}
	}

	
	
	
	
	public BookMeaning getBookMeaningByName(String name)
			throws SQLException, ReflectiveOperationException {

		BookMeaning bookMeaning = new BookMeaning();
		bookMeaning.setName(name);
		
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(BookMeaningStatements.SELECT_BOOK_MEANING_BY_NAME);

			Transformer.valueIntoPreparedStatement(statement, bookMeaning,
					BookMeaningStatements.SELECT_BOOK_MEANING_BY_NAME_ORDER);

			ResultSet rs = statement.executeQuery();
			
			return Transformer.transformResultSetIntoObject(rs, BookMeaning.class);

		}
	}

	
	
	
		
	
}
