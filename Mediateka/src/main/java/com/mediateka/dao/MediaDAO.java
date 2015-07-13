package com.mediateka.dao;


import static com.mediateka.dao.statement.MediaStatements.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class MediaDAO {

	public static Media callSaveMedia(Media media) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_INSERT_MEDIA);

			Transformer.valueIntoPreparedStatement(statement, media,
					CALL_INSERT_MEDIA_ORDER);

			ResultSet resultSet = statement.executeQuery();

			return Transformer.transformResultSetIntoObject(resultSet,
					Media.class);
		}
	}

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

	public static Media getMediaByPath(String path) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_PATH);
			Media media = new Media();
			media.setPath(path);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_PATH_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Media.class);
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

	public static List<Media> getMediaByType(MediaType type)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_TYPE);
			Media media = new Media();
			media.setType(type);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_TYPE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}

	public static List<Media> getMediaByState(State state) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_STATE);
			Media media = new Media();
			media.setState(state);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}

	public static List<Media> getMediaByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_NAME_REGEX);
			Media media = new Media();
			media.setName(name);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_NAME_REGEX_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}

	public static List<Media> getMediaByPathRegex(String path)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_PATH_REGEX);
			Media media = new Media();
			media.setPath(path);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_PATH_REGEX_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}

	public static List<Media> getMediaAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}

	public static Media getFirstMediaByContentGroupId(Integer contentGroupId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FRIST_MEDIA_BY_CONTENT_GROUP_ID);
			Media media = new Media();
			media.setContentGroupId(contentGroupId);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_FRIST_MEDIA_BY_CONTENT_GROUP_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Media.class);
		}
	}

	public static Integer getMediaCountByContentGroupId(Integer contentGroupId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_COUNT_BY_CONTENT_GROUP_ID);
			Media media = new Media();
			media.setContentGroupId(contentGroupId);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_COUNT_BY_CONTENT_GROUP_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		}
	}

	public static Integer getMediaCountByContentGroupIdAndType(
			Integer contentGroupId, MediaType type)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_COUNT_BY_CONTENT_GROUP_ID_AND_TYPE);
			Media media = new Media();
			media.setContentGroupId(contentGroupId);
			media.setType(type);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_COUNT_BY_CONTENT_GROUP_ID_AND_TYPE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		}
	}
	
	
	public static List<Media> getMediaContentGroupIdAndState(Integer contentGroupId, State state)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_MEDIA_BY_CONTENT_GROUP_ID_AND_STATE);
			Media media = new Media();
			media.setContentGroupId(contentGroupId);
			media.setState(state);
			Transformer.valueIntoPreparedStatement(statement, media,
					SELECT_MEDIA_BY_CONTENT_GROUP_ID_AND_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					Media.class);
		}
	}
	
}