package com.mediateka.dao;

import static com.mediateka.dao.statement.EventStatements.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.mediateka.model.Event;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class EventDAO {

	public static void saveEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_EVENT);
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

	public static List<Event> getEventByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_EVENT_BY_NAME_REGEX);
			Event event = new Event();
			event.setName(name);
			Transformer.valueIntoPreparedStatement(statement, event,
					SELECT_EVENT_BY_NAME_REGEX_ORDER);
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

	public static List<Event> getEventByType(EventType type)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_EVENT_BY_TYPE);
			Event event = new Event();
			event.setType(type);
			Transformer.valueIntoPreparedStatement(statement, event,
					SELECT_EVENT_BY_TYPE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Event.class);
		}
	}

	public static List<Event> getEventByState(State state) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_EVENT_BY_STATE);
			Event event = new Event();
			event.setState(state);
			Transformer.valueIntoPreparedStatement(statement, event,
					SELECT_EVENT_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Event.class);
		}
	}

	public static List<Event> getEventAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_EVENT_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Event.class);
		}
	}

	public static List<Event> getEventsByDate(Timestamp date)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_GET_EVENTS_BY_DATE);
			statement.setTimestamp(1, date);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Event.class);
		}
	}

	public static Event callSaveEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_INSERT_EVENT);
			Transformer.valueIntoPreparedStatement(statement, event,
					CALL_INSERT_EVENT_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Event.class);
		}
	}
}