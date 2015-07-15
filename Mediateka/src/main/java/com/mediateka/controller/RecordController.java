package com.mediateka.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.annotation.Roles;
import com.mediateka.content.CreateContent;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.pair.CommentUserCardPair;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.UserCardService;


@Controller
public class RecordController {
	
	
	@Request(url="loadRecord", method="post")
	public static void loadRecordPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, SQLException, ReflectiveOperationException, IOException{		
		CreateContent.createContent(request,response, ContentGroupType.RECORD);
	}
	
	@Request(url="viewNewRecord", method="get")
	public static void viewNewRecordGet(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException, SQLException, ServletException, IOException{
		CreateContent.loadContent(request, response);
	}
	
	@Request(url="deleteRecord", method="get")
	@Roles({ Role.USER, Role.ADMIN})
	public static void deleteRecordGet(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ReflectiveOperationException, SQLException, ServletException, IOException{
		ContentGroup contentGroup = ContentGroupService.getContentGroupById(Integer.parseInt(request.getParameter("recordId")));
		contentGroup.setState(State.DELETED);
		ContentGroupService.updateContentGroup(contentGroup);		
	}
	
	@Request(url = "restoreRecord", method="get")
	@Roles({ Role.USER, Role.ADMIN})
	public static void restoreRecordGet(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ReflectiveOperationException, SQLException, ServletException, IOException{
		ContentGroup contentGroup = ContentGroupService.getContentGroupById(Integer.parseInt(request.getParameter("recordId")));
		
		contentGroup.setState(State.ACTIVE);
		ContentGroupService.updateContentGroup(contentGroup);	
		
	}
	
	@Request(url = "getMoreRecords", method = "get")
	public static void getMoreRecords(HttpServletRequest request, HttpServletResponse response) throws SQLException, ReflectiveOperationException, IOException, ServletException{
		Integer index = Integer.parseInt(request.getParameter("index"));
		List<ContentGroup> records = null;
		boolean isSigned = false;
		ClubEventMember member = null;
		if (!request.getParameter("clubId").equals("null")){
			Integer clubId = Integer.parseInt(request.getParameter("clubId"));
			 records= ContentGroupService
					.getContentGroupByClubIdAndStateAndTypeLimited(clubId,
							State.ACTIVE, ContentGroupType.RECORD, ClubController.RECORD_COUNT*index ,ClubController.RECORD_COUNT);
				if (request.getSession().getAttribute("userId") != null) {
					member = ClubEventMemberService.getClubEventMemberByUserIdAndClubId(
							(Integer) request.getSession().getAttribute(
									"userId"), clubId);
					if (member != null&&member.getState() == State.ACTIVE){
						
							isSigned = true;
					}
				}

				
				
		} else if (!request.getParameter("eventId").equals("null")){
			Integer eventId = Integer.parseInt(request.getParameter("eventId"));
			 records= ContentGroupService
					.getContentGroupByEventIdAndStateAndTypeLimited(eventId,
							State.ACTIVE, ContentGroupType.RECORD, ClubController.RECORD_COUNT*index ,ClubController.RECORD_COUNT);
			 
				if (request.getSession().getAttribute("userId") != null) {
					member = ClubEventMemberService.getClubEventMemberByUserIdAndEventId(
							(Integer) request.getSession().getAttribute(
									"userId"), eventId);
					if (member != null&&member.getState() == State.ACTIVE){
						
							isSigned = true;
					}
				}
		}
		index++;
		boolean needScroll = true;
		if (records!=null){
			if (records.size()<ClubController.RECORD_COUNT){
				needScroll = false;
			}
		CreateContent.setContent(request, response, records);
		 request.setAttribute("comments", getComments(records));
		 
		} else {
			needScroll = false;
		}
		
		
		
		
		
		request.setAttribute("isSigned", isSigned);
		 
		request.setAttribute("isNew", true);
		request.setAttribute("load", false);
		request.setAttribute("scroll", needScroll);
		request.setAttribute("scrollIndex", index);
		request.getRequestDispatcher("pages/content/record_central.jsp").forward(request, response);
	}
	
	private static Map<Integer, List<CommentUserCardPair>> getComments(List<ContentGroup> records) throws SQLException, ReflectiveOperationException {
		Map<Integer, List<CommentUserCardPair>> result = new HashMap<Integer, List<CommentUserCardPair>>();
		
		if (records != null) {
			for (ContentGroup record : records) {
				List<CommentUserCardPair> commentUserCardPairs = new ArrayList<CommentUserCardPair>();
				List<ContentGroup> comments = ContentGroupService
						.getContentGroupByParentId(record.getId());
				if (comments != null) {
					for (ContentGroup comment : comments) {
						commentUserCardPairs.add(new CommentUserCardPair(
								comment, UserCardService
										.getUserCardByUserId(comment
												.getCreatorId())));
					}
				}
				result.put(record.getId(), commentUserCardPairs);
			}
		}
		return result;
	}

}
