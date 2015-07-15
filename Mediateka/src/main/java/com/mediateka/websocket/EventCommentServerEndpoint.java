package com.mediateka.websocket;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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

import com.google.gson.Gson;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.User;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.UserService;
@ServerEndpoint(value = "/eventComment")
public class EventCommentServerEndpoint {
	private static Logger logger = Logger.getLogger(ClubCommentsServerEndpoint.class);
	private static Map<Integer,Set<Session>> eventComments =Collections
			.synchronizedMap(new HashMap<Integer,Set<Session>>());

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
				Integer eventId=Integer.parseInt(userSession.getRequestParameterMap().get("eventId").get(0));
				if (eventComments.get(eventId)==null){
					eventComments.put(eventId,Collections
							.synchronizedSet(new HashSet<Session>()));
				}
				eventComments.get(eventId).add(userSession);
				userSession.getAsyncRemote().sendText("");
			}
			
			@OnClose
			public void onClose(Session userSession) {
				Integer eventId=Integer.parseInt(userSession.getRequestParameterMap().get("eventId").get(0));
				eventComments.get(eventId).remove(userSession);
			}

			
			@OnMessage
			public void onMessage(String message, Session userSession) {
				
				try {
					JSONObject jsonMessage = (JSONObject) new JSONParser()
							.parse(message);
					Integer userId = Integer.parseInt(jsonMessage.get("userId")
							.toString());
					Integer eventId = Integer.parseInt(jsonMessage.get("eventId")
							.toString());
					
					Integer recordId = Integer.parseInt(jsonMessage.get("recordId")
							.toString());
					
		            String messageText = jsonMessage.get("message").toString();
					
		            
		            if (isEventMemberOrAdmin(userId, eventId)&&!messageText.equals("")){
		            	
		            	 Map<String,String> map = new HashMap<String, String>();
		            	 ContentGroup comment =saveCommentToDB(messageText, userId, eventId, recordId);
		            	 map.put("userId", userId.toString());
		            	 map.put("recordId", recordId.toString());
		            	 map.put("message", messageText);
		            	 map.put("commentId", comment.getId().toString());
		      
		            	 map.put("date",new SimpleDateFormat("dd.MM.yyyy HH:mm").format(comment.getCreationDate()));
		            	
					String responseJson = new Gson().toJson(map);
					for (Session session : eventComments.get(eventId)){
						session.getAsyncRemote().sendText(responseJson);
					}
		            }
				} catch (Exception e) {
					logger.warn("user don't have permision for this chat", e);
				}
			}

			private ContentGroup saveCommentToDB(String messageText, Integer userId,
					Integer eventId,Integer recordId) throws ReflectiveOperationException, SQLException {
				ContentGroup comment = new ContentGroup();
				comment.setText(messageText);
				comment.setCreatorId(userId);
				comment.setEventId(eventId);
				comment.setParentId(recordId);
				comment.setState(State.ACTIVE);
				comment.setCreationDate(new Timestamp(new Date().getTime()));
				comment.setType(ContentGroupType.COMMENT);
				comment.setLike(0);
				comment.setDislike(0);
				return ContentGroupService.callSaveContentGroup(comment);
			}

			public boolean isEventMemberOrAdmin(Integer userId, Integer eventId)
					throws ReflectiveOperationException, SQLException {
				boolean result = false;

				User user = UserService.getUserById(userId);
				if (user != null && user.getState().equals(State.ACTIVE)) {
					if (user.getRole().equals(Role.ADMIN)) {
						result = true;
					} else {
						ClubEventMember clubEventMember = ClubEventMemberService
								.getClubEventMemberByUserIdAndEventId(userId, eventId);
						if (clubEventMember != null
								&& clubEventMember.getState().equals(State.ACTIVE)) {
							result = true;
						}
					}
				}

				return result;
			}

}
