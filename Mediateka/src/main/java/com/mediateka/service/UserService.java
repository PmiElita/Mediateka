package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.UserDAO;
import com.mediateka.model.User;

public class UserService {

	public static void saveUser(User user) throws SQLException,
			ReflectiveOperationException {
		UserDAO.saveUser(user);
	}

	public static User getUserById(Integer userId)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserById(userId);
	}

	public static void updateUser(User user) throws SQLException,
			ReflectiveOperationException {
		UserDAO.updateUser(user);
	}
}
