package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.statement.ClubStatements;
import com.mediateka.model.Club;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ClubDAO {

	public static void saveClub(Club Club) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.INSERT_CLUB);

			Transformer.valueIntoPreparedStatement(statement, Club,
					ClubStatements.INSERT_CLUB_ORDER);

			statement.executeUpdate();

		}
	}

	public static void updateClub(Club Club) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.UPDATE_CLUB);

			Transformer.valueIntoPreparedStatement(statement, Club,
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

	public static List<Club> getClubByName(String name) throws SQLException,
			ReflectiveOperationException {

		Club Club = new Club();
		Club.setName(name);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(ClubStatements.SELECT_CLUB_BY_NAME);

			Transformer.valueIntoPreparedStatement(statement, Club,
					ClubStatements.SELECT_CLUB_BY_NAME_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, Club.class);

		}
	}

}