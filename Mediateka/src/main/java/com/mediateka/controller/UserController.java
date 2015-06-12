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
	
	
	
	@Request(url="cabinet", method="get")
	public static void cabinetGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("pages/user/user.jsp").forward(request, response);
	//	request.getRequestDispatcher("pages/admin/admin.jsp").forward(request, response);
	}
}