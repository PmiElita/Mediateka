package com.mediateka.dao;

import static com.mediateka.dao.statement.ClubEventMemberStatements.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.ClubEventMember;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ClubEventMemberDAO {

	public static void saveClubEventMember(ClubEventMember clubEventMember)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_CLUB_EVENT_MEMBER);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					INSERT_CLUB_EVENT_MEMBER_ORDER);
			statement.executeUpdate();
		}
	}

	public static void updateClubEventMember(ClubEventMember clubEventMember)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_CLUB_EVENT_MEMBER_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					UPDATE_CLUB_EVENT_MEMBER_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

	public static ClubEventMember getClubEventMemberById(Integer id)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CLUB_EVENT_MEMBER_BY_ID);
			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setId(id);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					SELECT_CLUB_EVENT_MEMBER_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					ClubEventMember.class);
		}
	}

	public static List<ClubEventMember> getClubEventMemberByUserId(
			Integer userId) throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CLUB_EVENT_MEMBER_BY_USER_ID);
			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setUserId(userId);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					SELECT_CLUB_EVENT_MEMBER_BY_USER_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ClubEventMember.class);
		}
	}

	public static List<ClubEventMember> getClubEventMemberByClubId(
			Integer clubId) throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CLUB_EVENT_MEMBER_BY_CLUB_ID);
			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setClubId(clubId);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					SELECT_CLUB_EVENT_MEMBER_BY_CLUB_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ClubEventMember.class);
		}
	}

	public static List<ClubEventMember> getClubEventMemberByEventId(
			Integer eventId) throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CLUB_EVENT_MEMBER_BY_EVENT_ID);
			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(eventId);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					SELECT_CLUB_EVENT_MEMBER_BY_EVENT_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ClubEventMember.class);
		}
	}

	public static List<ClubEventMember> getClubEventMemberByState(State state)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CLUB_EVENT_MEMBER_BY_STATE);
			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setState(state);
			Transformer.valueIntoPreparedStatement(statement, clubEventMember,
					SELECT_CLUB_EVENT_MEMBER_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ClubEventMember.class);
		}
	}

	public static List<ClubEventMember> getClubEventMemberAll()
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CLUB_EVENT_MEMBER_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ClubEventMember.class);
		}
	}
}