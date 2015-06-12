package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.form.EventRegistrationForm;
import com.mediateka.model.Event;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.State;
import com.mediateka.util.ObjectFiller;
import com.mediateka.util.DateConverter;

import static com.mediateka.service.EventService.saveEvent;

@Controller
public class EventController {

	@Request(url = "CreateEvent", method = "get")
	public static void EventGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("pages/fedunets12.06/create_event.jsp")
				.forward(request, response);

	}

	@Request(url = "CreateEvent", method = "post")
	public static void EventPost(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, SQLException,
			ReflectiveOperationException, ServletException, IOException {

		StringBuilder message = new StringBuilder();

		HttpSession session = request.getSession();
		boolean fail = false;

		EventRegistrationForm form = new EventRegistrationForm();
		ObjectFiller.fill(form, request);

		if (form.getType().equals("") || form.getDate_from().equals("")
				|| form.getDate_till().equals("")) {
			fail = true;
			message.append("Some fields are missing. ");
		}

		// event name valid
		if (form.getName().equals("")) {
			fail = true;
			message.append("Event name is empty. ");
		} else if (form.getName().length() > 45) {
			fail = true;
			message.append("Event name to long. ");
		}

		// event description valid
		if (form.getDescription().equals("")) {
			fail = true;
			message.append("Event description is empty. ");
		} else if (form.getDescription().length() > 255) {
			fail = true;
			message.append("Event description to long. ");
		}

		// event type valid
		try {
			if (form.getType() != null)
				EventType.valueOf(form.getType().toUpperCase());
		} catch (IllegalArgumentException e) {
			fail = true;
			message.append("Wrong event type. ");
		}

		// event date valid
		if (form.getDate_from().equals("") || form.getDate_till().equals("")) {
			fail = true;
			message.append("Wrong date format. ");
		}
		try {
			Timestamp t1 = DateConverter.convertIntoTimestamp(form
					.getDate_from());
			Timestamp t2 = DateConverter.convertIntoTimestamp(form
					.getDate_till());
			if (t1.getTime() > t2.getTime()) {
				message.append("Wrong date order. ");
				fail = true;
			}
		} catch (ParseException e) {
			fail = true;
			message.append("Wrong date format. ");
		}

		if (!fail) {
			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.valueOf(form.getType().toUpperCase()));
			event.setDescription(form.getDescription());
			event.setState(State.BLOCKED);
			event.setDateFrom(DateConverter.convertIntoTimestamp(form
					.getDate_from()));
			event.setDateTill(DateConverter.convertIntoTimestamp(form
					.getDate_till()));
			if (session.getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(session
						.getAttribute("club_id").toString()));
			saveEvent(event);
			message.append("Event created. ");
		}

		request.setAttribute("message", message.toString());
		request.getRequestDispatcher("pages/fedunets12.06/create_event.jsp")
				.forward(request, response);
		request.removeAttribute("message");

	}
}