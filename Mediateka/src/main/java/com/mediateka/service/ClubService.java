package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ClubDAO;
import com.mediateka.model.Club;
import com.mediateka.model.enums.State;

public class ClubService {

	public static void saveClub(Club club) throws SQLException,
			ReflectiveOperationException {
		ClubDAO.saveClub(club);
	}

	public static Club callSaveClub(Club club) throws SQLException,
			ReflectiveOperationException {
		return ClubDAO.callSaveClub(club);
	}

	public static void updateClub(Club club) throws SQLException,
			ReflectiveOperationException {
		ClubDAO.updateClub(club);
	}

	public static Club getClubById(Integer clubId)
			throws ReflectiveOperationException, SQLException {
		return ClubDAO.getClubById(clubId);
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

	public static Integer getNumberOfRequestedClubs() throws SQLException {
		return ClubDAO.getNumberOfRequestedClubs();
	}

	public static List<Club> getAllNotDeletedClubs() throws SQLException,
			ReflectiveOperationException {
		return ClubDAO.getAllNotDeletedClubs();
	}
}