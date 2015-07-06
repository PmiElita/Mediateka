package com.mediateka.dao.statement;

public class UserCardStatements {

	public static final String SELECT_USER_CARD_BY_USER_ID = 
			"SELECT first_name, last_name, middle_name, email, path "
			+ "FROM user, media "
			+ "WHERE user.id=? AND ava_id = media.id";
			
	public static final String[] SELECT_USER_CARD_BY_USER_ID_ORDER = { "id" };


}
