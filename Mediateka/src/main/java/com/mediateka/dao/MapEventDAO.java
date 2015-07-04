package com.mediateka.dao;

import static com.mediateka.dao.statement.MapEventStatements.INSERT_MAP_EVENT;
import static com.mediateka.dao.statement.MapEventStatements.INSERT_MAP_EVENT_ORDER;
import static com.mediateka.dao.statement.MapEventStatements.SELECT_ALL_ACTIVE_MAP_EVENTS;
import static com.mediateka.dao.statement.MapEventStatements.UPDATE_MAP_EVENT;
import static com.mediateka.dao.statement.MapEventStatements.UPDATE_MAP_EVENT_ORDER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.MapEvent;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class MapEventDAO {

	public static void saveMapEvent(MapEvent mapEvent) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_MAP_EVENT);
			Transformer.valueIntoPreparedStatement(statement, mapEvent,
					INSERT_MAP_EVENT_ORDER);
			statement.executeUpdate();
		}
	}

	public static void updateMapEvent(MapEvent mapEvent) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_MAP_EVENT);
			Transformer.valueIntoPreparedStatement(statement, mapEvent,
					UPDATE_MAP_EVENT_ORDER);
			statement.executeUpdate();
		}
	}

	public static List<MapEvent> getActiveMapEvents() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_ALL_ACTIVE_MAP_EVENTS);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					MapEvent.class);
		}
	}
}
