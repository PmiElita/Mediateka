package com.mediateka.controller;

import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByEventId;
import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByUserId;
import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByUserIdAndClubId;
import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByUserIdAndEventId;
import static com.mediateka.service.ClubEventMemberService.saveClubEventMember;
import static com.mediateka.service.ClubEventMemberService.updateClubEventMember;
import static com.mediateka.service.ContentGroupService.getContentGroupByEventId;
import static com.mediateka.service.EventService.callSaveEvent;
import static com.mediateka.service.EventService.getEventById;
import static com.mediateka.service.EventService.updateEvent;
import static com.mediateka.service.MediaService.callSaveMedia;
import static com.mediateka.service.MediaService.getMediaById;
import static com.mediateka.service.UserService.getUserById;
import static com.mediateka.util.DateConverter.convertIntoTimestamp;
import static com.mediateka.util.DateConverter.timeValid;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.UsersByName;
import com.mediateka.content.CreateContent;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ExhibitionRegistrationForm;
import com.mediateka.form.MeetingRegistrationForm;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Event;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.EventService;
import com.mediateka.service.MediaService;
import com.mediateka.service.UserService;
import com.mediateka.util.DateConverter;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

@Controller
public class EventController {

	private static Logger logger = Logger.getLogger(EventController.class);

	@Request(url = "event", method = "get")
	public static void goToEventGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		try {
			int eventId = 0;
			if (request.getParameter("eventId") == null
					|| request.getParameter("eventId") == ""
					|| getEventById(Integer.parseInt(request.getParameter(
							"eventId").toString())) == null
					|| getEventById(
							Integer.parseInt(request.getParameter("eventId")
									.toString())).getState() == State.DELETED) {
				request.setAttribute("message", "No such event!");
				request.getRequestDispatcher("error404.jsp").forward(request,
						response);
				request.removeAttribute("message");
			} else {
				eventId = Integer.parseInt(request.getParameter("eventId")
						.toString());
				Event event = getEventById(eventId);
				List<ContentGroup> records = ContentGroupService
						.getContentGroupByEventIdAndState(eventId, State.ACTIVE);

				CreateContent.setContent(request, response, records);

				request.setAttribute("eventId", event.getId());
				request.setAttribute("event", event);

				String isSigned = "false";
				ClubEventMember member = null;
				if (request.getSession().getAttribute("userId") != null) {
					member = getClubEventMemberByUserIdAndEventId(
							(Integer) request.getSession().getAttribute(
									"userId"), eventId);
					if (member != null
							&& member.getType() == ClubEventMemberType.CREATOR)
						request.setAttribute("creator", "true");
				}
				if (member != null) {
					if (member.getState() == State.ACTIVE)
						isSigned = "true";
					else if (member.getState() == State.BLOCKED
							|| member.getState() == State.DELETED)
						request.setAttribute("badGuy", true);
				}

				List<ContentGroup> albums = getContentGroupByEventId(eventId);
				if (albums == null) {
					request.setAttribute("albums", 0);
					request.setAttribute("videos", 0);
					request.setAttribute("music", 0);
				} else if (albums != null) {
					List<ContentGroup> neededAlbums = new ArrayList<>();
					List<ContentGroup> neededMusic = new ArrayList<>();
					List<ContentGroup> neededVideos = new ArrayList<>();
					for (ContentGroup content : albums) {
						if (content.getState() == State.ACTIVE
								&& content.getType() == ContentGroupType.IMAGE)
							neededAlbums.add(content);
						else if (content.getState() == State.ACTIVE
								&& content.getType() == ContentGroupType.VIDEO)
							neededVideos.add(content);
						else if (content.getState() == State.ACTIVE
								&& content.getType() == ContentGroupType.AUDIO)
							neededVideos.add(content);
					}
					if (neededAlbums.size() != 0)
						request.setAttribute("albums", neededAlbums.size());
					if (neededVideos.size() != 0)
						request.setAttribute("videos", neededVideos.size());
					if (neededMusic.size() != 0)
						request.setAttribute("music", neededMusic.size());
				}

				request.setAttribute(
						"imagePath",
						getMediaById(event.getAvaId()).getPath().replace("\\",
								"/"));
				request.setAttribute("isSigned", isSigned);

				request.getRequestDispatcher("pages/event/event.jsp").forward(
						request, response);

				request.removeAttribute("mediaMap");
				request.removeAttribute("imageMap");
				request.removeAttribute("videoMap");
				request.removeAttribute("audioMap");
				request.removeAttribute("records");
				request.removeAttribute("event");
				request.removeAttribute("eventId");
				request.removeAttribute("userName");
				request.removeAttribute("isSigned");
				request.removeAttribute("imagePath");
				request.removeAttribute("badGuy");
				request.removeAttribute("creator");
				request.removeAttribute("albums");
				request.removeAttribute("videos");
				request.removeAttribute("music");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("message", "No such club!");
			response.sendError(404);
			request.removeAttribute("message");
		}
	}

