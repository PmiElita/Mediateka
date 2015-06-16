package com.mediateka.controller;

import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.EventService;

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
                   throw new WrongInputException("Date till must be equals or greater than date from");
			}
			//
			// // event name valid
			// if (form.getName() == null || form.getName().equals("")) {
			// fail = true;
			// message.append("Event name is empty. ");
			// } else if (form.getName().length() > 45) {
			// fail = true;
			// message.append("Event name to long. ");
			// }
			//
			// // event description valid
			// if (form.getDescription() == null ||
			// form.getDescription().equals("")) {
			// fail = true;
			// message.append("Event description is empty. ");
			// } else if (form.getDescription().length() > 255) {
			// fail = true;
			// message.append("Event description to long. ");
			// }
			//
			// // event type valid
			// try {
			// if (form.getType() != null)
			// EventType.valueOf(form.getType().toUpperCase());
			// else {
			// fail = true;
			// message.append("Event type is missing. ");
			// }
			// } catch (IllegalArgumentException e) {
			// fail = true;
			// message.append("Wrong event type. ");
			// }
			//
			// // event date valid
			// if (form.getDateFrom() == null || form.getDateTill() == null
			// || form.getDateFrom().equals("")
			// || form.getDateTill().equals("")) {
			// fail = true;
			// message.append("Wrong date format. ");
			// } else {
			// try {
			// Timestamp t1 = DateConverter.convertIntoTimestamp(form
			// .getDateFrom());
			// Timestamp t2 = DateConverter.convertIntoTimestamp(form
			// .getDateTill());
			// if (t1.getTime() > t2.getTime()) {
			// message.append("Wrong date order. ");
			// fail = true;
			// }
			// } catch (ParseException e) {
			// fail = true;
			// message.append("Wrong date format. ");
			// }
			// }

			// logic

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
			event =EventService.callSaveEvent(event);
			
			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(event.getId());
			clubEventMember.setState(State.ACTIVE);
			clubEventMember.setType(ClubEventMemberType.CREATOR);
			clubEventMember.setUserId((Integer)session.getAttribute("userId"));
			
			ClubEventMemberService.saveClubEventMember(clubEventMember);
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