package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.EventDAO;
import com.mediateka.model.Event;
import com.mediateka.model.enums.EventType;

public class EventService {

	public void saveEvent(Event event) throws SQLException,
			ReflectiveOperationException {
		EventDAO.saveEvent(event);
	}

	public Event getEventById(Integer eventId) throws SQLException,
			ReflectiveOperationException {
		return EventDAO.getEventById(eventId);
	}

	public List<Event> getEventByClubId(Integer eventClubId)
			throws SQLException, ReflectiveOperationException {
		return EventDAO.getEventByClubId(eventClubId);
	}

	public List<Event> getEventByNameRegex(String name) throws SQLException,
			ReflectiveOperationException {
		return EventDAO.getEventByNameRegex(name);
	}

	public List<Event> getEventByType(EventType type) throws SQLException,
			ReflectiveOperationException {
		return EventDAO.getEventByType(type);
	}

	public void updateEventById(Event event) throws SQLException,
			ReflectiveOperationException {
		EventDAO.updateEventById(event);
	}

	public void getEventAll() throws SQLException, ReflectiveOperationException {
		EventDAO.getEventAll();
	}
}