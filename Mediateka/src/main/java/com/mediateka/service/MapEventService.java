package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.MapEventDAO;
import com.mediateka.model.MapEvent;

public class MapEventService {

	public static void saveMapEvent(MapEvent mapEvent) throws SQLException,
			ReflectiveOperationException {
		MapEventDAO.saveMapEvent(mapEvent);
	}

	public static void updateMapEvent(MapEvent mapEvent) throws SQLException,
			ReflectiveOperationException {
		MapEventDAO.updateMapEvent(mapEvent);
	}

	public static List<MapEvent> getActiveMapEvents() throws SQLException,
			ReflectiveOperationException {
		return MapEventDAO.getActiveMapEvents();
	}
}
