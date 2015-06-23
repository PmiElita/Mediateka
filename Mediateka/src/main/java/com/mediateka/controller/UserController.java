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

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.SearchUserForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.State;
import com.mediateka.search.UserSearch;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

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
			request.getRequestDispatcher("pages/error404.jsp").forward(request,
					response);
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

	@Request(url = "get_users_by_regexp", method = "get")
	public static void getUsersByRegexp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {
		String[] query = request.getParameter("query").split(" ");
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = UserSearch.getUserByRegexps(query);

		if (users != null && users.size() > 0) {
			List<Map<String, String>> result = new ArrayList<Map<String, String>>();

			for (User user : users) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("value",
						user.getLastName() + " " + user.getFirstName() + " "
								+ user.getMiddleName());
				item.put("data", user.getId().toString());
				result.add(item);
			}

			map.put("query", request.getParameter("query"));
			map.put("suggestions", result);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));

		}

	}

	@Request(url = "users", method = "get")
	public static void showUsersGet(HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException,
			SecurityException, SQLException, ReflectiveOperationException,
			ServletException, IOException {
		try {
			SearchUserForm searchUserForm = new SearchUserForm();
			ObjectFiller.fill(searchUserForm, request);
			FormValidator.validate(searchUserForm);
			String[] query = searchUserForm.getQuery().split(" ");
			List<User> users = UserSearch.getUserByRegexps(query);
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ searchUserForm.getQuery());
			}

			request.setAttribute("qeury", searchUserForm.getQuery());
			request.setAttribute("users", users);
			request.getRequestDispatcher("pages/fedunets12.06/users.jsp")
					.forward(request, response);
			;

		} catch (WrongInputException e) {
			logger.warn("search user wrong input", e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/fedunets12.06/users.jsp")
					.forward(request, response);

		}
	}

	@Request(url = "users", method = "post")
	public static void showUsers(HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException,
			SecurityException, SQLException, ReflectiveOperationException,
			ServletException, IOException {
		try {
			SearchUserForm searchUserForm = new SearchUserForm();
			ObjectFiller.fill(searchUserForm, request);
			FormValidator.validate(searchUserForm);
			String[] query = searchUserForm.getQuery().split(" ");
			List<User> users = UserSearch.getUserByRegexps(query);
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ searchUserForm.getQuery());
			}
			request.setAttribute("qeury", searchUserForm.getQuery());
			request.setAttribute("users", users);
			request.getRequestDispatcher("pages/fedunets12.06/users.jsp")
					.forward(request, response);

		} catch (WrongInputException e) {
			logger.warn("search user wrong input", e);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "user not found");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));

		}
	}

	@Request(url = "ckeckUsers", method = "post")
	public static void checkUsers(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, IOException,
			ReflectiveOperationException, SQLException, ServletException {
		try {
			SearchUserForm searchUserForm = new SearchUserForm();
			ObjectFiller.fill(searchUserForm, request);
			FormValidator.validate(searchUserForm);
			String[] query = searchUserForm.getQuery().split(" ");
			List<User> users = UserSearch.getUserByRegexps(query);
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ searchUserForm.getQuery());
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "success");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));

		} catch (WrongInputException e) {
			logger.warn("search user wrong input", e);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "user not found");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));

		}
	}

	@Request(url = "blockUser", method = "post")
	public static void blockUnblockUser(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, IOException {
		try {
			Integer userId;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (NumberFormatException e) {
				throw new WrongInputException("wrong userId");
			}

			User user = UserService.getUserById(userId);
			String buttonText = null;
			if (user != null) {
				if (user.getState().equals(State.BLOCKED)) {
					user.setState(State.ACTIVE);
					buttonText = "Block";
				} else if (user.getState().equals(State.ACTIVE)) {
					user.setState(State.BLOCKED);
					buttonText = "Unblock";
				}
				UserService.updateUser(user);
			} else {
				throw new WrongInputException("Wrong userId");
			}

			if (buttonText != null) {
				System.out.println(1);
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", "success");
				map.put("button", buttonText);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(new Gson().toJson(map));
			} else {
				throw new WrongInputException("Wrong userId");
			}

		} catch (WrongInputException e) {
			logger.warn("Wrong userId", e);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", e.getMessage());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));
		}
	}
}
