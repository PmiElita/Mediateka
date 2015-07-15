package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.annotation.Roles;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.LikeRecord;
import com.mediateka.model.enums.Role;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.LikeRecordService;

@Controller
public class LikeRecordController {

	@Request(url = "likes", method = "get")
	@Roles({ Role.USER, Role.ADMIN})
	public static void likeDislikeGet(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {
		Integer likeState = Integer.parseInt(request.getParameter("likeState"));
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null){
			return;
		}
		Integer contentGroupId = Integer.parseInt(request.getParameter("contentGroupId"));
		LikeRecord likeRecord = LikeRecordService
				.getLikeRecordByUserIdAndContentGroupId(userId, contentGroupId);
		if (likeRecord == null) {
			likeRecord = new LikeRecord();
			likeRecord.setUserId(userId);
			likeRecord.setContentGroupId(contentGroupId);
			likeRecord.setState(likeState);
			LikeRecordService.saveLikeRecord(likeRecord);
		} else {
			likeRecord.setState(likeState);
			LikeRecordService.updateLikeRecord(likeRecord);
		}
		ContentGroup contentGroup = ContentGroupService
				.getContentGroupById(contentGroupId);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(contentGroup));

	}

}
