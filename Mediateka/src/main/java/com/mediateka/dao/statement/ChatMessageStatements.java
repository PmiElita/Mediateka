package com.mediateka.dao.statement;

public class ChatMessageStatements {

	public static final String INSERT_CHAT_MESSAGE ="INSERT INTO chat_message"
			+ " (text,user_id,club_id,state,creation_date)VALUES(?,?,?,?,?);";
	public static final String[] INSERT_CHAT_MESSAGE_ORDER ={"text","user_id","club_id","state","creation_date"};
	
	public static final String UPDATE_CHAT_MESSAGE_BY_ID = "UPDATE chat_message SET "
			+ "text=?, user_id=?, club_id=?, state=?, creation_date WHERE id=?;";
	
	public static final String[] UPDATE_CHAT_MESSAGE_BY_ID_ORDER = {"text","user_id","club_id","state","creation_date","id"};
	
	public static final String SELECT_CHAT_MESSAGE_BY_CLUB_ID = "SELECT * FROM chat_message WHERE club_id=? ORDER BY id LIMIT ?,?";
}
