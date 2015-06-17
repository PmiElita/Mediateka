package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.LogInForm;
import com.mediateka.model.User;
import com.mediateka.model.UserCard;
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
	 * @param request	as in servlet
	 * @param response	as in servlet
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	@Request(url = "login", method = "post")
	public static void logIn(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, ReflectiveOperationException,
			SQLException, NoSuchAlgorithmException, IOException {

		LogInForm form = new LogInForm();
		ObjectFiller.fill(form, request);
		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			System.out.println("can't validate form");
			System.out.println(e.toString());
			logger.warn("can't validate login form", e);
			response.sendRedirect("index");
			return;
		}

		User user = UserService.getUserByEmail(form.getEmail());
		if (user==null){
			logger.warn("no user with such email");
			response.sendRedirect("index");
			return;
		}

		String saltedPassword = SaltedPasswordGenerator.generate(
				form.getPassword(), user.getSalt());
		if (!user.getPassword().equals(saltedPassword)) {
			logger.warn("failed to log in");
			response.sendRedirect("index");
			return;
		}

		HttpSession mySession = request.getSession();

		mySession.setAttribute("userId", user.getId());
		mySession.setAttribute("userFirstName", user.getFirstName());
		mySession.setAttribute("userRole", user.getRole());
		
		UserCard userCard = UserCardService.getUserCardByUserId(user.getId());
		
		mySession.setAttribute("userCard", userCard);
		
		
		response.sendRedirect("index");

	}

	/**
	 * Invalidates session
	 * @param request	as in servlet
	 * @param response	as in servlet
	 * @throws IOException
	 */
	@Request(url = "logout", method = "get")
	public static void logOut(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.getSession().invalidate();
		response.sendRedirect("index");

	}

}