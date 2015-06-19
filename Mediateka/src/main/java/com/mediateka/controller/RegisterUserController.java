package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.AnonymousUserRegistrationForm;
import com.mediateka.form.UserRegistrationForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.EmailSender;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.SaltedPasswordGenerator;
import com.mediateka.util.SecurityStringGenerator;

/**
 * 
 * Used to register the user
 */
@Controller
public class RegisterUserController {

	private static Logger logger = Logger
			.getLogger(RegisterUserController.class);

	/**
	 * Takes user registration form, validates, creates new user. It is used by
	 * administrator
	 * 
	 * @param request
	 *            as in servlet
	 * @param response
	 *            as in servlet
	 * @throws ServletException
	 * @throws IOException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 * @throws ReflectiveOperationException
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */
	@Request(url = "registerNewUser", method = "post")
	public static void registerNewUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SecurityException, IllegalArgumentException, SQLException,
			ReflectiveOperationException, AddressException, MessagingException,
			ParseException, NoSuchAlgorithmException {

		UserRegistrationForm form = new UserRegistrationForm();

		ObjectFiller.fill(form, request);
		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			logger.warn("failed to validate registration form", e);
			logger.warn(form);
			response.sendRedirect("index");
			return;
		}

		User userWithSuchEmail = UserService.getUserByEmail(form.getEmail());
		if (userWithSuchEmail != null) {
			// such email is already in use
			response.sendRedirect("index");
			return;
		}

		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		newUser.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy").parse(
				form.getBirthDate()).getTime()));

		newUser.setBirthDate(new Date(0));
		newUser.setNationality(form.getNationality());
		newUser.setProfessionId(Integer.parseInt(form.getProfession()));
		newUser.setEducation(form.getEducation());
		newUser.setEduInstitution(form.getInstitution());

		newUser.setEmail(form.getEmail());
		newUser.setPhone(form.getPhone());
		newUser.setAdress(form.getAddress());
		newUser.setRole(Role.USER);
		newUser.setJoinDate(new Date(new java.util.Date().getTime()));

		newUser.setState(State.ACTIVE);

		newUser.setFormId(Integer.parseInt(form.getFormId()));
		newUser.setIsFormActive(true);

		// generate salt
		String salt = SecurityStringGenerator.generateString(128);
		newUser.setSalt(salt);
		String token = SecurityStringGenerator.generateString(64);
		newUser.setPasswordChangingToken(token);

		UserService.saveUser(newUser);

		String emailBody = 
				"<a href=\"http://localhost:8080/Mediateka/changePassword?token="
				+ StringEscapeUtils.escapeHtml4(token)
				+ "\"> click here to set new password </a>";

		EmailSender.sendMail(form.getAddress(), "password setting link",
				emailBody);

		response.sendRedirect("index");
		return;
	}

	/**
	 * Registers new user. This method is user by user itself, when he tries to
	 * create account from index page.
	 * 
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws MessagingException
	 * @throws ServletException 
	 */
	@Request(url = "anonymousRegisterNewUser", method = "post")
	public static void anonymousRegisterNewUser(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			SecurityException, IllegalArgumentException, IOException,
			ReflectiveOperationException, SQLException,
			NoSuchAlgorithmException, MessagingException, ServletException {

		logger.debug("registering new user anonymously");
		AnonymousUserRegistrationForm form = new AnonymousUserRegistrationForm();

		ObjectFiller.fill(form, request);
		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			logger.warn("failed to validate registration form", e);
			logger.warn(form);
			response.sendRedirect("index");
			return;
		}

		if (!form.getPassword().equals(form.getConfirmPassword())) {
			logger.warn("failed to validate registration form");
			logger.warn(form);
			response.sendRedirect("index");
			return;
		}

		User userWithSuchEmail = UserService.getUserByEmail(form.getEmail());
		if (userWithSuchEmail != null) {
			// such email is already in use
			logger.debug("given email is already in use");
			response.sendRedirect("index");
			return;
		}

		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		newUser.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy").parse(
				form.getBirthDate()).getTime()));

		newUser.setBirthDate(new Date(0));
		newUser.setNationality(form.getNationality());
		newUser.setProfessionId(Integer.parseInt(form.getProfession()));
		newUser.setEducation(form.getEducation());
		newUser.setEduInstitution(form.getInstitution());

		newUser.setEmail(form.getEmail());
		newUser.setPhone(form.getPhone());
		newUser.setAdress(form.getAddress());
		newUser.setRole(Role.USER);
		newUser.setJoinDate(new Date(new java.util.Date().getTime()));

		newUser.setState(State.BLOCKED);

		newUser.setFormId(null);
		newUser.setIsFormActive(true);

		// generate salt
		String salt = SecurityStringGenerator.generateString(128);
		newUser.setSalt(salt);
		newUser.setPassword(SaltedPasswordGenerator.generate(
				form.getPassword(), salt));

		String token = SecurityStringGenerator.generateString(64);
		newUser.setPasswordChangingToken(token);

		UserService.saveUser(newUser);

		String emailBody = 
				"<a href=\"http://localhost:8080/Mediateka/confirmRegistration?token="
				+ StringEscapeUtils.escapeHtml4(token)
				+ "\"> click here to confirm your registration </a>";

		EmailSender.sendMail(form.getEmail(),
				"registration confirmation link", emailBody);

		request.getRequestDispatcher("pages/additional/post_register.jsp").forward(request, response);

	}

	@Request(url = "confirmRegistration", method = "get")
	public static void confirmRegistration(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ReflectiveOperationException, SQLException, ServletException {

		String token = request.getParameter("token");

		if (token == null) {
			response.sendRedirect("index");
			return;
		}

		User user = UserService.getUserByToken(token);
		if (user == null) {
			response.sendRedirect("index");
			return;
		}

		user.setState(State.ACTIVE);
		user.setPasswordChangingToken(null);

		UserService.updateUser(user);

		
		
		
		request.setAttribute("notification",
				"congratulations, your account is activated");
		request.getRequestDispatcher("pages/index/index.jsp").forward(request,
				response);

		return;
	}

}
