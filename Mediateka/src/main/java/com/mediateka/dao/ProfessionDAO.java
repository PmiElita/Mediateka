package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.mediateka.dao.statement.ProfessionStatements.*;

import com.mediateka.model.Profession;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ProfessionDAO {
	public static void saveProfession(Profession profession)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_PROFESSION);
			Transformer.valueIntoPreparedStatement(statement, profession,
					INSERT_PROFESSION_ORDER);
			statement.executeUpdate();
		}
	}

	public static void updateProfession(Profession profession)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_PROFESSION_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, profession,
					UPDATE_PROFESSION_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

	public static Profession getProfessionById(Integer professionId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_PROFESSION_BY_ID);
			Profession profession = new Profession();
			profession.setId(professionId);
			Transformer.valueIntoPreparedStatement(statement, profession,
					SELECT_PROFESSION_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Profession.class);
		}
	}

	public static List<Profession> getProfessionByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_PROFESSION_BY_NAME_REGEX);
			Profession profession = new Profession();
			profession.setName(name);
			Transformer.valueIntoPreparedStatement(statement, profession,
					SELECT_PROFESSION_BY_NAME_REGEX_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Profession.class);
		}
	}

	public static List<Profession> getProfessionByState(State state)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_PROFESSION_BY_STATE);
			Profession profession = new Profession();
			profession.setState(state);
			Transformer.valueIntoPreparedStatement(statement, profession,
					SELECT_PROFESSION_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Profession.class);
		}
	}

	public static List<Profession> getProfessionAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_PROFESSION_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Profession.class);
		}
	}
}