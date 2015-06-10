package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ClubDAO;
import com.mediateka.model.Club;

public class ClubService {


	public static void saveClub(Club book) throws SQLException,
			ReflectiveOperationException {
		ClubDAO.saveClub(book);
	}

	public static void updateClub(Club book) throws SQLException,
			ReflectiveOperationException {
		ClubDAO.updateClub(book);
	}

	public static Club getClubById(Integer bookId)
			throws ReflectiveOperationException, SQLException {
		return ClubDAO.getClubById(bookId);
	}

	public static List<Club> getClubByName(String name) throws SQLException,
			ReflectiveOperationException {
		return ClubDAO.getClubByName(name);
	}


	
}
