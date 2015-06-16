package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.form.PasswordChangingForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SaltedPasswordGenerator;

@Controller
public class PasswordChangingController {

	private static Logger logger = Logger
			.getLogger(PasswordChangingController.class);

	@Request(url = "changePassword", method = "get")
	public static void showPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		String token = request.getParameter("token");
		if (token == null) {
			logger.warn("trying to get password changing form without a token");
			response.sendRedirect("index");
			return;
		}
		if (token.length() != 64) {
			logger.warn("wrong token length");
			response.sendRedirect("index");
			return;
		}

		User user = UserService.getUserByToken(token);
		if (user == null) {
			logger.warn("no user with suck token");
			return;
		}

		request.getRequestDispatcher("pages/form/passwordChangingForm.jsp")
				.forward(request, response);
		return;

	}

	@Request(url = "changePassword", method = "post")
	public static void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException,
			NoSuchAlgorithmException {

		User user;

		PasswordChangingForm form = new PasswordChangingForm();

		ObjectFiller.fill(form, request);

		// TODO: VALIDATE FORM

		if (!form.getPassword().equals(form.getConfirmPassword())) {
			logger.warn("password and confirmPassword fields are not the same");
			response.sendRedirect("index");
			return;
		}

		user = UserService.getUserByToken(request.getParameter("token"));
		if (user == null) {
			logger.warn("no user is attached to given token");
			response.sendRedirect("index");
			return;
		}

		String saltedPass = SaltedPasswordGenerator.generate(
				form.getPassword(), user.getSalt());

		user.setPassword(saltedPass);
		user.setPasswordChangingToken(null);
		user.setState(State.ACTIVE);

		UserService.updateUser(user);

		response.sendRedirect("index");

	}

}
