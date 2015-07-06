package com.mediateka.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.mediateka.dao.EventDAO;
import com.mediateka.model.Event;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.State;

public class EventService {

	public static void saveEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		EventDAO.saveEvent(event);
	}

	public static Event getEventById(Integer eventId) throws SQLException,
			ReflectiveOperationException {
		return EventDAO.getEventById(eventId);
	}

	public static List<Event> getEventByClubId(Integer eventClubId)
			throws SQLException, ReflectiveOperationException {
		return EventDAO.getEventByClubId(eventClubId);
	}

	public static List<Event> getEventByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return EventDAO.getEventByNameRegex(name);
	}

	public static List<Event> getEventByType(EventType type)
			throws SQLException, ReflectiveOperationException {
		return EventDAO.getEventByType(type);
	}

	public static List<Event> getEventByState(State state) throws SQLException,
			ReflectiveOperationException {
		return EventDAO.getEventByState(state);
	}

	public static void updateEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		EventDAO.updateEventById(event);
	}

	public static List<Event> getEventAll() throws SQLException,
			ReflectiveOperationException {
		return EventDAO.getEventAll();
	}

	public static List<Event> getEventsByDate(Timestamp date)
			throws SQLException, ReflectiveOperationException {
		return EventDAO.getEventsByDate(date);
	}

	public static Event callSaveEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		return EventDAO.callSaveEvent(event);
	}
	
	public static Integer getNumberOfRequestedEvents() throws SQLException {
		return EventDAO.getNumberOfRequestedEvents();
	}

}