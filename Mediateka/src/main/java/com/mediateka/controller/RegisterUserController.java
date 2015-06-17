package com.mediateka.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.UserRegistrationForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.EmailSender;
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
	 * Takes user registration form, validates, creates new user
	 * 
	 * @param request	as in servlet
	 * @param response	as in servlet
	 * @throws ServletException
	 * @throws IOException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 * @throws ReflectiveOperationException
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws ParseException
	 */
	@Request(url = "registerNewUser", method = "post")
	public static void registerNewUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SecurityException, IllegalArgumentException, SQLException,
			ReflectiveOperationException, AddressException, MessagingException, ParseException {

		System.out.println("START");
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

		System.out.println("HERE");

		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		newUser.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy")
				.parse(form.getBirthDate()).getTime()));

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

		System.out.println("AND HERE");
		newUser.setState(State.BLOCKED);

		newUser.setFormId(Integer.parseInt(form.getFormId()));
		newUser.setIsFormActive(true);
		
		// generate salt
		String salt = SecurityStringGenerator.generateString(128);
		String token = SecurityStringGenerator.generateString(64);
		newUser.setSalt(salt);
		newUser.setPasswordChangingToken(token);

		UserService.saveUser(newUser);

		System.out.println("DDDDD");
		// send mail
		String mailBody = " <a href=\"http://localhost:8080/Mediateka/changePassword?token="
				+ token + "\">click here</a> ";

		EmailSender.sendMail(newUser.getEmail(), "password changing page",
				mailBody);

		request.getRequestDispatcher("pages/additional/post_register.jsp").forward(request, response);
	}

}
