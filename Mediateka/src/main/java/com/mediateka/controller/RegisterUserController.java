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

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.form.UserRegistrationForm;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.UserService;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.PasswordChangeEmailSender;
import com.mediateka.util.SecurityStringGenerator;

@Controller
public class RegisterUserController {

	static Pattern onlyCharsPattern = Pattern.compile("^[A-Za-zА-Яа-яіїґ’]+$");
	static Pattern onlyDigitsPattern = Pattern.compile("[0-9]+");
	static Pattern phoneNumberPattern = Pattern.compile("^[0-9]{12}$");
	static Pattern emailPattern = Pattern
			.compile("^[A-Z,a-z,0-9_.]+@[A-Z,a-z,0-9_.]+$");

	private static boolean checkRegistrationForm(UserRegistrationForm form) {

		// check user first, last and middle name
		if (form.getFirstName() == null) {
			return false;
		}
		if (form.getFirstName().length() > 40) {
			return false;
		}
		if (!onlyCharsPattern.matcher(form.getFirstName()).matches()) {
			return false;
		}

		if (form.getLastName() == null) {
			return false;
		}
		if (form.getLastName().length() > 40) {
			return false;
		}
		if (!onlyCharsPattern.matcher(form.getLastName()).matches()) {
			return false;
		}

		if (form.getMiddleName() == null) {
			return false;
		}
		if (form.getMiddleName().length() > 40) {
			return false;
		}
		if (!onlyCharsPattern.matcher(form.getMiddleName()).matches()) {
			return false;
		}

		if (form.getBirthDate() == null) {
			return false;
		}
		if (form.getBirthDate().length() > 40) {
			return false;
		}
		@SuppressWarnings("unused")
		java.util.Date date;
		try {
			date = new SimpleDateFormat("dd MM, yyyy").parse(form
					.getBirthDate());
		} catch (ParseException e) {
			return false;
		}

		// check citizenship
		if (form.getNationality() == null) {
			return false;
		}
		if (form.getNationality().length() > 40) {
			return false;
		}
		if (!onlyCharsPattern.matcher(form.getNationality()).matches()) {
			return false;
		}

		// check profession
		if (form.getProfession() == null) {
			return false;
		}
		if (form.getProfession().length() > 40) {
			return false;
		}
		if (!onlyDigitsPattern.matcher(form.getProfession()).matches()) {
			return false;
		}

		// check email
		if (form.getEmail() == null) {
			return false;
		}
		if (form.getEmail().length() > 40) {
			return false;
		}
		if (!emailPattern.matcher(form.getEmail()).matches()) {
			return false;
		}

		// check phone. see http://en.wikipedia.org/wiki/E.123
		if (form.getPhone() == null) {
			return false;
		}
		if (form.getPhone().length() > 40) {
			return false;
		}
		if (!phoneNumberPattern.matcher(form.getPhone()).matches()) {
			return false;
		}

		if (form.getFormId() == null) {
			return false;
		}
		if (form.getFormId().length() > 40) {
			return false;
		}
		if (!onlyDigitsPattern.matcher(form.getFormId()).matches()) {
			return false;
		}

		// check address
		if (form.getAddress() == null) {
			return false;
		}
		if (form.getAddress().length() > 40) {
			return false;
		}
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
			response.sendRedirect("index");
			return;
		}

		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		try {
			newUser.setBirthDate(new Date(new SimpleDateFormat("dd MMMM, yyyy")
					.parse(form.getBirthDate()).getTime())

			);
		} catch (ParseException e) {
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
		System.out.println("salt:" + salt);
		System.out.println("token:" + token);
		System.out.println("newUser.token = "
				+ newUser.getPasswordChangingToken());

		UserService.saveUser(newUser);
		PasswordChangeEmailSender.sendToken(newUser, token);

		response.sendRedirect("index");
	}

}
