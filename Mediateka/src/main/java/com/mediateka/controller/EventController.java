package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.EventRegistrationForm;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.Event;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.State;
import com.mediateka.util.DateConverter;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

import static com.mediateka.service.ClubEventMemberService.saveClubEventMember;
import static com.mediateka.service.EventService.callSaveEvent;

@Controller
public class EventController {

	private static Logger logger = Logger.getLogger(EventController.class);

	@Request(url = "CreateEvent", method = "get")
	public static void EventCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("pages/fedunets12.06/create_event.jsp")
				.forward(request, response);

	}

	@Request(url = "CreateEvent", method = "post")
	public static void EventCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, SQLException,
			ReflectiveOperationException, ServletException, IOException {

		try {

			HttpSession session = request.getSession();

			session.setAttribute("userId", new Integer(1));
			EventRegistrationForm form = new EventRegistrationForm();
			ObjectFiller.fill(form, request);

			FormValidator.validate(form);
			Timestamp dateFrom = DateConverter.convertIntoTimestamp(
					form.getDateFrom(), "yyyy-MM-dd");
			Timestamp dateTill = DateConverter.convertIntoTimestamp(
					form.getDateTill(), "yyyy-MM-dd");
			if (dateTill.getTime() < dateFrom.getTime()) {
				throw new WrongInputException(
						"Date till must be equals or greater than date from");
			}

			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.valueOf(form.getType().toUpperCase()));
			event.setDescription(form.getDescription());
			event.setState(State.BLOCKED);
			event.setDateFrom(DateConverter.convertIntoTimestamp(
					form.getDateFrom(), "yyyy-MM-dd"));
			event.setDateTill(DateConverter.convertIntoTimestamp(
					form.getDateTill(), "yyyy-MM-dd"));
			if (session.getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(session
						.getAttribute("club_id").toString()));
			event.setAvaId(1);
			event = callSaveEvent(event);

			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(event.getId());
			clubEventMember.setState(State.ACTIVE);
			clubEventMember.setType(ClubEventMemberType.CREATOR);
			clubEventMember.setUserId((Integer) session.getAttribute("userId"));
			saveClubEventMember(clubEventMember);
			String message = "Event created. ";

			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/fedunets12.06/create_event.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/fedunets12.06/create_event.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		}
	}
}