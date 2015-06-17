package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.User;
import com.mediateka.service.UserService;

@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Request(url = "events", method = "get")
	public static void eventsGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/events/events.jsp").forward(
				request, response);
	}

	@Request(url = "clubs", method = "get")
	public static void clubsGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/clubs/clubs.jsp").forward(request,
				response);
	}

	@Request(url = "cabinet", method = "get")
	public static void cabinetGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			request.getRequestDispatcher("pages/error404.jsp").forward(request, response);
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			response.sendRedirect("index");
			return;
		}

		switch (user.getRole()) {
		case ADMIN:
		case MODERATOR:
			request.getRequestDispatcher("pages/admin/admin.jsp").forward(
					request, response);
			break;

		case USER:
			request.getRequestDispatcher("pages/user/user.jsp").forward(
					request, response);
			break;

		default:
			logger.error("unknown user role " + user.getRole().toString());
			response.sendRedirect("index");
			break;
		}
	}

	@Request(url = "info", method = "get")
	public static void infoGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/admin/info.jsp").forward(request,
				response);
	}

	@Request(url = "post_register", method = "get")
	public static void postRegisterGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/additional/post_register.jsp")
				.forward(request, response);
	}
}