	// create event

	@Request(url = "CreateExhibition", method = "get")
	public static void exhibitionCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("pages/events/createExhibition.jsp")
				.forward(request, response);

	}

	@Request(url = "CreateMeeting", method = "get")
	public static void meetingCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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
			currentTime.setNanos(0);

			Timestamp dateFrom = convertIntoTimestamp(form.getDateFrom(),
					"dd.MM.yyyy");
			Timestamp dateTill = convertIntoTimestamp(form.getDateTill(),
					"dd.MM.yyyy");
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
			event.setState(State.REQUESTED);
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("club_id").toString()));
			event.setAvaId(1);
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
			response.sendRedirect("events");
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			response.sendError(404);
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
			currentTime.setSeconds(0);
			currentTime.setNanos(0);

			Timestamp dateFrom = convertIntoTimestamp(form.getDate(),
					"dd.MM.yyyy");
			Timestamp dateTill = convertIntoTimestamp(form.getDate(),
					"dd.MM.yyyy");

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
			if (dateFrom.getTime() > dateTill.getTime())
				throw new WrongInputException(
						"Date from cant be biger than date till. ");

			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.MEETING);
			event.setDescription(form.getDescription());
			event.setState(State.REQUESTED);
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("club_id").toString()));
			event.setAvaId(1);
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
			response.sendRedirect("events");
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			response.sendError(404);
			request.removeAttribute("message");
		}
	}

	// update event

	@Request(url = "UpdateEvent", method = "get")
	public static void eventUpdateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		int eventId = 0;
		try {
			if (request.getParameter("eventId") == null)
				throw new WrongInputException("eventId is NULL");
			try {
				eventId = Integer.parseInt(request.getParameter("eventId"));
				if (getEventById(eventId) == null)
					throw new NumberFormatException("No event with such id");
			} catch (NumberFormatException e) {
				throw new WrongInputException(e.getMessage());
			}
			if (request.getSession().getAttribute("userId") == null)
				throw new WrongInputException("Not a user, no user id");
			ClubEventMember member = getClubEventMemberByUserIdAndEventId(
					Integer.parseInt(request.getSession()
							.getAttribute("userId").toString()), eventId);
			if (member == null)
				throw new WrongInputException(
						"This user isnt even a member of this club");
			else if (member.getState() != State.ACTIVE
					&& member.getType() != ClubEventMemberType.CREATOR)
				throw new WrongInputException(
						"This member of event isn't it's active creator");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.getSession().setAttribute("message", e.getMessage());
			response.sendError(404);
			return;
		}

		Event event = getEventById(eventId);
		if (event.getAvaId() != null) {
			Media media = getMediaById(event.getAvaId());
			String imagePath = media.getPath().replace("\\", "/");
			request.setAttribute("imagePath", imagePath);
		}
		request.setAttribute("event", event);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		String dateFrom = format.format(event.getDateFrom());
		request.setAttribute("eventId", eventId);
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
		request.getSession().removeAttribute("message");
		request.removeAttribute("eventId");
	}

	@Request(url = "UpdateExhibition", method = "post")
	public static void exhibitionUpdatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		try {
			int eventId = 0;
			if (request.getParameter("eventId") == null)
				throw new WrongInputException("eventId is NULL");
			try {
				eventId = Integer.parseInt(request.getParameter("eventId"));
				if (getEventById(eventId) == null
						|| getEventById(eventId).getState() == State.DELETED
						|| getEventById(eventId).getType() != EventType.EXHIBITION)
					throw new NumberFormatException("No event with such id");
			} catch (NumberFormatException e) {
				throw new WrongInputException(e.getMessage());
			}
			if (request.getSession().getAttribute("userId") == null)
				throw new WrongInputException("Not a user, no user id");
			ClubEventMember member = getClubEventMemberByUserIdAndEventId(
					Integer.parseInt(request.getSession()
							.getAttribute("userId").toString()), eventId);
			if (member == null)
				throw new WrongInputException(
						"This user isnt even a member of this club");
			else if (member.getState() != State.ACTIVE
					&& member.getType() != ClubEventMemberType.CREATOR)
				throw new WrongInputException(
						"This member of event isn't it's active creator");
			FileLoader fileLoader = new FileLoader();
			fileLoader.loadFile(request, "event ava");
			HashMap<String, String> map = fileLoader.getParameterMap();

			// event name valid
			if (map.get("name") == null || map.get("name").equals(""))
				throw new WrongInputException("Event name is empty. ");
			else if (map.get("name").length() > 250) {
				throw new WrongInputException("Event name to long. ");
			}

			// event date valid
			Timestamp dateFrom;
			Timestamp dateTill;
			Timestamp currentTime = new Timestamp(
					new java.util.Date().getTime());
			try {
				dateFrom = DateConverter.convertIntoTimestamp(
						map.get("dateFrom"), "yyyy-MM-dd");
				dateTill = DateConverter.convertIntoTimestamp(
						map.get("dateTill"), "yyyy-MM-dd");
				if (dateFrom.getTime() < currentTime.getTime())
					throw new WrongInputException(
							"Date from cant be less than current time. ");
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
			event.setType(EventType.EXHIBITION);
			event.setName(map.get("name"));
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("clubId") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("clubId").toString()));
			event.setDescription(map.get("description"));
			event.setState(State.valueOf(map.get("state").toUpperCase()));
			event.setAvaId(media.getId());
			updateEvent(event);
			request.getSession().setAttribute("message", "Event updated. ");
			response.sendRedirect("event?eventId=" + eventId);
		} catch (WrongInputException e) {
			logger.warn(e);
			request.getSession().setAttribute("message", e.getMessage());
			response.sendError(404);
		}
	}

	@SuppressWarnings("deprecation")
	@Request(url = "UpdateMeeting", method = "post")
	public static void meetingUpdatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		try {
			int eventId = 0;
			if (request.getParameter("eventId") == null)
				throw new WrongInputException("eventId is NULL");
			try {
				eventId = Integer.parseInt(request.getParameter("eventId"));
				if (getEventById(eventId) == null
						|| getEventById(eventId).getState() == State.DELETED
						|| getEventById(eventId).getType() != EventType.MEETING)
					throw new NumberFormatException("No event with such id");
			} catch (NumberFormatException e) {
				throw new WrongInputException(e.getMessage());
			}
			if (request.getSession().getAttribute("userId") == null)
				throw new WrongInputException("Not a user, no user id");
			ClubEventMember member = getClubEventMemberByUserIdAndEventId(
					Integer.parseInt(request.getSession()
							.getAttribute("userId").toString()), eventId);
			if (member == null)
				throw new WrongInputException(
						"This user isnt even a member of this club");
			else if (member.getState() != State.ACTIVE
					&& member.getType() != ClubEventMemberType.CREATOR)
				throw new WrongInputException(
						"This member of event isn't it's active creator");
			FileLoader fileLoader = new FileLoader();
			fileLoader.loadFile(request, "event ava");
			HashMap<String, String> map = fileLoader.getParameterMap();

			// event name valid
			if (map.get("name") == null || map.get("name").equals(""))
				throw new WrongInputException("Event name is empty. ");
			else if (map.get("name").length() > 250) {
				throw new WrongInputException("Event name to long. ");
			}

			// event date valid
			Timestamp dateFrom;
			Timestamp dateTill;
			Timestamp currentTime = new Timestamp(
					new java.util.Date().getTime());
			currentTime.setSeconds(0);
			currentTime.setNanos(0);

			int[] timeFrom = timeValid(map.get("timeFrom"));
			int[] timeTill = timeValid(map.get("timeTill"));
			try {
				dateFrom = DateConverter.convertIntoTimestamp(
						map.get("dateFrom"), "dd.MM.yyyy");
				dateTill = DateConverter.convertIntoTimestamp(
						map.get("dateTill"), "dd.MM.yyyy");
			} catch (ParseException e) {
				logger.warn(e);
				throw new WrongInputException("Illigal date format. ");
			}
			dateFrom.setHours(timeFrom[0]);
			dateFrom.setMinutes(timeFrom[1]);
			dateTill.setHours(timeTill[0]);
			dateTill.setMinutes(timeTill[1]);
			if (dateFrom.getTime() < currentTime.getTime())
				throw new WrongInputException(
						"Date from cant be less than current time. ");
			if (dateTill.getTime() < dateFrom.getTime())
				throw new WrongInputException(
						"Date till must be equals or greater than date from");
			if (dateFrom.getTime() <= 0)
				throw new WrongInputException("Date is too big or too small. ");
			if (currentTime.getTime() > dateFrom.getTime())
				throw new WrongInputException("Date has gone. ");
			if (dateFrom.getTime() > dateTill.getTime())
				throw new WrongInputException(
						"Date from cant be biger than date till. ");

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
			event.setType(EventType.MEETING);
			event.setName(map.get("name"));
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("clubId") != null)
				event.setClubId(Integer.parseInt(request.getSession()
						.getAttribute("clubId").toString()));
			event.setDescription(map.get("description"));
			event.setState(State.valueOf(map.get("state").toUpperCase()));
			event.setAvaId(media.getId());
			updateEvent(event);
			request.getSession().setAttribute("message", "Event updated. ");
			response.sendRedirect("event?eventId=" + eventId);
		} catch (WrongInputException e) {
			logger.warn(e);
			request.getSession().setAttribute("message", e.getMessage());
			response.sendError(404);
		}
	}

	@Request(url = "activateEvent", method = "get")
	public static void activateEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			return;
		}

		if (user.getRole() != Role.ADMIN) {
			return;
		}

		String idString = request.getParameter("id");

		if (idString == null) {
			return;
		}

		Integer eventId = Integer.parseInt(idString);

		Event event = EventService.getEventById(eventId);

		event.setState(State.ACTIVE);
		EventService.updateEvent(event);

		return;
	}

	@Request(url = "deleteEvent", method = "get")
	public static void deleteEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			return;
		}

		if (user.getRole() != Role.ADMIN) {
			return;
		}

		System.out.println("5");
		String idString = request.getParameter("id");

		if (idString == null) {
			return;
		}

		Integer eventId = Integer.parseInt(idString);

		Event event = EventService.getEventById(eventId);

		event.setState(State.DELETED);
		EventService.updateEvent(event);

		return;
	}

	@Request(url = "memberSignEvent", method = "get")
	public static void memberSignEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		int userId = Integer.parseInt(request.getSession()
				.getAttribute("userId").toString());
		int eventId = 0;
		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
			Event event = getEventById(eventId);
			if (event == null)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			logger.error("no event with such id : "
					+ request.getParameter("eventId"));
			response.sendError(404);
			return;
		}
		boolean isMember = false;
		boolean blockedDeleted = false;
		List<ClubEventMember> member = getClubEventMemberByUserId(userId);
		ClubEventMember memberer = new ClubEventMember();
		if (member != null)
			for (ClubEventMember mem : member) {
				if (mem.getEventId() != null) {
					if (mem.getEventId() == eventId
							&& mem.getState() == State.ACTIVE) {
						isMember = true;
						memberer = mem;
					} else if (mem.getEventId() == eventId
							&& mem.getState() == State.UNSIGNED)
						memberer = mem;
					else if (mem.getEventId() == eventId
							&& (mem.getState() == State.BLOCKED || mem
									.getState() == State.DELETED))
						blockedDeleted = true;
				}
			}
		if (isMember) {
			memberer.setState(State.UNSIGNED);
			updateClubEventMember(memberer);
		} else if (!isMember && memberer.getState() != null
				&& memberer.getState() == State.UNSIGNED) {
			memberer.setState(State.ACTIVE);
			updateClubEventMember(memberer);
		} else if (!blockedDeleted && !isMember) {
			memberer.setEventId(eventId);
			memberer.setState(State.ACTIVE);
			memberer.setType(ClubEventMemberType.MEMBER);
			memberer.setUserId(userId);
			saveClubEventMember(memberer);
		}
		response.sendRedirect("event?eventId=" + eventId);
	}

	@Request(url = "loadEventAva", method = "post")
	public static void loadClubAvaPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			ReflectiveOperationException, SQLException, WrongInputException {
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request);
		System.out.println(Integer.parseInt(fileLoader.getParameterMap().get(
				"eventId")));
		Event event = EventService.getEventById(Integer.parseInt(fileLoader
				.getParameterMap().get("eventId")));
		Media media = new Media();
		System.out.println(fileLoader.getFilePath());
		media.setType(fileLoader.getMediaType());
		media.setState(State.ACTIVE);
		media.setPath(fileLoader.getRelativePath());
		media.setName(fileLoader.getDefaultFileName());
		media = MediaService.callSaveMedia(media);
		event.setAvaId(media.getId());
		EventService.updateEvent(event);
	}

	@Request(url = "EventUsers", method = "get")
	public static void showEventUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			// watching who is user
			if (request.getParameter("eventId") == null)
				throw new NumberFormatException();
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException(
						"There is no userId in session. ");
			ClubEventMember user = getClubEventMemberByUserIdAndEventId(
					Integer.parseInt(request.getSession()
							.getAttribute("userId").toString()),
					Integer.parseInt(request.getParameter("eventId")));
			if (user == null)
				throw new NumberFormatException(
						"You arent a member of this club. ");
			if (user.getType() == ClubEventMemberType.CREATOR)
				request.setAttribute("creator", true);
			// setting response of users
			List<ClubEventMember> members = getClubEventMemberByEventId(Integer
					.parseInt(request.getParameter("eventId")));
			List<ClubEventMember> eventActiveMembers = new ArrayList<>();
			List<ClubEventMember> eventBlockedMembers = new ArrayList<>();
			if (members != null)
				for (ClubEventMember member : members) {
					if (member.getState() == State.ACTIVE)
						eventActiveMembers.add(member);
					else if (member.getState() == State.BLOCKED)
						eventBlockedMembers.add(member);
				}
			List<User> activeUsers = new ArrayList<>();
			List<User> blockedUsers = new ArrayList<>();
			if (!eventActiveMembers.isEmpty()) {
				for (ClubEventMember member : eventActiveMembers)
					activeUsers.add(getUserById(member.getUserId()));
				Collections.sort(activeUsers, new UsersByName());
			}
			if (!eventBlockedMembers.isEmpty())
				for (ClubEventMember member : eventBlockedMembers) {
					blockedUsers.add(getUserById(member.getUserId()));
					Collections.sort(blockedUsers, new UsersByName());
				}
			request.setAttribute("userId",
					request.getSession().getAttribute("userId"));
			request.setAttribute("eventId", request.getParameter("eventId"));
			if (activeUsers.size() > 0)
				request.setAttribute("activeUsers", activeUsers);
			if (blockedUsers.size() > 0)
				request.setAttribute("blockedUsers", blockedUsers);
			request.getRequestDispatcher("pages/event/event_users.jsp")
					.forward(request, response);
			request.removeAttribute("activeUsers");
			request.removeAttribute("blockedUsers");
			request.removeAttribute("creator");
			request.removeAttribute("clubId");
			request.removeAttribute("userId");
		} catch (NumberFormatException e) {
			logger.error("no event with such id : "
					+ request.getParameter("event") + " " + e.getMessage());
			response.sendError(404);
		}
	}

	@Request(url = "activateEventUser", method = "get")
	public static void activateEventUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException();
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (request.getParameter("id") == null
					|| request.getParameter("eid") == null)
				throw new WrongInputException("There is no id or eid. ");
			int eventId = Integer.parseInt(request.getParameter("eid"));
			if (getEventById(eventId) == null)
				throw new NumberFormatException("No such event");
			ClubEventMember member = getClubEventMemberByUserIdAndEventId(
					userId, eventId);
			if (member == null || member.getState() != State.ACTIVE
					|| member.getType() != ClubEventMemberType.CREATOR)
				throw new NumberFormatException(
						"This user isnt an active creator of this event");
			userId = Integer.parseInt(request.getParameter("id"));
			if (getUserById(userId) == null || getEventById(eventId) == null)
				throw new WrongInputException(
						"Ther is no event or user with such id. ");
			member = getClubEventMemberByUserIdAndClubId(userId, eventId);
			if (member == null)
				throw new NumberFormatException(
						"There is no such member in this event");
			member.setState(State.ACTIVE);
			updateClubEventMember(member);
			return;
		} catch (NumberFormatException | WrongInputException e) {
			logger.error("no user id, or no SUCH user id : "
					+ request.getParameter("userId") + " " + e.getMessage());
			response.sendError(404);
		}
	}

	@Request(url = "deleteEventUser", method = "get")
	public static void deleteEventUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException();
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (request.getParameter("id") == null
					|| request.getParameter("eid") == null)
				throw new WrongInputException("There is no id or eid. ");
			int eventId = Integer.parseInt(request.getParameter("eid"));
			if (getEventById(eventId) == null)
				throw new NumberFormatException("No such event");
			ClubEventMember member = getClubEventMemberByUserIdAndEventId(
					userId, eventId);
			if (member == null || member.getState() != State.ACTIVE
					|| member.getType() != ClubEventMemberType.CREATOR)
				throw new NumberFormatException(
						"This user isnt an active creator of this event");
			userId = Integer.parseInt(request.getParameter("id"));
			if (getUserById(userId) == null || getEventById(eventId) == null)
				throw new WrongInputException(
						"Ther is no event or user with such id. ");
			member = getClubEventMemberByUserIdAndClubId(userId, eventId);
			if (member == null)
				throw new NumberFormatException(
						"There is no such member in this event");
			member.setState(State.DELETED);
			updateClubEventMember(member);
			return;
		} catch (NumberFormatException | WrongInputException e) {
			logger.error("no user id, or no SUCH user id : "
					+ request.getParameter("userId") + " " + e.getMessage());
			response.sendError(404);
		}
	}

	@Request(url = "blockEventUser", method = "get")
	public static void blockEventUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException();
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (request.getParameter("id") == null
					|| request.getParameter("eid") == null)
				throw new WrongInputException("There is no id or eid. ");
			int eventId = Integer.parseInt(request.getParameter("eid"));
			if (getEventById(eventId) == null)
				throw new NumberFormatException("No such event");
			ClubEventMember member = getClubEventMemberByUserIdAndEventId(
					userId, eventId);
			if (member == null || member.getState() != State.ACTIVE
					|| member.getType() != ClubEventMemberType.CREATOR)
				throw new NumberFormatException(
						"This user isnt an active creator of this event");
			userId = Integer.parseInt(request.getParameter("id"));
			if (getUserById(userId) == null || getEventById(eventId) == null)
				throw new WrongInputException(
						"Ther is no event or user with such id. ");
			member = getClubEventMemberByUserIdAndClubId(userId, eventId);
			if (member == null)
				throw new NumberFormatException(
						"There is no such member in this event");
			member.setState(State.BLOCKED);
			updateClubEventMember(member);
			return;
		} catch (NumberFormatException | WrongInputException e) {
			logger.error("no user id, or no SUCH user id : "
					+ request.getParameter("userId") + " " + e.getMessage());
			response.sendError(404);
		}
	}

	@Request(url = "creatorBlockEvent", method = "get")
	public static void creatorBlockEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		creatorChangeEventState(request, response, State.BLOCKED);
	}

	@Request(url = "creatorUnblockEvent", method = "get")
	public static void creatorUnblockEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		creatorChangeEventState(request, response, State.ACTIVE);
	}

	@Request(url = "creatorDeleteEvent", method = "get")
	public static void creatorDeleteEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		creatorChangeEventState(request, response, State.DELETED);
	}

	private static void creatorChangeEventState(HttpServletRequest request,
			HttpServletResponse response, State state) throws ServletException,
			IOException, ReflectiveOperationException, SQLException {

		if (request.getSession().getAttribute("userId") == null) {
			logger.warn("No user id in session");
			response.sendError(404);
			return;
		}
		if (request.getParameter("eventId") == null) {
			logger.warn("No club id in session");
			response.sendError(404);
			return;
		}
		Integer userId = 0;
		Integer eventId = 0;
		Event event = null;
		try {
			userId = (Integer) request.getSession().getAttribute("userId");
			eventId = Integer.parseInt(request.getParameter("clubId"));
			event = getEventById(eventId);
			if (event == null)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			logger.warn("Wrong format of userId or eventId, or no event with such id");
			response.sendError(404);
			return;
		}

		User user = getUserById(userId);
		ClubEventMember member = getClubEventMemberByUserIdAndEventId(userId,
				eventId);

		if (user.getRole() != Role.USER) {
			logger.warn("User role is not user!");
			response.sendError(404);
			return;
		}
		if (member == null || member.getType() != ClubEventMemberType.CREATOR) {
			logger.warn("Members dont have such permissions");
			response.sendError(404);
			return;
		}

		event.setState(state);
		updateEvent(event);
		if (state != State.DELETED)
			response.sendRedirect("event?eventId=" + eventId);
		else if (state == State.DELETED)
			response.sendRedirect("events");
	}
	
	
	@Request(url = "eventAlbums", method = "get")
	public static void eventAlbumsGet(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
		List<ContentGroup> contentGroups = ContentGroupService
				.getContentGroupByEventIdAndStateAndType(eventId, State.ACTIVE,
						ContentGroupType.IMAGE);
		CreateContent.setContent(request, response, contentGroups);
		Event event = EventService.getEventById(eventId);
		request.setAttribute("eventName", event.getName());
		request.setAttribute("eventId", eventId);
		request.getRequestDispatcher("pages/content/albums.jsp").forward(request,
				response);
		request.removeAttribute("eventName");
		request.removeAttribute("eventId");
	}

	@Request(url = "eventVideos", method = "get")
	public static void eventVideosGet(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
		List<ContentGroup> contentGroups = ContentGroupService
				.getContentGroupByEventIdAndStateAndType(eventId, State.ACTIVE,
						ContentGroupType.VIDEO);
		CreateContent.setContent(request, response, contentGroups);
		Event event = EventService.getEventById(eventId);
		request.setAttribute("eventName", event.getName());
		request.setAttribute("eventId", eventId);
		request.getRequestDispatcher("pages/content/videos.jsp").forward(request,
				response);
		request.removeAttribute("eventName");
		request.removeAttribute("eventId");
	}

	@Request(url = "eventAudios", method = "get")
	public static void clubAudiosGet(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
		List<ContentGroup> contentGroups = ContentGroupService
				.getContentGroupByEventIdAndStateAndType(eventId, State.ACTIVE,
						ContentGroupType.AUDIO);
		CreateContent.setContent(request, response, contentGroups);
		Club club = ClubService.getClubById(eventId);
		request.setAttribute("eventName", club.getName());
		request.setAttribute("eventId", eventId);
		request.setAttribute("index", 0);
		request.getRequestDispatcher("pages/content/audios.jsp").forward(request,
				response);
		request.removeAttribute("eventName");
		request.removeAttribute("eventId");
	}
	
	
}