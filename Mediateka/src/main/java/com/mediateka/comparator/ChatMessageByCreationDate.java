package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.ChatMessage;

public class ChatMessageByCreationDate implements Comparator<ChatMessage> {

	@Override
	public int compare(ChatMessage chatMessage1, ChatMessage chatMessage2) {
		
		return chatMessage2.getCreationDate().compareTo(chatMessage1.getCreationDate());
	}
	

}
