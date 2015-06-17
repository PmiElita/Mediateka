package com.mediateka.dao;

import com.mediateka.dao.statement.UserCardStatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.model.User;
import com.mediateka.model.UserCard;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class UserCardDAO {

	public static UserCard getUserCardByUserId(Integer id)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UserCardStatements.SELECT_USER_CARD_BY_USER_ID);

			User user = new User();
			user.setId(id);

			Transformer.valueIntoPreparedStatement(statement, user,
					UserCardStatements.SELECT_USER_CARD_BY_USER_ID_ORDER);
			
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					UserCard.class);
		}

	}
}
