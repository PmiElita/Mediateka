package com.mediateka.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.UserModificationForm;
import com.mediateka.model.Profession;
import com.mediateka.model.User;
import com.mediateka.model.UserCard;
import com.mediateka.model.enums.State;
import com.mediateka.service.ProfessionService;
import com.mediateka.service.UserCardService;
import com.mediateka.service.UserService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

@Controller
public class ModifyUserController {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(UserController.class);

	@Request(url = "modifyUser", method = "get")
	public static void getModificationForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException, ReflectiveOperationException {

		
		List<Profession> professions = ProfessionService.getProfessionByState(State.ACTIVE);
		request.setAttribute("professions", professions);
		
		User me = UserService.getUserById((Integer) request.getSession().getAttribute("userId"));
		System.out.println("me == " + me);
		request.setAttribute("firstName", me.getFirstName());
		request.setAttribute("lastName", me.getLastName());
		request.setAttribute("middleName", me.getMiddleName());
		request.setAttribute("nationality", me.getNationality());
		request.setAttribute("eduInstitution", me.getEduInstitution());
		String birthDate = new SimpleDateFormat("dd.MM.yyyy").format(me.getBirthDate());
		request.setAttribute("birthDate", birthDate);
		request.setAttribute("address", me.getAdress());
		request.setAttribute("phone", me.getPhone());
		
		request.getRequestDispatcher("pages/form/modify_user_form.jsp")
				.forward(request, response);
	}

	@Request(url = "modifyUser", method = "post")
	public static void modifyUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException,
			IllegalArgumentException, WrongInputException, ParseException {

		UserModificationForm form = new UserModificationForm();
		ObjectFiller.fill(form, request);

		System.out.println(form);
		FormValidator.validate(form);

		Integer myId = (Integer) request.getSession().getAttribute("userId");
		if (myId == null) {
			response.sendRedirect("index");
			return;
		}

		User me = UserService.getUserById(myId);

		if (me == null) {
			request.getSession().invalidate();
			response.sendRedirect("index");
			return;
		}

		me.setFirstName(form.getFirstName());
		me.setMiddleName(form.getMiddleName());
		me.setLastName(form.getLastName());
		me.setNationality(form.getNationality());
		me.setProfessionId(Integer.parseInt(form.getProfession()));
		me.setEducation(form.getEducation());
		me.setEduInstitution(form.getInstitution());
		me.setBirthDate(new Date(new SimpleDateFormat("dd.MM.yyyy").parse(
				form.getBirthDate()).getTime()));

		me.setAdress(form.getAddress());
		me.setPhone(form.getPhone());

		UserService.updateUser(me);

		// update userCard
		UserCard userCard = UserCardService.getUserCardByUserId(me.getId());

		request.getSession().setAttribute("userCard", userCard);

		response.sendRedirect("cabinet");

	}

}
