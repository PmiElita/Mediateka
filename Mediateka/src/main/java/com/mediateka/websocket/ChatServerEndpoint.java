package com.mediateka.websocket;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.mediateka.model.ChatMessage;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ChatMessageService;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ClubService;
import com.mediateka.service.UserService;

@ServerEndpoint(value = "/chat")
public class ChatServerEndpoint {
	private static Logger logger = Logger.getLogger(ChatServerEndpoint.class);
	private static Map<Integer,Set<Session>> clubChats; 
static{
	try {
	 clubChats = Collections
				.synchronizedMap(new HashMap<Integer,Set<Session>>());
	 List<Club> clubs = ClubService.getClubAll();
	 for (Club club : clubs){
		 if (club.getState().equals(State.ACTIVE)){
			 clubChats.put(club.getId(),Collections
						.synchronizedSet(new HashSet<Session>()));
		 }
	 }
	}catch (Exception e){
		logger.warn("exception in clubChats initialization",e);
	}
}
	/**
	 * Callback hook for Connection open events.
	 * 
	 * This method will be invoked when a client requests for a WebSocket
	 * connection.
	 * 
	 * @param userSession
	 *            the userSession which is opened.
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		Integer clubId=Integer.parseInt(userSession.getRequestParameterMap().get("clubId").get(0));
		clubChats.get(clubId).add(userSession);
		userSession.getAsyncRemote().sendText("");
	}

	/**
	 * Callback hook for Connection close events.
	 * 
	 * This method will be invoked when a client closes a WebSocket connection.
	 * 
	 * @param userSession
	 *            the userSession which is opened.
	 */
	@OnClose
	public void onClose(Session userSession) {
		Integer clubId=Integer.parseInt(userSession.getRequestParameterMap().get("clubId").get(0));
		System.out.println(clubId);
		clubChats.get(clubId).remove(userSession);
	}

	/**
	 * Callback hook for Message Events.
	 * 
	 * This method will be invoked when a client send a message.
	 * 
	 * @param message
	 *            The text message
	 * @param userSession
	 *            The session of the client
	 * @throws ParseException
	 */
	@OnMessage
	public void onMessage(String message, Session userSession) {
		
		try {
			JSONObject jsonMessage = (JSONObject) new JSONParser()
					.parse(message);
			Integer userId = Integer.parseInt(jsonMessage.get("userId")
					.toString());
			Integer clubId = Integer.parseInt(jsonMessage.get("clubId")
					.toString());
            String messageText = jsonMessage.get("message").toString();
			
            
            if (isClubMemberOrAdmin(userId, clubId)&&!messageText.equals("")){
            	
            	 Map<String,String> map = new HashMap<String, String>();
            	 map.put("userId", userId.toString());
            	 map.put("message", messageText);
            	 map.put("date",new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
            	 saveMessageToDB(messageText, userId, clubId);
			String responseJson = new Gson().toJson(map);
			for (Session session : clubChats.get(clubId)){
				session.getAsyncRemote().sendText(responseJson);
			}
            }
		} catch (Exception e) {
			logger.warn("user don't have permision for this chat", e);
		}
	}

	private void saveMessageToDB(String messageText, Integer userId,
			Integer clubId) throws ReflectiveOperationException, SQLException {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setText(messageText);
		chatMessage.setUserId(userId);
		chatMessage.setClubId(clubId);
		chatMessage.setState(State.ACTIVE);
		chatMessage.setCreationDate(new Timestamp(new Date().getTime()));
		ChatMessageService.saveChatMessage(chatMessage);
	}

	public boolean isClubMemberOrAdmin(Integer userId, Integer clubId)
			throws ReflectiveOperationException, SQLException {
		boolean result = false;

		User user = UserService.getUserById(userId);
		if (user != null && user.getState().equals(State.ACTIVE)) {
			if (user.getRole().equals(Role.ADMIN)) {
				result = true;
			} else {
				ClubEventMember clubEventMember = ClubEventMemberService
						.getClubEventMemberByUserIdAndClubId(userId, clubId);
				if (clubEventMember != null
						&& clubEventMember.getState().equals(State.ACTIVE)) {
					result = true;
				}
			}
		}

		return result;
	}
}
