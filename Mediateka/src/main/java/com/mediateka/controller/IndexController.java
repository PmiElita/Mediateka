package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.EventsByDate;
import com.mediateka.model.Event;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ProfessionService;

import static com.mediateka.service.EventService.getEventByState;

@Controller
public class IndexController {

	@Request(url = "index", method = "get")
	public static void indexGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		long currentTime = new java.util.Date().getTime();
		List<Event> events = getEventByState(State.ACTIVE);
		List<Event> currentEvents = new ArrayList<>();
		if (events != null)
			for (Event event : events)
				if (event.getDateTill().getTime() > currentTime)
					currentEvents.add(event);
		Collections.sort(currentEvents, new EventsByDate());

		int size = currentEvents.size();

		if (size > 3)
			for (int i = 3; i < currentEvents.size(); i++)
				currentEvents.remove(i);
		else if (size < 3) {
			int difference = 3 - size;
			List<Event> oldEvents = new ArrayList<>();
			if (events != null)
				for (Event event : events)
					if (event.getDateTill().getTime() < currentTime)
						oldEvents.add(event);
			Collections.sort(oldEvents, new EventsByDate());
			if (oldEvents.size() >= difference)
				for (int i = 0; i < difference; i++)
					currentEvents.add(oldEvents.get(i));
			if (oldEvents.size() < difference)
				currentEvents.addAll(oldEvents);
		}

		List<String> dates = new ArrayList<>();
		Timestamp dateFrom;
		Timestamp dateTill;
		String date = "It goes right now!";
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat formatMeeting = new SimpleDateFormat("hh:mm");
		for (Event event : currentEvents) {
			dateFrom = event.getDateFrom();
			dateTill = event.getDateTill();
			if (event.getType() == EventType.MEETING)
				date = format.format(dateFrom) + "    "
						+ formatMeeting.format(dateFrom) + "  -  "
						+ formatMeeting.format(dateTill);
			else if (event.getType() == EventType.EXHIBITION)
				date = format.format(dateFrom) + "  -  "
						+ format.format(dateTill);
			dates.add(date);
		}

		request.setAttribute("dates", dates);
		request.setAttribute("events", currentEvents);
		request.setAttribute("professions",
				ProfessionService.getProfessionAll());
		request.getRequestDispatcher("pages/index/index.jsp").forward(request,
				response);
		request.removeAttribute("events");
		request.removeAttribute("professions");
		request.removeAttribute("dates");
	}
}
