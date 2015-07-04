package com.mediateka.dao;

import static com.mediateka.dao.statement.ChatMessageStatements.INSERT_CHAT_MESSAGE;
import static com.mediateka.dao.statement.ChatMessageStatements.INSERT_CHAT_MESSAGE_ORDER;
import static com.mediateka.dao.statement.ChatMessageStatements.SELECT_CHAT_MESSAGE_BY_CLUB_ID;
import static com.mediateka.dao.statement.ChatMessageStatements.UPDATE_CHAT_MESSAGE_BY_ID;
import static com.mediateka.dao.statement.ChatMessageStatements.UPDATE_CHAT_MESSAGE_BY_ID_ORDER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mediateka.model.ChatMessage;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ChatMessageDAO {

	public static void saveChatMessage(ChatMessage chatMessage)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_CHAT_MESSAGE);
			Transformer.valueIntoPreparedStatement(statement, chatMessage,
					INSERT_CHAT_MESSAGE_ORDER);
			statement.executeUpdate();

		}
	}

	public static void updateChatMessage(ChatMessage chatMessage)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_CHAT_MESSAGE_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, chatMessage,
					UPDATE_CHAT_MESSAGE_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

	public static List<ChatMessage> getChatMessageByClubId(Integer clubId, Integer firstIndex,Integer count)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CHAT_MESSAGE_BY_CLUB_ID);
			statement.setInt(1, clubId);
			statement.setInt(2, firstIndex);
			statement.setInt(3, count);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ChatMessage.class);
		}
	}
}
