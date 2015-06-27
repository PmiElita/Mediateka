package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.PasswordChangingForm;
import com.mediateka.form.PasswordInvalidationForm;
import com.mediateka.model.User;
import com.mediateka.service.UserService;
import com.mediateka.util.EmailSender;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SaltedPasswordGenerator;
import com.mediateka.util.SecurityStringGenerator;

@Controller
public class PasswordManagementController {

	private static Logger logger = Logger
			.getLogger(PasswordChangingController.class);

	@Request(url = "changePassword", method = "post")
	public static void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, ServletException, IOException,
			NoSuchAlgorithmException, SQLException,
			ReflectiveOperationException {

		PasswordChangingForm form = new PasswordChangingForm();

		ObjectFiller.fill(form, request);
		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			logger.warn("failed to validate password changing form", e);
			// TODO: show message to user
			response.sendRedirect("cabinet");
			return;
		}

		if (!form.getOldPassword().equals(form.getConfirmNewPassword())) {
			logger.warn("oldPassword is not the same as confirmOldPassword");
			// TODO: show message to user
			response.sendRedirect("cabinet");
			return;
		}

		// check my session
		Integer myId = (Integer) request.getSession().getAttribute("userId");

		if (myId == null) {
			logger.warn("trying to change password when not logged in");
			// TODO: show message to user
			response.sendRedirect("index");
			return;
		}

		User me = UserService.getUserById(myId);
		if (me == null) {
			logger.error("no user with such id " + myId);
			response.sendError(500);
			return;
		}

		// check old password

		String saltedPassword = SaltedPasswordGenerator.generate(
				form.getOldPassword(), me.getSalt());

		if (!saltedPassword.equals(me.getPassword())) {
			logger.warn("wrong oldPassword");
			response.sendRedirect("cabinet");
			return;
		}

		String newSaltedPassword = SaltedPasswordGenerator.generate(
				form.getNewPassword(), me.getSalt());
		me.setPassword(newSaltedPassword);
		UserService.updateUser(me);

		response.sendRedirect("cabinet");
	}
	

	
	@Request(url = "invalidatePassword", method = "get")
	public static void forgotPasswordPage(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException, IllegalArgumentException, IOException, ReflectiveOperationException, SQLException, MessagingException, ServletException {

		request.getRequestDispatcher("pages/form/forgot_password_form.jsp").forward(
				request, response);
		
	}
	
	

	@Request(url = "invalidatePassword", method = "post")
	public static void invalidatePassword(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException, IllegalArgumentException, IOException, ReflectiveOperationException, SQLException, MessagingException, ServletException {

		System.out.println("DOING THIS");
		
		PasswordInvalidationForm form = new PasswordInvalidationForm();
		ObjectFiller.fill(form, request);
		
		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			logger.warn("failed to validate password invalidation form");
			request.setAttribute("notification", "no_user_with_such_email");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);
			return;
		}
		
		User user = UserService.getUserByEmail(form.getEmail());
		
		if (user == null){
			logger.warn("no user with such email");
			System.out.println("HEREEEEEE");
			request.setAttribute("notification", "no_user_with_such_email");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);
			return;
		}
		
		
		String token = SecurityStringGenerator.generateString(64);
		user.setPasswordChangingToken(token);
		
		UserService.updateUser(user);

		// send mail
		String mailBody = " <a href=\"http://localhost:8080/Mediateka/setNewPasswordByToken?token="
				+ token + "\">click here</a> ";

		try{
		EmailSender.sendMail(user.getEmail(), "password changing page",
				mailBody);
		}catch (Exception e){
			request.setAttribute("notification", "can_not_send_email");
			request.getRequestDispatcher("pages/index/index.jsp").forward(
					request, response);
			
			user.setPasswordChangingToken(null);
			UserService.updateUser(user);
			System.out.println("can't send email");
			return;
		}
		request.setAttribute("notification", "check_your_email");
		request.getRequestDispatcher("pages/index/index.jsp").forward(
				request, response);
		
		
		System.out.println("DONE");

	}

	
	
	
	
}
