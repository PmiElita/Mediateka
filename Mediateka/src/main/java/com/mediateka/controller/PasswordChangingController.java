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
import com.mediateka.util.SaltedPasswordGenerator;

@Controller
public class PasswordChangingController {

	@Request(url = "changePassword", method = "get")
	public static void showPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HERE");
		try {
			String token = request.getParameter("token");
			if (token == null) {
				System.out.println("AAAA");
				response.sendRedirect("index");
				return;
			}

			System.out.println("token = "+token);
			User user = UserService.getUserByToken(token);
			if (user == null) {
				System.out.println("BBBB");
				response.sendRedirect("index");
				return;
			}

			if (!token.equals(user.getPasswordChangingToken())) {
				System.out.println("CCCC");
				response.sendRedirect("index");
				return;
			}
			System.out.println("OKOKO");
			request.getRequestDispatcher("pages/form/passwordChangingForm.jsp").forward(request, response);
			System.out.println("OLOLO");
			return;

		} catch (NumberFormatException | ReflectiveOperationException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Request(url = "changePassword", method = "post")
	public static void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user;
		try {
			String password = request.getParameter("password");
			if (password == null) {
				response.sendRedirect("index");
				return;

			}

			String token = request.getParameter("token");
			if (token == null) {
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

		} catch (NumberFormatException | ReflectiveOperationException
				| SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
