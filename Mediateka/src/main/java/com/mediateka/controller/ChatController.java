package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.ChatMessageByCreationDate;
import com.mediateka.model.ChatMessage;
import com.mediateka.model.User;
import com.mediateka.service.ChatMessageService;
import com.mediateka.service.UserService;
@Controller
public class ChatController {

	public static final int MESSAGE_COUNT = 10;

	@Request(url ="getUserData", method="get")
	public static void getUserData(HttpServletRequest request, HttpServletResponse response) throws ReflectiveOperationException, SQLException, IOException{
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = null;
		if (userId!=null){
			User user = UserService.getUserById(userId);
			if (user!=null){
				userName = user.getFirstName();
			}
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("userName", userName);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	
	@Request(url="getMoreMessages", method="get")
	public static void getMoreMessages(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {
		Integer index = Integer.parseInt(request.getParameter("index"));
		Integer clubId = Integer.parseInt(request.getParameter("clubId"));
		if (index!=null&& clubId!=null){
			List<ChatMessage> chatMessages = ChatMessageService.getChatMessageByClubId(clubId);
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			if (chatMessages!=null&&chatMessages.size()>=index*MESSAGE_COUNT){
				Collections.sort(chatMessages, new ChatMessageByCreationDate());
			for (int i =index*MESSAGE_COUNT; i<(index+1)*MESSAGE_COUNT && i<chatMessages.size(); i++){
				Map<String, String> map = new HashMap<String, String>();
			    map.put("userName", UserService.getUserById(chatMessages.get(i).getUserId()).getFirstName());
			    map.put("message", chatMessages.get(i).getText());
			    result.add(map);
			}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(result));
		}
	}
}
