package com.mediateka.dao;

import static com.mediateka.dao.statement.ChatMessageStatements.*;

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

	public static List<ChatMessage> getChatMessageByClubId(Integer clubId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_CHAT_MESSAGE_BY_CLUB_ID);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setClubId(clubId);
			Transformer.valueIntoPreparedStatement(statement, chatMessage,
					SELECT_CHAT_MESSAGE_BY_CLUB_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					ChatMessage.class);
		}
	}
}
