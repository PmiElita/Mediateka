package com.mediateka.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.form.UserRegistrationForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.EmailSender;
import com.mediateka.util.SecurityStringGenerator;

@Controller
public class RegisterUserController {

	private static Logger logger = Logger
			.getLogger(RegisterUserController.class);

	static Pattern onlyCharsPattern = Pattern.compile("^[A-Za-zА-Яа-яіїґ’]+$");
	static Pattern onlyDigitsPattern = Pattern.compile("[0-9]+");
	static Pattern phoneNumberPattern = Pattern.compile("^[0-9]{12}$");
	static Pattern emailPattern = Pattern
			.compile("^[A-Z,a-z,0-9_.]+@[A-Z,a-z,0-9_.]+$");

	@Deprecated
	private static boolean checkRegistrationForm(UserRegistrationForm form) {

		//use form validator instead
		return true;
	}

	@Request(url = "registerNewUser", method = "get")
	public static void showUserRegistrationForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("pages/form/register_new_user.jsp")
				.forward(request, response);
	}

	@Request(url = "registerNewUser", method = "post")
	public static void registerNewUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SecurityException, IllegalArgumentException, SQLException,
			ReflectiveOperationException, AddressException, MessagingException {

		UserRegistrationForm form = new UserRegistrationForm();

		ObjectFiller.fill(form, request);

		if (!checkRegistrationForm(form)) {
			logger.info("form validation failed");
			response.sendRedirect("index");
			return;
		}

		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		try {
			newUser.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy")
					.parse(form.getBirthDate()).getTime())

			);
		} catch (ParseException e) {
			logger.error("failed at parsing birth date");
			response.sendRedirect("index");
			return;
		}
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

		newUser.setFormId(Integer.parseInt(form.getFormId()));
		newUser.setIsFormActive(true);
		// generate salt

		String salt = SecurityStringGenerator.generateString(128);
		String token = SecurityStringGenerator.generateString(64);
		newUser.setSalt(salt);
		newUser.setPasswordChangingToken(token);


		UserService.saveUser(newUser);

		// send mail

		String mailBody = " <a href=\"http://localhost:8080/Mediateka/changePassword?token="
				+ token + "\">click here</a> ";

		try {
			EmailSender.sendMail(newUser.getEmail(), "password changing page",
					mailBody);
		} catch (MessagingException e) {
			logger.error("failed to send email", e);
			response.sendRedirect("index");
			return;
		}

		response.sendRedirect("index");
	}

}
