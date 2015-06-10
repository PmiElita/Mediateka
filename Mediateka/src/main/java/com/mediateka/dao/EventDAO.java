package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.mediateka.dao.statement.EventStatements.*;

import com.mediateka.model.Event;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class EventDAO {

	public static void saveEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSER_EVENT);
			Transformer.valueIntoPreparedStatement(statement, event,
					INSERT_EVENT_ORDER);
			statement.executeUpdate();
		}
	}

	public static Event getEventById(Integer eventId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_EVENT_BY_ID);
			Event event = new Event();
			event.setId(eventId);
			Transformer.valueIntoPreparedStatement(statement, event,
					SELECT_EVENT_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Event.class);
		}
	}

	public static List<Event> getEventByClubId(Integer eventClubId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_EVENT_BY_CLUB_ID);
			Event event = new Event();
			event.setId(eventClubId);
			Transformer.valueIntoPreparedStatement(statement, event,
					SELECT_EVENT_BY_CLUB_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Event.class);
		}
	}

	public static void updateEventById(Event event) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_EVENT_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, event,
					UPDATE_EVENT_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

}
