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
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.PasswordResettingForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SaltedPasswordGenerator;

/**
 * 
 * Used to change user's password
 */
@Controller
public class PasswordChangingController {

	private static Logger logger = Logger
			.getLogger(PasswordChangingController.class);

	/**
	 * Show's password changing form
	 * The form has hidden 'token' field 
	 * 
	 * @param request	as in servlet
	 * @param response	as in servlet
	 * @throws ServletException
	 * @throws IOException
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 */
	@Request(url = "setNewPasswordByToken", method = "get")
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
		
		request.setAttribute("token", request.getParameter("token"));

		request.getRequestDispatcher("pages/form/password_setting_by_token_form.jsp")
				.forward(request, response);
		return;

	}

	
	/**
	 * Checks user's token. Sets password and deletes token from DB
	 * 
	 * @param request	as in servlet
	 * @param response	as in servlet
	 * @throws ServletException
	 * @throws IOException
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 */
	@Request(url = "setNewPasswordByToken", method = "post")
	public static void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException,
			NoSuchAlgorithmException {

		User user;

		PasswordResettingForm form = new PasswordResettingForm();

		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);
		} catch (IllegalArgumentException | WrongInputException e) {
			logger.warn("can't validate password changing form", e);
			response.sendRedirect("index");
			return;
		}
		

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
