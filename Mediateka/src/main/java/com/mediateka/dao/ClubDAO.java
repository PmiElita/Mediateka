package com.mediateka.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.statement.ClubStatements;
import com.mediateka.model.Club;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ClubDAO {

	public static void saveClub(Club club) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.INSERT_CLUB);

			Transformer.valueIntoPreparedStatement(statement, club,
					ClubStatements.INSERT_CLUB_ORDER);

			statement.executeUpdate();

		}
	}

	public static Club callSaveClub(Club club) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(ClubStatements.CALL_INSERT_CLUB);

			Transformer.valueIntoPreparedStatement(statement, club,
					ClubStatements.CALL_INSERT_CLUB_ORDER);

			ResultSet resultSet = statement.executeQuery();

			return Transformer.transformResultSetIntoObject(resultSet,
					Club.class);
		}
	}

	public static void updateClub(Club club) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.UPDATE_CLUB);

			Transformer.valueIntoPreparedStatement(statement, club,
					ClubStatements.UPDATE_CLUB_ORDER);

			statement.executeUpdate();

		}
	}

	public static Club getClubById(Integer id) throws SQLException,
			ReflectiveOperationException {

		Club Club = new Club();
		Club.setId(id);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_CLUB_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, Club,
					ClubStatements.SELECT_CLUB_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs, Club.class);

		}
	}

	public static List<Club> getClubByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {

		Club Club = new Club();
		Club.setName(name);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_CLUB_BY_NAME_REGEX);

			Transformer.valueIntoPreparedStatement(statement, Club,
					ClubStatements.SELECT_CLUB_BY_NAME_REGEX_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Club.class);

		}
	}

	public static List<Club> getClubByState(State state) throws SQLException,
			ReflectiveOperationException {

		Club Club = new Club();
		Club.setState(state);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_CLUB_BY_STATE);

			Transformer.valueIntoPreparedStatement(statement, Club,
					ClubStatements.SELECT_CLUB_BY_STATE_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Club.class);

		}
	}

	public static List<Club> getClubAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_CLUB_ALL);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Club.class);

		}
	}

	public static Integer getNumberOfRequestedClubs() throws SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_COUNT_OF_REQUESTED_CLUBS);

			ResultSet rs = statement.executeQuery();
			rs.next();
			return rs.getInt(1);
		}

	}

	public static List<Club> getAllNotDeletedClubs() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_ALL_NOT_DELETED_CLUBS);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Club.class);

		}
	}
}