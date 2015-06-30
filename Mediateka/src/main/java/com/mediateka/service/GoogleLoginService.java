package com.mediateka.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.mediateka.dao.GoogleLoginDAO;
import com.mediateka.model.User;

public class GoogleLoginService {
	private GoogleLoginDAO googleLoginDAO = new GoogleLoginDAO();

	public User getUserFromSocialNetwork(String token) throws IOException,
			ParseException {
		return googleLoginDAO.getUserFromSocialNetwork(token);
	}
}
