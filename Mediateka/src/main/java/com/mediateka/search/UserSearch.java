package com.mediateka.search;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.User;
import com.mediateka.service.UserService;

public class UserSearch {
	public static List<User> getUserByRegexps(String[] regexps, int offset,
			int limit) throws SQLException, ReflectiveOperationException {
		List<User> users = null;
		switch (regexps.length) {
		case 1:
			users = UserService.getUsersByOneRegexp(regexps[0], offset, limit);
			break;
		case 2:
			users = UserService.getUsersByTwoRegexp(regexps[0], regexps[1],
					offset, limit);
			break;
		case 3:
			users = UserService.getUsersByThreeRegexp(regexps[0], regexps[1],
					regexps[2], offset, limit);
			break;
		}

		return users;
	}
}
