package com.mediateka.controller;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.User;
import com.mediateka.service.UserService;
import com.mediateka.util.RegExps;
import com.mediateka.util.SaltedPasswordGenerator;

@Controller
public class LogInController {
	@Request(url = "login", method = "post")
	public static void indexPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SecurityException, IllegalArgumentException,
			ReflectiveOperationException, SQLException,
			NoSuchAlgorithmException {
		
		System.out.println("HERE");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println("HERE");
		if (email == null) {
			response.sendRedirect("index");
			return;
		}
		System.out.println("HERE");
		if (password == null) {
			response.sendRedirect("index");
			return;
		}
		System.out.println("HERE");

//		if (!RegExps.passwordPattern.matcher(password).matches()) {
//			response.sendRedirect("index");
//			return;
//		}
		System.out.println("HERE");

		if (!RegExps.emailPattern.matcher(email).matches()) {
			response.sendRedirect("index");
			return;
		}
		System.out.println("HERE");

		User user = UserService.getUserByEmail(email);

		System.out.println("HERE");
		String saltedPassword = SaltedPasswordGenerator.generate(password, user.getSalt());
		if (!user.getPassword().equals(saltedPassword)) {
			
			System.out.println("password in db: " + user.getPassword());
			System.out.println("salted : " + saltedPassword );
			response.sendRedirect("index");
			return;
		}

		System.out.println("HERE");
		HttpSession mySession = request.getSession();
		
		mySession.setAttribute("id", user.getId());
		mySession.setAttribute("firstName", user.getFirstName());
		
		System.out.println("OK");
		response.sendRedirect("index");

	}

}