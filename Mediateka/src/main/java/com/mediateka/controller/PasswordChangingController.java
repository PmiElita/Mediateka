package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.User;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.SaltedPasswordGenerator;

@Controller
public class PasswordChangingController {

	static Pattern passwordPattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

	
	@Request(url = "changePassword", method = "get")
	public static void showPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		System.out.println("HERE");

		String token = request.getParameter("token");
		if (token == null) {
			response.sendRedirect("index");
			return;
		}
		if (token.length() != 64) {
			response.sendRedirect("index");
			return;
		}

		System.out.println("token = " + token);
		User user = UserService.getUserByToken(token);
		if (user == null) {
			response.sendRedirect("index");
			return;
		}

		if (!token.equals(user.getPasswordChangingToken())) {
			response.sendRedirect("index");
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
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if (password == null) {
			response.sendRedirect("index");
			return;
		}
		
		if (password.length() > 40){
			response.sendRedirect("index");
			return;
		}

		if (!password.equals(confirmPassword)){
			response.sendRedirect("index");
			return;			
		}
		
		if (!passwordPattern.matcher(password).matches()) {
			response.sendRedirect("index");
			return;						
		}

		
		
		String token = request.getParameter("token");
		if (token == null) {
			response.sendRedirect("index");
			return;
		}

		if (token.length() != 64){
			response.sendRedirect("index");
			return;
		}
		user = UserService.getUserByToken(request.getParameter("token"));
		if (user == null) {
			response.sendRedirect("index");
			return;
		}

		if (!token.equals(user.getPasswordChangingToken())) {
			response.sendRedirect("index");
			return;
		}

		String saltedPass = SaltedPasswordGenerator.generate(password,
				user.getSalt());

		user.setPassword(saltedPass);
		user.setPasswordChangingToken(null);
		user.setState(State.ACTIVE);

		UserService.updateUser(user);

		response.sendRedirect("index");

	}

}
