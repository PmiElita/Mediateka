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
import com.mediateka.util.EmailSender;
import com.mediateka.util.RegExps;
import com.mediateka.util.SecurityStringGenerator;

@Controller
public class RegisterUserController {

	static Pattern onlyCharsPattern = Pattern.compile("^[A-Za-zА-Яа-яіїґ’]+$");
	static Pattern onlyDigitsPattern = Pattern.compile("[0-9]+");
	static Pattern phoneNumberPattern = Pattern.compile("^[0-9]{12}$");
	static Pattern emailPattern = Pattern
			.compile("^[A-Z,a-z,0-9_.]+@[A-Z,a-z,0-9_.]+$");

	private static boolean checkRegistrationForm(UserRegistrationForm form) {

		System.out.println("aaa");
		// check user first, last and middle name
		if (form.getFirstName() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getFirstName().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.onlyCharsPattern.matcher(form.getFirstName()).matches()) {
			return false;
		}

		System.out.println("aaa");
		if (form.getLastName() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getLastName().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.onlyCharsPattern.matcher(form.getLastName()).matches()) {
			return false;
		}

		System.out.println("aaa");
		if (form.getMiddleName() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getMiddleName().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.onlyCharsPattern.matcher(form.getMiddleName()).matches()) {
			return false;
		}
		System.out.println("aaa");

		if (form.getBirthDate() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getBirthDate().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		@SuppressWarnings("unused")
		java.util.Date date;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy").parse(form
					.getBirthDate());
		} catch (ParseException e) {
			System.out.println("FAILED AT DATE");
			return false;
		}

		System.out.println("aaa");
		// check citizenship
		if (form.getNationality() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getNationality().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.onlyCharsPattern.matcher(form.getNationality()).matches()) {
			return false;
		}

		System.out.println("aaa");
		// check profession
		if (form.getProfession() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getProfession().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.onlyDigitsPattern.matcher(form.getProfession()).matches()) {
			return false;
		}
		System.out.println("aaa");

		// check email
		if (form.getEmail() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getEmail().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.emailPattern.matcher(form.getEmail()).matches()) {
			return false;
		}
		System.out.println("aaa0");

		// check phone. see http://en.wikipedia.org/wiki/E.123
		if (form.getPhone() == null) {
			return false;
		}
		System.out.println("aaa");
		if (form.getPhone().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.phoneNumberPattern.matcher(form.getPhone()).matches()) {
			return false;
		}
		System.out.println("aaa");

		if (form.getFormId() == null) {
			return false;
		}
		System.out.println("aaa1");
		if (form.getFormId().length() > 40) {
			return false;
		}
		System.out.println("aaa");
		if (!RegExps.onlyDigitsPattern.matcher(form.getFormId()).matches()) {
			return false;
		}

		System.out.println("aaa");
		// check address
		if (form.getAddress() == null) {
			System.out.println("address is null!");
			return false;
		}
		System.out.println("aaa");
		if (form.getAddress().length() > 40) {
			return false;
		}
		System.out.println("aaa");
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

		
		System.out.println("POST HERE");
		UserRegistrationForm form = new UserRegistrationForm();

		ObjectFiller.fill(form, request);

		if (!checkRegistrationForm(form)) {
			System.out.println("CHECK FAILED");
			response.sendRedirect("index");
			return;
		}

		
		System.out.println("CHECK DONE");
		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		try {
			newUser.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy")
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

		//send mail
		
		String mailBody = 
				" <a href=\"http://localhost:8080/Mediateka/changePassword?token="
				+ token
				+ "\">click here</a> ";
		

		EmailSender.sendMail(newUser.getEmail(), "password changing page", mailBody);

		response.sendRedirect("index");
	}

}
