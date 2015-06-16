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
import com.mediateka.form.LogInForm;
import com.mediateka.model.User;
import com.mediateka.service.UserService;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SaltedPasswordGenerator;

@Controller
public class LogInController {

	private static Logger logger = Logger.getLogger(LogInController.class);

	@Request(url = "login", method = "post")
	public static void logIn(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, ReflectiveOperationException,
			SQLException, NoSuchAlgorithmException, IOException {

		LogInForm form = new LogInForm();
		ObjectFiller.fill(form, request);

		User user = UserService.getUserByEmail(form.getEmail());

		String saltedPassword = SaltedPasswordGenerator.generate(
				form.getPassword(), user.getSalt());
		if (!user.getPassword().equals(saltedPassword)) {
			logger.warn("failed to log in");
			response.sendRedirect("index");
			return;
		}

		HttpSession mySession = request.getSession();

		mySession.setAttribute("id", user.getId());
		mySession.setAttribute("firstName", user.getFirstName());

		response.sendRedirect("index");

	}

	@Request(url = "logout", method = "get")
	public static void logOut(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.getSession().invalidate();
		response.sendRedirect("index");

	}

}