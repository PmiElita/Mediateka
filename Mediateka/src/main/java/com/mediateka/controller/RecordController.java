package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.content.CreateContent;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ContentGroupService;


@Controller
public class RecordController {
	
	
	@Request(url="loadRecord", method="post")
	public static void loadRecordPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, SQLException, ReflectiveOperationException, IOException{
		System.out.println("laodRecord");			
		System.out.println(request.getParameterMap());
		CreateContent.createContent(request,response, ContentGroupType.COMMENT);
	}
	
	@Request(url="viewNewRecord", method="get")
	public static void viewNewRecordGet(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException, SQLException, ServletException, IOException{
		System.out.println("viewNewRecord");
		CreateContent.loadContent(request, response);
	}
	
	@Request(url="deleteRecord", method="get")
	public static void deleteRecordGet(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ReflectiveOperationException, SQLException{
		ContentGroup contentGroup = ContentGroupService.getContentGroupById(Integer.parseInt(request.getParameter("recordId")));
		contentGroup.setState(State.DELETED);
		ContentGroupService.updateContentGroup(contentGroup);
	}
	
	

}
