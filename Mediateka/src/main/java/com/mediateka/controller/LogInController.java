package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.LogInForm;
import com.mediateka.model.User;
import com.mediateka.model.UserCard;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ProfessionService;
import com.mediateka.service.UserCardService;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SaltedPasswordGenerator;

/**
 * 
 * LogIn controller is used to log in and log out
 * 
 */
@Controller
public class LogInController {

	private static Logger logger = Logger.getLogger(LogInController.class);

	/**
	 * 
	 * Takes login form, validates login data and sets session
	 * 
	 * @param request
	 *            as in servlet
	 * @param response
	 *            as in servlet
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Request(url = "login", method = "post")
	public static void logIn(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, ReflectiveOperationException,
			SQLException, NoSuchAlgorithmException, IOException,
			ServletException {

		LogInForm form = new LogInForm();
		ObjectFiller.fill(form, request);
		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			logger.warn("can't validate login form", e);
			response.sendRedirect("index");
			return;
		}

		User user = UserService.getUserByEmail(form.getEmail());
		if (user == null) {
			logger.warn("no user with such email");
			request.setAttribute("notification", "invalid email or password");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);

			return;
		}
        if (user.getSocialId()!=null){
        	logger.warn("trying to login as social user");
        	request.setAttribute("notification", "invalid email or password");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);
			return;
        }
		String saltedPassword = SaltedPasswordGenerator.generate(
				form.getPassword(), user.getSalt());
		if (!user.getPassword().equals(saltedPassword)) {
			logger.warn("failed to log in");
			request.setAttribute("notification", "invalid email or password");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);
			return;
		}

		if (user.getState() != State.ACTIVE) {
			logger.warn("trying to log in as non-active user");
			request.setAttribute("notification", "your account is inactive");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);
			return;
		}

		HttpSession mySession = request.getSession();

		mySession.setAttribute("userId", user.getId());
		mySession.setAttribute("userFirstName", user.getFirstName());
		mySession.setAttribute("userRole", user.getRole());
		mySession.setAttribute("userEmail", user.getEmail());
		
		if (user.getRole() == Role.ADMIN){
			mySession.setAttribute("professions", ProfessionService.getProfessionAll());
		}

		UserCard userCard = UserCardService.getUserCardByUserId(user.getId());

		mySession.setAttribute("userCard", userCard);
		


		response.sendRedirect("cabinet");

	}

	/**
	 * Invalidates session
	 * 
	 * @param request
	 *            as in servlet
	 * @param response
	 *            as in servlet
	 * @throws IOException
	 */
	@Request(url = "logout", method = "get")
	public static void logOut(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.getSession().invalidate();
		response.sendRedirect("index");

	}

	@Request(url = "checkLogin", method = "get")
	public static void checkLogin(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			ReflectiveOperationException, SQLException, IOException {
		String email = request.getParameter("email");
		User user = UserService.getUserByEmail(email);
		String message = "success";
		if (user == null||user.getSocialId()!=null) {
			logger.warn("no user with such email");
			message = "invalid email or password";

		} else {
			String saltedPassword = SaltedPasswordGenerator.generate(
					request.getParameter("password"), user.getSalt());
			if (!user.getPassword().equals(saltedPassword)) {
				logger.warn("failed to log in");
				message = "invalid email or password";
			} else if (user.getState() != State.ACTIVE
					&& user.getPasswordChangingToken() != null) {
				logger.warn("trying to log in as non-active user");
				message = "your account is inactive";
			} else if ((user.getState() != State.ACTIVE && user
					.getPasswordChangingToken() == null)) {
				logger.warn("trying to log in as blocked user");
				message = "your account is blocked";
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("message", message);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}

}