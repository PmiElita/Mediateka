package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.UserDAO;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;

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

	public static User getUserByEmail(String email)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByEmail(email);
	}

	public static List<User> getUserByState(State state)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByState(state);
	}

	public static List<User> getUserByFormActivity(Boolean formActivity)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByFormActivity(formActivity);
	}

	public static List<User> getUserByRole(Role role)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByRole(role);
	}

	public static List<User> getUserByFormId(Integer formId)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByFormId(formId);
	}

	public static List<User> getUserByProfession(String profession)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByProfession(profession);
	}

	public static List<User> getUserByNationality(String nationality)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByNationality(nationality);
	}

	public static User getUserByToken(String token)
			throws ReflectiveOperationException, SQLException {
		return UserDAO.getUserByToken(token);
	}

	public static List<User> getUserAll() throws ReflectiveOperationException,
			SQLException {
		return UserDAO.getUserAll();
	}

	public static List<User> getUsersByOneRegexp(String regexp, int offset,
			int limit) throws SQLException, ReflectiveOperationException {
		return UserDAO.getUsersByOneRegexp(regexp, offset, limit);
	}

	public static List<User> getUsersByTwoRegexp(String firstRegexp,
			String secondRegexp, int offset, int limit) throws SQLException,
			ReflectiveOperationException {
		return UserDAO.getUsersByTwoRegexp(firstRegexp, secondRegexp, offset,
				limit);
	}

	public static List<User> getUsersByThreeRegexp(String firstRegexp,
			String secondRegexp, String thirdRegexp, int offset, int limit)
			throws SQLException, ReflectiveOperationException {
		return UserDAO.getUsersByThreeRegexp(firstRegexp, secondRegexp,
				thirdRegexp, offset, limit);
	}

	public static User getUserBySocialId(String socialId) throws SQLException,
			ReflectiveOperationException {
		return UserDAO.getUserBySocialId(socialId);
	}

	public static List<User> getUsersLimited(int offset, int limit)
			throws SQLException, ReflectiveOperationException {
		return UserDAO.getUsersByStateLimited(offset, limit);
	}
}