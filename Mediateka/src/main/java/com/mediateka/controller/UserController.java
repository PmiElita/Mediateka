package com.mediateka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;

@Controller
public class UserController {	
	
	@Request(url="events", method="get")
	public static void eventsGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("pages/events/events.jsp").forward(request, response);
	}
	
	@Request(url="clubs", method="get")
	public static void clubsGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("pages/clubs/clubs.jsp").forward(request, response);
	}
	
	@Request(url="cabinet", method="get")
	public static void cabinetGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		
		
	//	request.getRequestDispatcher("pages/user/user.jsp").forward(request, response);
		request.getRequestDispatcher("pages/admin/admin.jsp").forward(request, response);
	}
	
	@Request(url="info", method="get")
	public static void infoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("pages/admin/info.jsp").forward(request, response);
	}
	
	@Request(url="post_register", method="get")
	public static void postRegisterGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("pages/additional/post_register.jsp").forward(request, response);
	}
}
