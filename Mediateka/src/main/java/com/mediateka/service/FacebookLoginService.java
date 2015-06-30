package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.FacebookLoginDAO;
import com.mediateka.model.User;

public class FacebookLoginService {
	private FacebookLoginDAO facebookLoginDAO = new FacebookLoginDAO();

	public void getAccessToken(String code) {
		facebookLoginDAO.getAccessToken(code);
	}

	public User getUserFromSocNet() {
		return facebookLoginDAO.getUserFromSocNet();
	}

	public User getUserForLogIn() throws SQLException,
			ReflectiveOperationException {
		return facebookLoginDAO.getUserForLogIn();
	}

	public boolean isRegistrated() throws SQLException,
			ReflectiveOperationException {
		return facebookLoginDAO.isRegistrated();
	}
}
