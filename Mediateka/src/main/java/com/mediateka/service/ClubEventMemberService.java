package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ClubEventMemberDAO;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.enums.State;

public class ClubEventMemberService {

	public static void saveClubEventMember(ClubEventMember clubEventMember)
			throws SQLException, ReflectiveOperationException {
		ClubEventMemberDAO.saveClubEventMember(clubEventMember);
	}

	public static void updateClubEventMember(ClubEventMember clubEventMember)
			throws SQLException, ReflectiveOperationException {
		ClubEventMemberDAO.updateClubEventMember(clubEventMember);
	}

	public static ClubEventMember getClubEventMemberById(Integer id)
			throws SQLException, ReflectiveOperationException {
		return ClubEventMemberDAO.getClubEventMemberById(id);
	}

	public static List<ClubEventMember> getClubEventMemberByUserId(
			Integer userId) throws SQLException, ReflectiveOperationException {
		return ClubEventMemberDAO.getClubEventMemberByUserId(userId);
	}

	public static List<ClubEventMember> getClubEventMemberByClubId(
			Integer clubId) throws SQLException, ReflectiveOperationException {
		return ClubEventMemberDAO.getClubEventMemberByClubId(clubId);
	}

	public static List<ClubEventMember> getClubEventMemberByEventId(
			Integer eventId) throws SQLException, ReflectiveOperationException {
		return ClubEventMemberDAO.getClubEventMemberByEventId(eventId);
	}

	public static List<ClubEventMember> getClubEventMemberByState(State state)
			throws SQLException, ReflectiveOperationException {
		return ClubEventMemberDAO.getClubEventMemberByState(state);
	}

	public static List<ClubEventMember> getClubEventMemberAll()
			throws SQLException, ReflectiveOperationException {
		return ClubEventMemberDAO.getClubEventMemberAll();
	}

	public static ClubEventMember getClubEventMemberByUserIdAndClubId(
			Integer userId, Integer clubId)
			throws ReflectiveOperationException, SQLException {
		return ClubEventMemberDAO.getClubEventMemberByUserIdAndClubId(userId,
				clubId);
	}
}