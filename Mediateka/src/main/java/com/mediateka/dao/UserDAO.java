package com.mediateka.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.Profession;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;

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

	public static List<User> getUserByState(State state)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_STATE);
			User user = new User();
			user.setState(state);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUserByFormActivity(Boolean formActivity)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_FORM_ACTIVITY);
			User user = new User();
			user.setIsFormActive(formActivity);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_FORM_ACTIVITY_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUserByRole(Role role)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_ROLE);
			User user = new User();
			user.setRole(role);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_ROLE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUserByFormId(Integer formId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_FORM_ID);
			User user = new User();
			user.setFormId(formId);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_FORM_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUserByProfession(String professionName)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_PROFESSION);
			Profession profession = new Profession();
			profession.setName(professionName);
			Transformer.valueIntoPreparedStatement(statement, profession,
					SELECT_USER_BY_FORM_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUserByNationality(String nationality)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_NATIONALITY);
			User user = new User();
			user.setNationality(nationality);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_NATIONALITY_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static User getUserByToken(String token)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_BY_TOKEN);
			User user = new User();
			user.setPasswordChangingToken(token);
			Transformer.valueIntoPreparedStatement(statement, user,
					SELECT_USER_BY_TOKEN_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					User.class);
		}
	}

	public static List<User> getUserAll() throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUsersByOneRegexp(String regexp)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_GET_USERS_BY_ONE_REGEXP);
			statement.setString(1, regexp);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUsersByTwoRegexp(String firstRegexp,
			String secondRegexp) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_GET_USERS_BY_TWO_REGEXP);
			statement.setString(1, firstRegexp);
			statement.setString(2, secondRegexp);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}

	public static List<User> getUsersByThreeRegexp(String firstRegexp,
			String secondRegexp, String thirdRegexp) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_GET_USERS_BY_THREE_REGEXP);
			statement.setString(1, firstRegexp);
			statement.setString(2, secondRegexp);
			statement.setString(3, thirdRegexp);
			ResultSet resultSet = statement.executeQuery();
			return Transformer
					.transformResultSetIntoList(resultSet, User.class);
		}
	}
}