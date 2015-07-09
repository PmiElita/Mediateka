package com.mediateka.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.mediateka.comparator.UsersByFullname;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.SearchUserForm;
import com.mediateka.form.UserRegistrationForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.search.UserSearch;
import com.mediateka.service.ProfessionService;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SecurityStringGenerator;
import com.mediateka.util.Translator;

@Controller
public class AdminController {
	
	private static Logger logger = Logger.getLogger(AdminController.class);
	private static final int USER_COUNT = 5;
	
	@Request(url="getMoreUsersForAdmin", method="get")
	public static void getMoreUsersForAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ReflectiveOperationException, ServletException, IOException{
		try {
			String query =null;
			Integer index = null;
			try{
			 query =request.getParameter("query");
			 index= Integer.parseInt(request.getParameter("index"));
			} catch (NumberFormatException e){
				throw new WrongInputException("wrong input data");
			}
		
			if (query == null || index== null) {
				throw new WrongInputException("");
			}
			List<User> users;
			if (query.equals("")){
				users=UserService.getUsersLimited(index*USER_COUNT, USER_COUNT);
			} else {
			String[] queryArray = query.split(" ");
			 users = UserSearch.getUserByRegexps(queryArray, 0 , USER_COUNT);
			}
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ query);
			}
			boolean haveMoreResults =false;
			if (users.size()==USER_COUNT){
				haveMoreResults = true;
			}
			Collections.sort(users, new UsersByFullname());
			request.setAttribute("haveMoreResults", haveMoreResults);
			request.setAttribute("users", users);
			request.getRequestDispatcher("pages/users/user_list.jsp")
					.forward(request, response);
			;

		} catch (WrongInputException e) {
			logger.warn("search user wrong input", e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/users/user_list.jsp").forward(
					request, response);

		}
	}
	
	@Request(url = "get_users_by_regexp", method = "get")
	public static void getUsersByRegexp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {
		String[] query = request.getParameter("query").split(" ");
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = UserSearch.getUserByRegexps(query,0,USER_COUNT);

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
			List<User> users;
			if (searchUserForm.getQuery() == null || searchUserForm.equals("")) {
				users = UserService.getUsersLimited(0, USER_COUNT);
			} else {
			FormValidator.validate(searchUserForm);
			String[] query = searchUserForm.getQuery().split(" ");
		    users = UserSearch.getUserByRegexps(query, 0 , USER_COUNT);
			}
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ searchUserForm.getQuery());
			}
			boolean haveMoreResults =false;
			if (users.size()==USER_COUNT){
				haveMoreResults = true;
			}
			request.setAttribute("haveMoreResults", haveMoreResults);
			request.setAttribute("query", searchUserForm.getQuery());
			Collections.sort(users, new UsersByFullname());
			request.setAttribute("users", users);
			request.getRequestDispatcher("pages/users/users.jsp")
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
			List<User> users = UserSearch.getUserByRegexps(query,0,USER_COUNT);
			if (users == null || users.size() == 0) {
				throw new WrongInputException("No such user "
						+ searchUserForm.getQuery());
			}
			
			boolean haveMoreResults =false;
			if (users.size()==USER_COUNT){
				haveMoreResults = true;
			}
		
			request.setAttribute("haveMoreResults", haveMoreResults);
			request.setAttribute("query", searchUserForm.getQuery());
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
			List<User> users = UserSearch.getUserByRegexps(query,0,USER_COUNT);
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



	@Request(url = "editUser", method = "get")
	public static void editUserGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		
		Integer userId = null;
		try{
			userId = Integer.parseInt(request.getParameter("userId"));
		}catch (Exception e){
			response.sendRedirect("cabinet");
			return;
		}
		
		
		
		User user = UserService.getUserById(userId);
		if (user == null){
			response.sendRedirect("cabinet");
			return;
		}
		
		
		request.setAttribute("user", user);
		request.setAttribute("professions", ProfessionService.getProfessionAll());
		request.setAttribute("userId", request.getParameter("userId"));
		
		request.getRequestDispatcher("pages/form/modify_user_by_admin_form.jsp").forward(request, response);
		
	}

	
	@Request(url = "editUser", method = "post")
	public static void editUserPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException, ParseException {
		
		UserRegistrationForm form = new UserRegistrationForm();
		
		ObjectFiller.fill(form, request);
		
		System.out.println(form);
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		User user = UserService.getUserById(userId);
		
		user.setFirstName(form.getFirstName());
		user.setMiddleName(form.getMiddleName());
		user.setLastName(form.getLastName());

		user.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy").parse(
				form.getBirthDate()).getTime()));

		user.setNationality(form.getNationality());
		user.setProfessionId(Integer.parseInt(form.getProfession()));
		user.setEducation(form.getEducation());
		user.setEduInstitution(form.getInstitution());

		user.setEmail(form.getEmail());
		user.setPhone(form.getPhone());
		user.setAdress(form.getAddress());

		if (form.getFormId().equals("")){
			user.setFormId(null);
		} else {
			user.setFormId(Integer.parseInt(form.getFormId()));

		}

		UserService.updateUser(user);

		return;
		
	}
}
