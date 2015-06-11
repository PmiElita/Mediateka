package com.mediateka.dao;

import static com.mediateka.dao.statement.MediaStatements.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.Media;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class MediaDAO {

	public static void saveMedia(Media media) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_MEDIA);
			Transformer.valueIntoPreparedStatement(statement, media,
					INSERT_MEDIA_ORDER);
			statement.executeUpdate();
		}
	}

	public static void updateMedia(Media media) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_MEDIA_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, media,
					UPDATE_MEDIA_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

	public static Media getMediaById(Integer mediaId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_ID);
			Media media = new Media();
			media.setId(mediaId);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Media.class);
		}
	}

	public static List<Media> getMediaContentGroupId(Integer contentGroupId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_CONTENT_GROUP_ID);
			Media media = new Media();
			media.setContentGroupId(contentGroupId);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_CONTENT_GROUP_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}

}