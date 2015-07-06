package com.mediateka.pair;

import com.mediateka.model.ChatMessage;
import com.mediateka.model.UserCard;

public class ChatMessageUserCardPair {

	private ChatMessage chatMessage;
	private UserCard userCard;

	public ChatMessageUserCardPair(ChatMessage chatMessage, UserCard userCard) {
		this.chatMessage = chatMessage;
		this.userCard = userCard;
	}

	public ChatMessage getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(ChatMessage chatMessage) {
		this.chatMessage = chatMessage;
	}

	public UserCard getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCard userCard) {
		this.userCard = userCard;
	}

}
