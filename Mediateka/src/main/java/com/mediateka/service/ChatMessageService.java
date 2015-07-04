package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ChatMessageDAO;
import com.mediateka.model.ChatMessage;

public class ChatMessageService {
	public static void saveChatMessage(ChatMessage chatMessage)
			throws ReflectiveOperationException, SQLException {
		ChatMessageDAO.saveChatMessage(chatMessage);
	}

	public static void updateChatMessage(ChatMessage chatMessage)
			throws ReflectiveOperationException, SQLException {
		ChatMessageDAO.updateChatMessage(chatMessage);
	}

	public static List<ChatMessage> getChatMessageByClubId(Integer clubId,
			Integer firstIndex, Integer count) throws SQLException,
			ReflectiveOperationException {
		return ChatMessageDAO.getChatMessageByClubId(clubId, firstIndex, count);
	}
}
