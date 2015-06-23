package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ExhibitionRegistrationForm;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.Event;
import com.mediateka.model.Media;
import com.mediateka.model.Profession;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ProfessionService;
import com.mediateka.util.DateConverter;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

import static com.mediateka.service.ClubEventMemberService.saveClubEventMember;
import static com.mediateka.service.EventService.*;
import static com.mediateka.service.MediaService.callSaveMedia;
import static com.mediateka.service.MediaService.getMediaById;

@Controller
public class EventController {

	private static Logger logger = Logger.getLogger(EventController.class);

	@Request(url="event", method="get")
	public static void goToEventGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException{
		
		request.getRequestDispatcher("pages/event/event.jsp").forward(request, response);
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

	@Request(url = "CreateExhibition", method = "post")
	public static void exhibitionCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, SQLException,
			ReflectiveOperationException, ServletException, IOException {
		
		ExhibitionRegistrationForm form = new ExhibitionRegistrationForm();
		ObjectFiller.fill(form, request);
		
		try {
			FormValidator.validate(form);

			// e name valid
			if (request.getParameter("name") == null
					|| request.getParameter("name").equals(""))
				throw new WrongInputException("Event name is empty. ");
			else if (request.getParameter("name").length() > 250) {
				throw new WrongInputException("Event name to long. ");
			}

			// event type valid + event date valid
			Timestamp dateFrom = null;
			Timestamp dateTill = null;
			if (request.getParameter("type") == null
					|| request.getParameter("type").equals(""))
				throw new WrongInputException("Event type is empty. ");
			else if (!request.getParameter("type").toUpperCase()
					.equals("MEETING")
					&& !request.getParameter("type").toUpperCase()
							.equals("EXHIBITION"))
				throw new WrongInputException("Event type is incorrect. ");
			else if (request.getParameter("type").toUpperCase()
					.equals("MEETING")) {
				if (request.getParameter("dateFrom") == null
						|| request.getParameter("dateFrom").equals(""))
					throw new WrongInputException("Event date from is empty. ");
				if (request.getParameter("dateTill") == null
						|| request.getParameter("dateTill").equals(""))
					throw new WrongInputException("Event dateTill is empty. ");
				if (request.getParameter("timeFrom") == null
						|| request.getParameter("timeFrom").equals(""))
					try {
						dateFrom = DateConverter.convertIntoTimestamp(
								request.getParameter("dateFrom"), "yyyy-MM-dd");
						dateTill = dateFrom;
						if (!timeValid(request.getParameter("timeFrom"))
								|| !timeValid(request.getParameter("dateTill"))) {
							throw new WrongInputException(
									"Illegal time format. ");
						} else {
							String[] time = request.getParameter("timeFrom")
									.split(":");
							int hour = Integer.parseInt(time[0]);
							int minute = Integer.parseInt(time[1]);
							dateFrom.setHours(hour);
							dateFrom.setMinutes(minute);
							time = request.getParameter("dateTill").split(":");
							hour = Integer.parseInt(time[0]);
							minute = Integer.parseInt(time[1]);
							dateTill.setHours(hour);
							dateTill.setMinutes(minute);
						}
						if (dateTill.getTime() < dateFrom.getTime())
							throw new WrongInputException(
									"Time till must be equals or greater than time from");
					} catch (ParseException e) {
						logger.warn(e);
						throw new WrongInputException("Illigal date format. ");
					}
			} else if (request.getParameter("type").toUpperCase()
					.equals("EXHIBITION")) {
				if (request.getParameter("dateFrom") == null
						|| request.getParameter("dateFrom").equals(""))
					throw new WrongInputException("Event date from is empty. ");
				if (request.getParameter("dateTill") == null
						|| request.getParameter("dateTill").equals(""))
					throw new WrongInputException("Event dateTill is empty. ");
				try {
					dateFrom = DateConverter.convertIntoTimestamp(
							request.getParameter("dateFrom"), "yyyy-MM-dd");
					dateTill = DateConverter.convertIntoTimestamp(
							request.getParameter("dateTill"), "yyyy-MM-dd");
					if (dateTill.getTime() < dateFrom.getTime())
						throw new WrongInputException(
								"Date till must be equals or greater than time from");
				} catch (ParseException e) {
					logger.warn(e);
					throw new WrongInputException("Illigal date format. ");
				}
			}

			// event description valid
			if (request.getParameter("description") == null
					|| request.getParameter("description").equals(""))
				throw new WrongInputException("Event description is empty. ");
			else if (request.getParameter("description").length() > 255)
				throw new WrongInputException("Event description is too long. ");

			Event event = new Event();
			event.setName(request.getParameter("name"));
			event.setType(EventType.valueOf(request.getParameter("type")
					.toUpperCase()));
			event.setDescription(request.getParameter("description"));
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
			String message = "Event created. ";

			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/events/create_event.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/events/create_event.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		}
	}

	// update book

	@Request(url = "UpdateEvent", method = "get")
	public static void eventUpdateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getSession().setAttribute("eventId", 1);
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
		String dateTill = format.format(event.getDateTill());
		request.setAttribute("dateFrom", dateFrom);
		request.setAttribute("dateTill", dateTill);

		request.getRequestDispatcher("pages/fedunets12.06/update_event.jsp")
				.forward(request, response);

		request.removeAttribute("event");
		request.removeAttribute("imagePath");
		request.removeAttribute("dateFrom");
		request.removeAttribute("dateTill");
	}

	@Request(url = "UpdateEvent", method = "post")
	public static void eventUpdatePost(HttpServletRequest request,
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

	private static boolean timeValid(String time) {
		boolean result = true;
		String[] array = time.split(":");
		try {
			int hour = Integer.parseInt(array[0]);
			int minute = Integer.parseInt(array[1]);
			if (hour > 23 || hour < 0 || minute > 59 || minute < 0)
				throw new NumberFormatException();
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			result = false;
		}
		return result;
	}
}