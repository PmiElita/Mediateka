package com.mediateka.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.LikeRecord;
import com.mediateka.service.LikeRecordService;

@Controller
public class LikeRecordController {

	@Request(url = "like", method = "post")
	public static void likePost(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ReflectiveOperationException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		Integer contentGroupId = (Integer) request.getAttribute("contentGroupId");
		LikeRecord likeRecord = LikeRecordService.getLikeRecordByContentGroupId(contentGroupId);
		if(likeRecord == null){
			likeRecord.setUserId(userId);
			likeRecord.setContentGroupId(contentGroupId);
			likeRecord.setState(1);
			LikeRecordService.saveLikeRecord(likeRecord);
		} else {
			if(userId == likeRecord.getUserId()){
				
			}
		}
		
	}
	
	@Request(url = "dislike", method = "post")
	public static void dislikePost(HttpServletRequest request,
			HttpServletResponse response){
		
	}

}
