package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.Club;
import com.mediateka.model.Event;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubService;
import com.mediateka.service.EventService;
import com.mediateka.service.UserService;

@Controller
public class SergijController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Request(url = "activateClub", method = "get")
	public static void activateClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			return;
		}

		if (user.getRole() != Role.ADMIN){
			return;
		}
		
		System.out.println("5");
		String idString = request.getParameter("id");
		
		if (idString == null){
			return;
		}
		
		Integer clubId = Integer.parseInt( idString );
		
		if (clubId == null){
			return;
		}
		
		Club club = ClubService.getClubById(clubId);
		
		club.setState(State.ACTIVE);
		ClubService.updateClub(club);

		return;
	}


	@Request(url = "deleteClub", method = "get")
	public static void deleteClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			response.getWriter().write("");
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			response.getWriter().write("");
			return;
		}

		if (user.getRole() != Role.ADMIN){
			response.getWriter().write("");
			return;
		}
		
		Integer clubId = Integer.parseInt( request.getParameter("id"));
		
		if (clubId == null){
			response.getWriter().write("");
			return;
		}
		
		
		Club club = ClubService.getClubById(clubId);
		
		club.setState(State.DELETED);
		ClubService.updateClub(club);
		response.getWriter().write("");
		return;
	}









	@Request(url = "activateEvent", method = "get")
	public static void activateEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			return;
		}

		if (user.getRole() != Role.ADMIN){
			return;
		}
		
		System.out.println("5");
		String idString = request.getParameter("id");
		
		if (idString == null){
			return;
		}
		
		Integer eventId = Integer.parseInt( idString );
		
		if (eventId == null){
			return;
		}
		
		Event event = EventService.getEventById(eventId);
		
		event.setState(State.ACTIVE);
		EventService.updateEventById(event);

		return;
	}


	@Request(url = "deleteEvent", method = "get")
	public static void deleteEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ReflectiveOperationException, SQLException {


		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			return;
		}

		if (user.getRole() != Role.ADMIN){
			return;
		}
		
		System.out.println("5");
		String idString = request.getParameter("id");
		
		if (idString == null){
			return;
		}
		
		Integer eventId = Integer.parseInt( idString );
		
		if (eventId == null){
			return;
		}
		
		Event event = EventService.getEventById(eventId);
		
		event.setState(State.DELETED);
		EventService.updateEventById(event);

		return;
	}



}
