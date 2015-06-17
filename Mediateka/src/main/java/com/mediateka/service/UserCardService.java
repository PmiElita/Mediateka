package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.UserCardDAO;
import com.mediateka.model.UserCard;

public class UserCardService {
	public static UserCard getUserCardByUserId(Integer userId)
			throws ReflectiveOperationException, SQLException {
		return UserCardDAO.getUserCardByUserId(userId);
	}


}
