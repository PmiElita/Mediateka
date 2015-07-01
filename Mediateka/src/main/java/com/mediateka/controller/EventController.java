package com.mediateka.controller;

import static com.mediateka.service.ClubEventMemberService.saveClubEventMember;
import static com.mediateka.service.EventService.callSaveEvent;
import static com.mediateka.service.EventService.getEventById;
import static com.mediateka.service.EventService.updateEventById;
import static com.mediateka.service.MediaService.callSaveMedia;
import static com.mediateka.service.MediaService.getMediaById;
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
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.ContentGroupByDate;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ExhibitionRegistrationForm;
import com.mediateka.form.MeetingRegistrationForm;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Event;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubEventMemberService;
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
	public static void goToEventGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ReflectiveOperationException {

		try {
			int eventId = 0;
			if (request.getParameter("eventId") == null || request.getParameter("eventId") == ""
					|| getEventById(Integer.parseInt(request.getParameter("eventId").toString())) == null) {
				request.setAttribute("message", "No such event!");
				request.getRequestDispatcher("error404.jsp").forward(request, response);
				request.removeAttribute("message");
			} else {
				eventId = Integer.parseInt(request.getParameter("eventId").toString());
				Event event = getEventById(eventId);
				List<ContentGroup> records = ContentGroupService.getContentGroupByEventId(eventId);

				Map<Integer, List<Media>> mediaMap = new HashMap<Integer, List<Media>>();
				Map<Integer, List<Media>> imageMap = new HashMap<Integer, List<Media>>();
				Map<Integer, List<Media>> videoMap = new HashMap<Integer, List<Media>>();
				Map<Integer, List<Media>> audioMap = new HashMap<Integer, List<Media>>();
				if (records != null) {
					Collections.sort(records, new ContentGroupByDate());
					for (ContentGroup contentGroup : records) {
						List<Media> images = new ArrayList<Media>();
						List<Media> videos = new ArrayList<Media>();
						List<Media> audios = new ArrayList<Media>();
						mediaMap.put(contentGroup.getId(), MediaService.getMediaContentGroupId(contentGroup.getId()));
						List<Media> medias = MediaService.getMediaContentGroupId(contentGroup.getId());
						System.out.println(medias);
						if (medias != null) {
							for (Media media : medias) {
								System.out.println(media);
								if (media.getType().equals(MediaType.IMAGE)) {
									images.add(media);
								}
								if (media.getType().equals(MediaType.VIDEO)) {
									videos.add(media);
								}
								if (media.getType().equals(MediaType.AUDIO)) {
									audios.add(media);
								}
							}
							if (images != null) {
								if (images.size() > 0) {
									imageMap.put(contentGroup.getId(), images);
								}

							}
							if (videos != null) {
								if (videos.size() > 0) {
									videoMap.put(contentGroup.getId(), videos);
								}

							}
							if (audios != null) {
								if (audios.size() > 0) {
									audioMap.put(contentGroup.getId(), audios);
								}

							}

						} else {
							images = videos = audios = null;
						}

					}
				}

				User user = UserService.getUserById((Integer) request.getSession().getAttribute("userId"));
				String name = user.getFirstName() + " " + user.getLastName();
				System.out.println(mediaMap);
				System.out.println(imageMap);
				System.out.println(videoMap);
				System.out.println(audioMap);
				request.setAttribute("mediaMap", mediaMap);
				request.setAttribute("imageMap", imageMap);
				request.setAttribute("videoMap", videoMap);
				request.setAttribute("audioMap", audioMap);
				request.setAttribute("records", records);
				request.setAttribute("eventId", event.getId());
				request.setAttribute("event", event);
				request.setAttribute("userName", name);

				String isSigned = "false";

				List<ClubEventMember> eventMembers = ClubEventMemberService.getClubEventMemberByEventId(event.getId());
				
				for (ClubEventMember member : eventMembers)
					if (member.getUserId().equals(user.getId()))
						isSigned = "true";

				request.setAttribute("isSigned", isSigned);

				request.getRequestDispatcher("pages/event/event.jsp").forward(request, response);

				request.removeAttribute("mediaMap");
				request.removeAttribute("imageMap");
				request.removeAttribute("videoMap");
				request.removeAttribute("audioMap");
				request.removeAttribute("records");
				request.removeAttribute("event");
				request.removeAttribute("eventId");
				request.removeAttribute("userName");
				request.removeAttribute("isSigned");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("message", "No such club!");
			request.getRequestDispatcher("error404.jsp").forward(request, response);
			request.removeAttribute("message");
		}
	}

	// create event

	@Request(url = "CreateExhibition", method = "get")
	public static void exhibitionCreateGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().setAttribute("userId", 1);
		request.getRequestDispatcher("pages/events/createExhibition.jsp").forward(request, response);

	}

	@Request(url = "CreateMeeting", method = "get")
	public static void meetingCreateGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().setAttribute("userId", 1);
		request.getRequestDispatcher("pages/events/createMeeting.jsp").forward(request, response);

	}

	@SuppressWarnings("deprecation")
	@Request(url = "CreateExhibition", method = "post")
	public static void exhibitionCreatePost(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException, ReflectiveOperationException, ServletException, IOException {

		ExhibitionRegistrationForm form = new ExhibitionRegistrationForm();
		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);

			Timestamp currentTime = new Timestamp(new java.util.Date().getTime());
			currentTime.setHours(0);
			currentTime.setMinutes(0);
			currentTime.setSeconds(0);
			currentTime.setNanos(0);

			Timestamp dateFrom = convertIntoTimestamp(form.getDateFrom(), "dd.MM.yyyy");
			Timestamp dateTill = convertIntoTimestamp(form.getDateTill(), "dd.MM.yyyy");
			if (dateFrom.getTime() <= 0 || dateTill.getTime() <= 0)
				throw new WrongInputException("Date is too big or too small. ");
			if (currentTime.getTime() > dateFrom.getTime())
				throw new WrongInputException("Date from has gone. ");
			if (dateFrom.getTime() > dateTill.getTime())
				throw new WrongInputException("Date from cant be bigger than date till. ");
			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.EXHIBITION);
			event.setDescription(form.getDescription());
			event.setState(State.REQUESTED);
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(request.getSession().getAttribute("club_id").toString()));
			event.setAvaId(2);
			event = callSaveEvent(event);

			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(event.getId());
			clubEventMember.setState(State.ACTIVE);
			clubEventMember.setType(ClubEventMemberType.CREATOR);
			clubEventMember.setUserId((Integer) request.getSession().getAttribute("userId"));
			saveClubEventMember(clubEventMember);
			String message = "Exhibition created. ";

			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/events/createExhibition.jsp").forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/events/createExhibition.jsp").forward(request, response);
			request.removeAttribute("message");
		}
	}

	@SuppressWarnings("deprecation")
	@Request(url = "CreateMeeting", method = "post")
	public static void meetingCreatePost(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException, ReflectiveOperationException, ServletException, IOException {

		MeetingRegistrationForm form = new MeetingRegistrationForm();
		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);

			Timestamp currentTime = new Timestamp(new java.util.Date().getTime());
			currentTime.setSeconds(0);
			currentTime.setNanos(0);

			Timestamp dateFrom = convertIntoTimestamp(form.getDate(), "dd.MM.yyyy");
			Timestamp dateTill = convertIntoTimestamp(form.getDate(), "dd.MM.yyyy");

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
				throw new WrongInputException("Date from cant be biger than date till. ");

			Event event = new Event();
			event.setName(form.getName());
			event.setType(EventType.MEETING);
			event.setDescription(form.getDescription());
			event.setState(State.REQUESTED);
			event.setDateFrom(dateFrom);
			event.setDateTill(dateTill);
			if (request.getSession().getAttribute("club_id") != null)
				event.setClubId(Integer.parseInt(request.getSession().getAttribute("club_id").toString()));
			event.setAvaId(2);
			event = callSaveEvent(event);

			ClubEventMember clubEventMember = new ClubEventMember();
			clubEventMember.setEventId(event.getId());
			clubEventMember.setState(State.ACTIVE);
			clubEventMember.setType(ClubEventMemberType.CREATOR);
			clubEventMember.setUserId((Integer) request.getSession().getAttribute("userId"));
			saveClubEventMember(clubEventMember);
			String message = "Meeting created. ";

			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/events/createMeeting.jsp").forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("pages/events/createMeeting.jsp").forward(request, response);
			request.removeAttribute("message");
		}
	}

	// update event

	@Request(url = "UpdateEvent", method = "get")
	public static void eventUpdateGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ReflectiveOperationException {

		request.setAttribute("message", request.getSession().getAttribute("message"));

		Event event = getEventById(Integer.parseInt(request.getSession().getAttribute("eventId").toString()));
		if (event.getAvaId() != null) {
			Media media = getMediaById(event.getAvaId());
			String imagePath = media.getPath().replace("\\", "/");
			request.setAttribute("imagePath", imagePath);
		}
		request.setAttribute("event", event);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		String dateFrom = format.format(event.getDateFrom());
		if (event.getType() == EventType.EXHIBITION) {
			String dateTill = format.format(event.getDateTill());
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("dateTill", dateTill);
			request.getRequestDispatcher("pages/events/updateExhibition.jsp").forward(request, response);
		} else if (event.getType() == EventType.MEETING) {
			@SuppressWarnings("deprecation")
			String timeFrom = event.getDateFrom().getHours() + ":" + event.getDateFrom().getMinutes();
			@SuppressWarnings("deprecation")
			String timeTill = event.getDateTill().getHours() + ":" + event.getDateTill().getMinutes();
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("timeFrom", timeFrom);
			request.setAttribute("timeTill", timeTill);
			request.getRequestDispatcher("pages/events/updateMeeting.jsp").forward(request, response);
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
	}

	@Request(url = "UpdateExhibition", method = "post")
	public static void exhibitionUpdatePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ReflectiveOperationException {

		int eventId = Integer.parseInt(request.getSession().getAttribute("eventId").toString());

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

			// event date valid
			Timestamp dateFrom;
			Timestamp dateTill;
			Timestamp currentTime = new Timestamp(new java.util.Date().getTime());
			try {
				dateFrom = DateConverter.convertIntoTimestamp(map.get("dateFrom"), "yyyy-MM-dd");
				dateTill = DateConverter.convertIntoTimestamp(map.get("dateTill"), "yyyy-MM-dd");
				if (dateFrom.getTime() < currentTime.getTime())
					throw new WrongInputException("Date from cant be less than current time. ");
				if (dateTill.getTime() < dateFrom.getTime())
					throw new WrongInputException("Date till must be equals or greater than date from");
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
				event.setClubId(Integer.parseInt(request.getSession().getAttribute("clubId").toString()));
			event.setDescription(map.get("description"));
			event.setState(State.valueOf(map.get("state").toUpperCase()));
			event.setAvaId(media.getId());
			updateEventById(event);
			request.getSession().setAttribute("message", "Event updated. ");
			response.sendRedirect("UpdateEvent");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.getSession().setAttribute("message", e.getMessage());
			response.sendRedirect("UpdateEvent");
		}
	}

	@SuppressWarnings("deprecation")
	@Request(url = "UpdateMeeting", method = "post")
	public static void meetingUpdatePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ReflectiveOperationException {

		int eventId = Integer.parseInt(request.getSession().getAttribute("eventId").toString());

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

			// event date valid
			Timestamp dateFrom;
			Timestamp dateTill;
			Timestamp currentTime = new Timestamp(new java.util.Date().getTime());
			currentTime.setSeconds(0);
			currentTime.setNanos(0);

			int[] timeFrom = timeValid(map.get("timeFrom"));
			int[] timeTill = timeValid(map.get("timeTill"));
			try {
				dateFrom = DateConverter.convertIntoTimestamp(map.get("dateFrom"), "dd.MM.yyyy");
				dateTill = DateConverter.convertIntoTimestamp(map.get("dateTill"), "dd.MM.yyyy");
			} catch (ParseException e) {
				logger.warn(e);
				throw new WrongInputException("Illigal date format. ");
			}
			dateFrom.setHours(timeFrom[0]);
			dateFrom.setMinutes(timeFrom[1]);
			dateTill.setHours(timeTill[0]);
			dateTill.setMinutes(timeTill[1]);
			if (dateFrom.getTime() < currentTime.getTime())
				throw new WrongInputException("Date from cant be less than current time. ");
			if (dateTill.getTime() < dateFrom.getTime())
				throw new WrongInputException("Date till must be equals or greater than date from");
			if (dateFrom.getTime() <= 0)
				throw new WrongInputException("Date is too big or too small. ");
			if (currentTime.getTime() > dateFrom.getTime())
				throw new WrongInputException("Date has gone. ");
			if (dateFrom.getTime() > dateTill.getTime())
				throw new WrongInputException("Date from cant be biger than date till. ");

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
				event.setClubId(Integer.parseInt(request.getSession().getAttribute("clubId").toString()));
			event.setDescription(map.get("description"));
			event.setState(State.valueOf(map.get("state").toUpperCase()));
			event.setAvaId(media.getId());
			updateEventById(event);
			request.getSession().setAttribute("message", "Event updated. ");
			response.sendRedirect("UpdateEvent");
		} catch (WrongInputException e) {
			logger.warn(e);
			request.getSession().setAttribute("message", e.getMessage());
			response.sendRedirect("UpdateEvent");
		}
	}

	@Request(url = "activateEvent", method = "get")
	public static void activateEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ReflectiveOperationException, SQLException {

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

		if (eventId == null) {
			return;
		}

		Event event = EventService.getEventById(eventId);

		event.setState(State.ACTIVE);
		EventService.updateEventById(event);

		return;
	}

	@Request(url = "deleteEvent", method = "get")
	public static void deleteEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ReflectiveOperationException, SQLException {

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

		if (eventId == null) {
			return;
		}

		Event event = EventService.getEventById(eventId);

		event.setState(State.DELETED);
		EventService.updateEventById(event);

		return;
	}

}