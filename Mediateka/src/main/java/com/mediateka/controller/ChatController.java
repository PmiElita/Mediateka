package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.mediateka.model.UserCard;
import com.mediateka.service.ChatMessageService;
import com.mediateka.service.UserCardService;

@Controller
public class ChatController {

	public static final int MESSAGE_COUNT = 10;

	@Request(url = "getUserData", method = "get")
	public static void getUserData(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = null;
		String userAvaPath = null;
		if (userId != null) {
			UserCard userCard = UserCardService.getUserCardByUserId(userId);
			if (userCard != null) {
				userName = userCard.getFirstName();
				userAvaPath = userCard.getPath();
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("avaPath", userAvaPath);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}

	@Request(url = "getMoreMessages", method = "get")
	public static void getMoreMessages(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {
		Integer index = Integer.parseInt(request.getParameter("index"));
		Integer clubId = Integer.parseInt(request.getParameter("clubId"));
		if (index != null && clubId != null) {
			List<ChatMessage> chatMessages = ChatMessageService
					.getChatMessageByClubId(clubId, index * MESSAGE_COUNT,
							MESSAGE_COUNT);
			List<Map<String, String>> result = new ArrayList<Map<String, String>>();

			if (chatMessages != null) {
				Collections.sort(chatMessages, new ChatMessageByCreationDate());
				for (int i = 0; i < chatMessages.size(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					UserCard userCard = UserCardService
							.getUserCardByUserId(chatMessages.get(i)
									.getUserId());
					map.put("userName", userCard.getFirstName());
					map.put("avaPath", userCard.getPath());
					map.put("message", chatMessages.get(i).getText());
					map.put("date", new SimpleDateFormat("dd.MM.yyyy HH:mm")
							.format(chatMessages.get(i).getCreationDate()
									.getTime()));
					result.add(map);
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(result));
		}
	}
}
