package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.content.CreateContent;
import com.mediateka.model.enums.ContentGroupType;


@Controller
public class RecordController {
	
	
	@Request(url="loadRecord", method="post")
	public static void loadRecordPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, SQLException, ReflectiveOperationException, IOException{
		System.out.println("laodRecord");			
		System.out.println(request.getParameterMap());
		//request.setAttribute("clubId", 2);
		CreateContent.createContent(request,response, ContentGroupType.COMMENT);
	}
	
//	@Request(url="like", method="post")
//	public static void likeRecordPost(HttpServletRequest request,
//			HttpServletResponse response) throws ReflectiveOperationException, SQLException{
//		Integer recordId = (Integer) request.getAttribute("recordId");
//		ContentGroup contentGroup = ContentGroupService.getContentGroupById(recordId);
//		contentGroup.setLike(contentGroup.getLike() + 1);
//		ContentGroupService.updateContentGroup(contentGroup);
//	}
//	
//	@Request(url="dislike", method="post")
//	public static void dislikeRecordPost(HttpServletRequest request,
//			HttpServletResponse response) throws ReflectiveOperationException, SQLException{
//		Integer recordId = (Integer) request.getAttribute("recordId");
//		ContentGroup contentGroup = ContentGroupService.getContentGroupById(recordId);
//		contentGroup.setDislike(contentGroup.getDislike() + 1);
//		ContentGroupService.updateContentGroup(contentGroup);
//	}

}
