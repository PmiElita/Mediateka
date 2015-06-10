package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.MediaDAO;
import com.mediateka.model.Media;

public class MediaService {

	public void saveMedia(Media media) throws SQLException,
			ReflectiveOperationException {
		MediaDAO.saveMedia(media);
	}

	public void updateMedia(Media media) throws SQLException,
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
}
