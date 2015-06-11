package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ClubDAO;
import com.mediateka.model.Club;
import com.mediateka.model.enums.State;

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

	public static List<Club> getClubByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return ClubDAO.getClubByNameRegex(name);
	}

	public static List<Club> getClubByState(State state) throws SQLException,
			ReflectiveOperationException {
		return ClubDAO.getClubByState(state);
	}

	public static List<Club> getClubAll() throws SQLException,
			ReflectiveOperationException {
		return ClubDAO.getClubAll();
	}
}