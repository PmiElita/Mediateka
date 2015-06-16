package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.User;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.RegExps;
import com.mediateka.util.SaltedPasswordGenerator;

@Controller
public class PasswordChangingController {


	
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
			System.out.println("err1");
			response.sendRedirect("index");
			return;
		}
		
		if (password.length() > 40){
			System.out.println("err2");
			response.sendRedirect("index");
			return;
		}

		if (!password.equals(confirmPassword)){
			System.out.println("err3");
			response.sendRedirect("index");
			return;			
		}
		
//		if (!RegExps.passwordPattern.matcher(password).matches()) {
//			System.out.println("err4");
//			response.sendRedirect("index");
//			return;						
//		}

		
		
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
