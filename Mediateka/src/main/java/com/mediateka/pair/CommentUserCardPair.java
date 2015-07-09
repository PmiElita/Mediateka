package com.mediateka.pair;

import com.mediateka.model.ContentGroup;
import com.mediateka.model.UserCard;

public class CommentUserCardPair {
	private ContentGroup comment;
	private UserCard userCard;

	public CommentUserCardPair(ContentGroup comment, UserCard userCard) {
		this.comment = comment;
		this.userCard = userCard;
	}

	public ContentGroup getComment() {
		return comment;
	}

	public void setComment(ContentGroup comment) {
		this.comment = comment;
	}

	public UserCard getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCard userCard) {
		this.userCard = userCard;
	}

}
