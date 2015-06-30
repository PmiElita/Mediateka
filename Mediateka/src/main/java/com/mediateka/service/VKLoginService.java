package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.VKLoginDAO;
import com.mediateka.model.User;

public class VKLoginService {
	private VKLoginDAO vkLoginDAO = new VKLoginDAO();

	public void getAccessToken(String code) {
		vkLoginDAO.getAccessToken(code);
	}

	public boolean isRegistrated() throws SQLException,
			ReflectiveOperationException {
		return vkLoginDAO.isRegistrated();
	}

	public User getUserFromSocNet() {
		return vkLoginDAO.getUserFromSocNet();
	}

	public User getUserForLogIn() throws SQLException,
			ReflectiveOperationException {
		return vkLoginDAO.getUserForLogIn();
	}
}
