package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.model.User;

import static com.mediateka.dao.statement.UserStatements.*;

import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class UserDAO {
	public static void saveUser(User user) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_USER);
			Transformer.valueIntoPreparedStatement(statement, user,
					INSERT_USER_ORDER);
			statement.executeUpdate();
		}
	}

	public static User getUserById(Integer userId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_ID);
			User user = new User();
			user.setId(userId);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					User.class);
		}
	}

	public static void updateUser(User user) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_USER_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, user,
					UPDATE_USER_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

	public static User getUserByEmail(String email)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_EMAIL);
			User user = new User();
			user.setEmail(email);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_EMAIL_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					User.class);
		}
	}
}
