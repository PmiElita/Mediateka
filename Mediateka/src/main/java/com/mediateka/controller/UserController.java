package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import com.mediateka.comparator.ClubsByMembersNumber;
import com.mediateka.comparator.UsersByFullname;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.SearchUserForm;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.Event;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.search.UserSearch;
import com.mediateka.service.EventService;
import com.mediateka.service.ProfessionService;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.Translator;

import static com.mediateka.service.ClubEventMemberService.*;
import static com.mediateka.service.ClubService.*;
import static com.mediateka.service.MediaService.*;

@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Request(url = "events", method = "get")
	public static void eventsGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");

		System.out.println("events");
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

			List<Event> allEvents = EventService.getEventAll();

			List<Event> requestedEvents = new ArrayList<Event>();
			for (Event c : allEvents) {
				if (c.getState() == State.REQUESTED) {
					requestedEvents.add(c);
				}
			}

			request.setAttribute("events", requestedEvents);

			request.getRequestDispatcher("pages/table/admin_events.jsp")
					.forward(request, response);
			break;

		case USER:
			request.getRequestDispatcher("pages/events/events.jsp").forward(
					request, response);
			break;

		default:
			logger.error("unknown user role " + user.getRole().toString());
			response.sendRedirect("index");
			break;
		}

	}

	@Request(url = "clubs", method = "get")
	public static void clubsGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException, ReflectiveOperationException {

		request.getSession().setAttribute("userId", 1);

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

		if ((user.getRole() == Role.ADMIN)
				|| (user.getRole() == Role.MODERATOR)) {
			List<Event> allEvents = EventService.getEventAll();

			List<Event> requestedEvents = new ArrayList<Event>();
			for (Event c : allEvents) {
				if (c.getState() == State.REQUESTED) {
					requestedEvents.add(c);
				}
			}

			request.setAttribute("events", requestedEvents);

			request.getRequestDispatcher("pages/table/admin_clubs.jsp")
					.forward(request, response);
			return;

		} else if (user.getRole() == Role.USER) {

			// my clubs
			List<ClubEventMember> memberer = getClubEventMemberByUserId(Integer
					.parseInt(request.getSession().getAttribute("userId")
							.toString()));
			List<ClubEventMember> clubMemberer = new ArrayList<>();
			for (ClubEventMember member : memberer)
				if (member.getClubId() != null)
					clubMemberer.add(member);
			List<Club> myClubs = new ArrayList<>();
			for (ClubEventMember member : clubMemberer) {
				myClubs.add(getClubById(member.getClubId()));
			}
			Collections.sort(myClubs, new ClubsByMembersNumber());
			List<Club> myActiveClubs = new ArrayList<>();
			List<Club> myBlockedClubs = new ArrayList<>();
			for (Club club : myClubs) {
				if (club.getState() == State.ACTIVE)
					myActiveClubs.add(club);
				else if (club.getState() == State.BLOCKED)
					myBlockedClubs.add(club);
			}

			// my clubs avas
			List<String> myActiveClubsAvas = new ArrayList<>();
			List<String> myBlockedClubsAvas = new ArrayList<>();
			for (Club club : myActiveClubs)
				myActiveClubsAvas.add(getMediaById(club.getAvaId()).getPath()
						.replace("\\", "/"));
			for (Club club : myBlockedClubs)
				myBlockedClubsAvas.add(getMediaById(club.getAvaId()).getPath()
						.replace("\\", "/"));

			request.setAttribute("myActiveClubsAvas", myActiveClubsAvas);
			request.setAttribute("myBlockedClubsAvas", myBlockedClubsAvas);
			request.setAttribute("myActiveClubs", myActiveClubs);
			request.setAttribute("myBlockedClubs", myBlockedClubs);
			request.getRequestDispatcher("pages/clubs/clubs.jsp").forward(
					request, response);
			request.removeAttribute("myActiveClubs");
			request.removeAttribute("myBlockedClubs");
			request.removeAttribute("myActiveClubsAvas");
			request.removeAttribute("myBlockedClubsAvas");
		}
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
			request.setAttribute("professions",
					ProfessionService.getProfessionAll());

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
			if (searchUserForm.getQuery() == null || searchUserForm.equals("")) {
				throw new WrongInputException("");
			}
			FormValidator.validate(searchUserForm);
			String[] query = searchUserForm.getQuery().split(" ");
			List<User> users = UserSearch.getUserByRegexps(query);
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ searchUserForm.getQuery());
			}

			request.setAttribute("qeury", searchUserForm.getQuery());
			Collections.sort(users, new UsersByFullname());
			request.setAttribute("users", users);
			request.getRequestDispatcher("pages/users/users_central.jsp")
					.forward(request, response);
			;

		} catch (WrongInputException e) {
			logger.warn("search user wrong input", e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/users/users.jsp").forward(
					request, response);

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
			Collections.sort(users, new UsersByFullname());
			request.setAttribute("users", users);
			request.getRequestDispatcher("pages/users/users.jsp").forward(
					request, response);

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
				Translator translator = new Translator("translations/users",
						request);

				if (user.getState().equals(State.BLOCKED)) {
					user.setState(State.ACTIVE);

					buttonText = translator.getMessage("button.block");
					// buttonText = "Block";
				} else if (user.getState().equals(State.ACTIVE)) {
					user.setState(State.BLOCKED);
					buttonText = translator.getMessage("button.unblock");
					// buttonText = "Unblock";
				}
				UserService.updateUser(user);
			} else {
				throw new WrongInputException("Wrong userId");
			}

			if (buttonText != null) {
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
