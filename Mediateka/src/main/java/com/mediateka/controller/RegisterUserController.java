package com.mediateka.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

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
import com.mediateka.util.SecurityStringGenerator;

@Controller
public class RegisterUserController {

	static Pattern onlyCharsPattern = Pattern.compile("^[A-Za-zА-Яа-яіїґ’]+$");
	static Pattern onlyDigitsPattern = Pattern.compile("[0-9]+");
	static Pattern phoneNumberPattern = Pattern.compile("^[0-9]{12}$");
	static Pattern emailPattern = Pattern
			.compile("^[A-Z,a-z,0-9_.]+@[A-Z,a-z,0-9_.]+$");

	private static boolean checkRegistrationForm(UserRegistrationForm form) {

		System.out.println(form);
		// check user first, last and middle name
		if (!onlyCharsPattern.matcher(form.getFirstName()).matches()) {
			System.out.print("1");
			return false;
		}
		if (!onlyCharsPattern.matcher(form.getLastName()).matches()) {
			System.out.print("2");
			return false;
		}
		if (!onlyCharsPattern.matcher(form.getMiddleName()).matches()) {
			System.out.print("3");
			return false;
		}

		@SuppressWarnings("unused")
		java.util.Date date;
		//date = new SimpleDateFormat("dd MM, yyyy").parse(form
			//	.getBirthDate());
		date = new java.util.Date(0);

		// check nationality
		if (!onlyCharsPattern.matcher(form.getNationality()).matches()) {
			System.out.print("1");
			return false;
		}

		// check profession
		if (!onlyDigitsPattern.matcher(form.getProfession()).matches()) {
			System.out.print("5");
			return false;
		}

		// check email
		if (!emailPattern.matcher(form.getEmail()).matches()) {
			System.out.print("8");
			return false;
		}

		// check phone. see http://en.wikipedia.org/wiki/E.123
		if (!phoneNumberPattern.matcher(form.getPhone()).matches()) {
			System.out.print("9");
			return false;
		}

		if (!onlyDigitsPattern.matcher(form.getFormId()).matches()){
			System.out.println("10");
			return false;
		}
		
		// check address
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
			HttpServletResponse response) throws ServletException, IOException {

		UserRegistrationForm form = new UserRegistrationForm();
		try {
			ObjectFiller.fill(form, request);

		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!checkRegistrationForm(form)) {
			response.sendRedirect("gofuckyourself.com");
			return;
		}

		User newUser = new User();
		newUser.setFirstName(form.getFirstName());
		newUser.setMiddleName(form.getMiddleName());
		newUser.setLastName(form.getLastName());

		try {
			newUser.setBirthDate(
					new Date(
							new SimpleDateFormat("dd MMMMMMMM, yyyy").parse(form.getBirthDate()).getTime()
					)
					
					);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newUser.setNationality(form.getNationality());
		newUser.setProfessionId(Integer.parseInt(form.getProfession()));
		newUser.setEducation(form.getEducation());
		newUser.setEduInstitution(form.getInstitution());
		
		newUser.setEmail(form.getEmail());
		newUser.setPhone(form.getPhone());
		newUser.setAdress(form.getAddress());
		newUser.setRole(Role.USER);
		newUser.setJoinDate(new Date( new java.util.Date().getTime() ));
		
		newUser.setState(State.BLOCKED);
		
		newUser.setFormId(Integer.parseInt(form.getFormId()));
		newUser.setIsFormActive(true);
		//generate salt
		


		String salt = SecurityStringGenerator.generateString(128);
		newUser.setSalt(salt);
		
		
		try {
			UserService.saveUser(newUser);
		} catch (SQLException | ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("home");
	}

}
