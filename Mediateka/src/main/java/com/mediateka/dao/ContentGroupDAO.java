package com.mediateka.dao;

import static com.mediateka.dao.statement.ContentGroupStatements.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import com.mediateka.dao.statement.ContentGroupStatements;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ContentGroupDAO {
	// insert
	public static void saveContentGroup(ContentGroup contentGroup)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_CONTENT_GROUP);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					INSERT_CONTENT_GROUP_ORDER);
			statement.executeUpdate();
		}
	}

	public static ContentGroup callSaveContentGroup(ContentGroup contentGroup)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(ContentGroupStatements.CALL_INSERT_CONTENT_GROUP);

			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					ContentGroupStatements.CALL_INSERT_CONTENT_GROUP_ORDER);

			ResultSet resultSet = statement.executeQuery();

			return Transformer.transformResultSetIntoObject(resultSet,
					ContentGroup.class);
		}
	}

	// select
	public static ContentGroup getContentGroupById(Integer contentGroupId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_ID);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setId(contentGroupId);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByType(ContentGroupType type)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_TYPE);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setType(type);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_TYPE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByState(State state)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_STATE);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setState(state);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByEventId(Integer eventId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_EVENT_ID);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setEventId(eventId);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_EVENT_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByClubId(Integer clubId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_CLUB_ID);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setClubId(clubId);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_CLUB_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByParentId(Integer parentId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_PARENT_ID);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setParentId(parentId);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_PARENT_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByCreatorId(
			Integer creatorId) throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_CREATOR_ID);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setCreatorId(creatorId);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_CREATOR_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByNameRegexId(
			String nameRegex) throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_NAME_REGEX);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setName(nameRegex);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_NAME_REGEX_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupAll()
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByClubIdAndState(
			Integer clubId, State state) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_CLUB_ID_AND_STATE);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setClubId(clubId);
			contentGroup.setState(state);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_CLUB_ID_AND_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByEventIdAndState(
			Integer eventId, State state) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_EVENT_ID_AND_STATE);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setEventId(eventId);
			contentGroup.setState(state);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_EVENT_ID_AND_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByClubIdAndStateAndType(
			Integer clubId, State state, ContentGroupType contentGroupType)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_CLUB_ID_AND_STATE_AND_TYPE);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setClubId(clubId);
			contentGroup.setState(state);
			contentGroup.setType(contentGroupType);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_CLUB_ID_AND_STATE_AND_TYPE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByEventIdAndStateAndType(
			Integer eventId, State state, ContentGroupType contentGroupType)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_EVENT_ID_AND_STATE_AND_TYPE);
			ContentGroup contentGroup = new ContentGroup();
			contentGroup.setEventId(eventId);
			contentGroup.setState(state);
			contentGroup.setType(contentGroupType);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					SELECT_CONTENT_GROUP_BY_EVENT_ID_AND_STATE_AND_TYPE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	// update
	public static void updateContentGroup(ContentGroup contentGroup)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_CONTENT_GROUP);
			Transformer.valueIntoPreparedStatement(statement, contentGroup,
					UPDATE_CONTENT_GROUP_ORDER);
			statement.executeUpdate();
		}
	}

	public static List<ContentGroup> getContentGroupByClubIdAndStateAndTypeLimited(
			Integer clubId, State state, ContentGroupType contentGroupType,
			Integer offset, Integer limit) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_CLUB_ID_AND_STATE_AND_TYPE_LIMITED);
			statement.setInt(1, clubId);
			statement.setString(2, state.toString());
			statement.setString(3, contentGroupType.toString());
			statement.setInt(4, offset);
			statement.setInt(5, limit);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

	public static List<ContentGroup> getContentGroupByEventIdAndStateAndTypeLimited(
			Integer eventId, State state, ContentGroupType contentGroupType,
			Integer offset, Integer limit) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CONTENT_GROUP_BY_EVENT_ID_AND_STATE_AND_TYPE_LIMITED);
			statement.setInt(1, eventId);
			statement.setString(2, state.toString());
			statement.setString(3, contentGroupType.toString());
			statement.setInt(4, offset);
			statement.setInt(5, limit);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ContentGroup.class);
		}
	}

}