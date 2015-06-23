package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ExhibitionRegistrationForm;
import com.mediateka.form.MeetingRegistrationForm;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.Event;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.util.DateConverter;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

import static com.mediateka.service.ClubEventMemberService.saveClubEventMember;
import static com.mediateka.service.EventService.*;
import static com.mediateka.service.MediaService.callSaveMedia;
import static com.mediateka.service.MediaService.getMediaById;
import static com.mediateka.util.DateConverter.*;

@Controller
public class EventController {

	private static Logger logger = Logger.getLogger(EventController.class);

	@Request(url = "event", method = "get")
	public static void goToEventGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getRequestDispatcher("pages/event/event.jsp").forward(request,
				response);
	}

	// create event

	@Request(url = "CreateExhibition", method = "get")
	public static void exhibitionCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("userId", 1);
		request.getRequestDispatcher("pages/events/createExhibition.jsp")
				.forward(request, response);

	}

	@Request(url = "CreateMeeting", method = "get")
	public static void meetingCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("userId", 1);
		request.getRequestDispatcher("pages/events/createMeeting.jsp").forward(
				request, response);

	}

	@SuppressWarnings("deprecation")
	@Request(url = "CreateExhibition", method = "post")
	public static void exhibitionCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, SQLException,
			ReflectiveOperationException, ServletException, IOException {

		ExhibitionRegistrationForm form = new ExhibitionRegistrationForm();
		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);

			Timestamp currentTime = new Timestamp(
					new java.util.Date().getTime());
			currentTime.setHours(0);
			currentTime.setMinutes(0);
			currentTime.setSeconds(0);

			Timestamp dateFrom = convertIntoTimestamp(form.getDateFrom(),
					"yyyy-MM-dd");
			Timestamp dateTill = convertIntoTimestamp(form.getDateFrom(),
					"yyyy-MM-dd");
			if (dateFrom.getTime() <= 0 || dateTill.getTime() <= 0)
				throw new WrongInputException("Date is too big or too small. ");
			if (currentTime.getTime() > dateFrom.getTime())
				throw new WrongInputException("Date from has gone. ");
			if (dateFrom.getTime() > dateTill.getTime())
				throw new WrongInputException(
						"Date from cant be bigger than date till. ");

			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.EXHIBITION);
			event.setDescription(form.getDescription());
			event.setState(State.BLOCKED);
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("club_id").toString()));
			event.setAvaId(2);
			event = callSaveEvent(event);

			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(event.getId());
			clubEventMember.setState(State.ACTIVE);
			clubEventMember.setType(ClubEventMemberType.CREATOR);
			clubEventMember.setUserId((Integer) request.getSession()
					.getAttribute("userId"));
			saveClubEventMember(clubEventMember);
			String message = "Exhibition created. ";

			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/events/createExhibition.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/events/createExhibition.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		}
	}

	@SuppressWarnings("deprecation")
	@Request(url = "CreateMeeting", method = "post")
	public static void meetingCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, SQLException,
			ReflectiveOperationException, ServletException, IOException {

		MeetingRegistrationForm form = new MeetingRegistrationForm();
		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);

			Timestamp currentTime = new Timestamp(
					new java.util.Date().getTime());
			currentTime.setHours(0);
			currentTime.setMinutes(0);
			currentTime.setSeconds(0);

			Timestamp dateFrom = convertIntoTimestamp(form.getDate(),
					"yyyy-MM-dd");
			Timestamp dateTill = convertIntoTimestamp(form.getDate(),
					"yyyy-MM-dd");

			int[] timeFrom = timeValid(form.getTimeFrom());
			int[] timeTill = timeValid(form.getTimeTill());
			dateFrom.setHours(timeFrom[0]);
			dateFrom.setMinutes(timeFrom[1]);
			dateTill.setHours(timeTill[0]);
			dateTill.setMinutes(timeTill[1]);

			if (dateFrom.getTime() <= 0)
				throw new WrongInputException("Date is too big or too small. ");
			if (currentTime.getTime() > dateFrom.getTime())
				throw new WrongInputException("Date has gone. ");

			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.MEETING);
			event.setDescription(form.getDescription());
			event.setState(State.BLOCKED);
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("club_id").toString()));
			event.setAvaId(2);
			event = callSaveEvent(event);

			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(event.getId());
			clubEventMember.setState(State.ACTIVE);
			clubEventMember.setType(ClubEventMemberType.CREATOR);
			clubEventMember.setUserId((Integer) request.getSession()
					.getAttribute("userId"));
			saveClubEventMember(clubEventMember);
			String message = "Meeting created. ";

			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/events/createMeeting.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/events/createMeeting.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		}
	}

	// update event

	@Request(url = "UpdateEvent", method = "get")
	public static void eventUpdateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getSession().setAttribute("eventId", 31);
		request.getSession().setAttribute("userId", 2);

		Event event = getEventById(Integer.parseInt(request.getSession()
				.getAttribute("eventId").toString()));
		if (event.getAvaId() != null) {
			Media media = getMediaById(event.getAvaId());
			String imagePath = media.getPath().replace("\\", "/");
			request.setAttribute("imagePath", imagePath);
		}
		request.setAttribute("event", event);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateFrom = format.format(event.getDateFrom());
		if (event.getType() == EventType.EXHIBITION) {
			String dateTill = format.format(event.getDateTill());
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("dateTill", dateTill);
			request.getRequestDispatcher("pages/events/updateExhibition.jsp")
					.forward(request, response);
		} else if (event.getType() == EventType.MEETING) {
			@SuppressWarnings("deprecation")
			String timeFrom = event.getDateFrom().getHours() + ":"
					+ event.getDateFrom().getMinutes();
			@SuppressWarnings("deprecation")
			String timeTill = event.getDateTill().getHours() + ":"
					+ event.getDateTill().getMinutes();
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("timeFrom", timeFrom);
			request.setAttribute("timeTill", timeTill);
			request.getRequestDispatcher("pages/events/updateMeeting.jsp")
					.forward(request, response);
		} else {
			logger.warn("No such event type, event id=" + event.getId());
		}

		request.removeAttribute("event");
		request.removeAttribute("imagePath");
		request.removeAttribute("dateFrom");
		request.removeAttribute("dateTill");
		request.removeAttribute("timeFrom");
		request.removeAttribute("timeTill");
	}

	// ?????
	@Request(url = "UpdateExhibition", method = "post")
	public static void exhibitionUpdatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		int eventId = Integer.parseInt(request.getSession()
				.getAttribute("eventId").toString());
		try {
			FileLoader fileLoader = new FileLoader();
			fileLoader.loadFile(request, "event ava");
			HashMap<String, String> map = fileLoader.getParameterMap();

			// event name valid
			if (map.get("name") == null || map.get("name").equals(""))
				throw new WrongInputException("Event name is empty. ");
			else if (map.get("name").length() > 250) {
				throw new WrongInputException("Event name to long. ");
			}

			// event type valid
			if (map.get("type") == null)
				throw new WrongInputException("Event type is empty. ");
			else if (!map.get("type").toString().toUpperCase()
					.equals("MEETING")
					&& !map.get("type").toString().toUpperCase()
							.equals("EXHIBITION"))
				throw new WrongInputException("Event type is incorrect. ");

			// event date valid
			Timestamp dateFrom;
			Timestamp dateTill;
			try {
				dateFrom = DateConverter.convertIntoTimestamp(
						map.get("dateFrom"), "yyyy-MM-dd");
				dateTill = DateConverter.convertIntoTimestamp(
						map.get("dateTill"), "yyyy-MM-dd");
				if (dateTill.getTime() < dateFrom.getTime())
					throw new WrongInputException(
							"Date till must be equals or greater than date from");
			} catch (ParseException e) {
				logger.warn(e);
				throw new WrongInputException("Illigal date format. ");
			}

			// event description valid
			if (map.get("description") == null)
				map.put("description", "");
			else if (map.get("description").length() > 255)
				throw new WrongInputException("Event description is too long. ");

			// event media valid
			Media media = new Media();
			try {
				fileLoader.getAllFilePathes();
				media = new Media();
				media.setName(fileLoader.getDefaultFileName());
				media.setPath(fileLoader.getRelativePath());
				media.setType(MediaType.IMAGE);
				media.setState(State.ACTIVE);
				media = callSaveMedia(media);
			} catch (WrongInputException e) {
				Event event = getEventById(eventId);
				media = getMediaById(event.getAvaId());
			}

			// event state valid
			if (map.get("state") == null || map.get("state") == "")
				throw new WrongInputException("Event state is empty. ");
			else if (!map.get("state").toUpperCase().equals("ACTIVE")
					&& !map.get("state").toUpperCase().equals("BLOCKED"))
				throw new WrongInputException("No such event type. ");

			Event event = new Event();
			event.setId(eventId);
			event.setName(map.get("name"));
			event.setType(EventType.valueOf(map.get("type").toUpperCase()));
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("clubId") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("clubId").toString()));
			event.setDescription(map.get("description"));
			event.setState(State.valueOf(map.get("state").toUpperCase()));
			event.setAvaId(media.getId());
			updateEventById(event);
			request.setAttribute("message", "Event updated. ");

			request.getRequestDispatcher("pages/fedunets12.06/update_event.jsp")
					.forward(request, response);
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/fedunets12.06/update_event.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		}
	}
}