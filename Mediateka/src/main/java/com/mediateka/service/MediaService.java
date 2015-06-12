package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.MediaDAO;
import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;

public class MediaService {

	public static void saveMedia(Media media) throws SQLException,
			ReflectiveOperationException {
		MediaDAO.saveMedia(media);
	}

	public static void updateMedia(Media media) throws SQLException,
			ReflectiveOperationException {
		MediaDAO.updateMedia(media);
	}

	public static Media getMediaById(Integer mediaId) throws SQLException,
			ReflectiveOperationException {
		return MediaDAO.getMediaById(mediaId);
	}

	public static List<Media> getMediaContentGroupId(Integer contentGroupId)
			throws SQLException, ReflectiveOperationException {
		return MediaDAO.getMediaContentGroupId(contentGroupId);
	}

	public static List<Media> getMediaByType(MediaType type)
			throws SQLException, ReflectiveOperationException {
		return MediaDAO.getMediaByType(type);
	}

	public static List<Media> getMediaByState(State state) throws SQLException,
			ReflectiveOperationException {
		return MediaDAO.getMediaByState(state);
	}

	public static List<Media> getMediaByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return MediaDAO.getMediaByNameRegex(name);
	}

	public static List<Media> getMediaAll() throws SQLException,
			ReflectiveOperationException {
		return MediaDAO.getMediaAll();
	}
}