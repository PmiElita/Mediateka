package com.mediateka.controller;

import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByClubId;
import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByEventId;
import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByUserId;
import static com.mediateka.service.ClubService.getClubById;
import static com.mediateka.service.ClubService.getClubByState;
import static com.mediateka.service.EventService.getEventByClubId;
import static com.mediateka.service.EventService.getEventById;
import static com.mediateka.service.EventService.getEventByState;
import static com.mediateka.service.MediaService.getMediaById;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.annotation.Roles;
import com.mediateka.comparator.ClubsByMembersNumber;
import com.mediateka.comparator.EventsByDate;
import com.mediateka.comparator.FormRecordsByDateFrom;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Event;
import com.mediateka.model.FormRecord;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.BookService;
import com.mediateka.service.ClubService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.EventService;
import com.mediateka.service.FormRecordService;
import com.mediateka.service.MediaService;
import com.mediateka.service.ProfessionService;
import com.mediateka.service.ReportService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;

@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Request(url = "events", method = "get")
	public static void eventsGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		if (request.getParameter("clubId") == null) {
			Integer userId = (Integer) request.getSession().getAttribute(
					"userId");

			List<Event> allEvents = getEventByState(State.ACTIVE);
			List<Integer> allEventsMemberNumber = new ArrayList<>();
			if (allEvents != null) {
				Collections.sort(allEvents, new EventsByDate());
				for (Event event : allEvents) {
					if (getClubEventMemberByEventId(event.getId()) == null)
						allEventsMemberNumber.add(0);
					else {
						int k = 0;
						for (ClubEventMember member : getClubEventMemberByEventId(event
								.getId()))
							if (member.getState() == State.ACTIVE)
								k++;
						allEventsMemberNumber.add(k);
						k = 0;
					}
				}
			}

			// all events avas
			List<String> allEventsAvas = new ArrayList<>();
			if (allEvents != null)
				for (Event event : allEvents)
					allEventsAvas.add(getMediaById(event.getAvaId()).getPath()
							.replace("\\", "/"));

			User user = new User();
			user.setRole(Role.UNKNOWN);
			if (userId != null) {
				user = UserService.getUserById(userId);
				if (user == null) {
					logger.error("no user with such id : " + userId);
					response.sendRedirect("index");
					return;
				}
			}
			switch (user.getRole()) {
			case ADMIN:
			case MODERATOR:

				List<Event> requestedEvents = getEventByState(State.REQUESTED);

				List<String> datesRequested = new ArrayList<>();
				List<String> datesAll = new ArrayList<>();
				Timestamp dateFrom;
				Timestamp dateTill;
				String date = "It goes right now!";
				SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
				SimpleDateFormat formatMeeting = new SimpleDateFormat("hh:mm");
				if (requestedEvents != null)
					for (Event event : requestedEvents) {
						dateFrom = event.getDateFrom();
						dateTill = event.getDateTill();
						if (event.getType() == EventType.MEETING)
							date = format.format(dateFrom) + "    "
									+ formatMeeting.format(dateFrom) + "  -  "
									+ formatMeeting.format(dateTill);
						else if (event.getType() == EventType.EXHIBITION)
							date = format.format(dateFrom) + "  -  "
									+ format.format(dateTill);
						datesRequested.add(date);
					}
				if (allEvents != null)
					for (Event event : allEvents) {
						dateFrom = event.getDateFrom();
						dateTill = event.getDateTill();
						if (event.getType() == EventType.MEETING)
							date = format.format(dateFrom) + "    "
									+ formatMeeting.format(dateFrom) + "  -  "
									+ formatMeeting.format(dateTill);
						else if (event.getType() == EventType.EXHIBITION)
							date = format.format(dateFrom) + "  -  "
									+ format.format(dateTill);
						datesAll.add(date);
					}

				request.setAttribute("datesRequested", datesRequested);
				request.setAttribute("datesAll", datesAll);
				request.setAttribute("requestedEvents", requestedEvents);
				request.setAttribute("allEvents", allEvents);
				request.getRequestDispatcher("pages/table/admin_events.jsp")
						.forward(request, response);
				request.removeAttribute("requestedEvents");
				request.removeAttribute("allEvents");
				request.removeAttribute("datesRequested");
				request.removeAttribute("datesAll");
				break;

			case USER:
				// my events
				List<ClubEventMember> iMemberOfEvents = getClubEventMemberByUserId(userId);
				List<Event> myEvents = new ArrayList<>();
				if (iMemberOfEvents != null)
					for (ClubEventMember member : iMemberOfEvents)
						if (member.getEventId() != null
								&& member.getState() == State.ACTIVE
								&& getEventById(member.getEventId()) != null)
							myEvents.add(getEventById(member.getEventId()));
				Collections.sort(myEvents, new EventsByDate());
				List<Event> myActiveEvents = new ArrayList<>();
				List<Event> myBlockedEvents = new ArrayList<>();
				List<Event> myRequestedEvents = new ArrayList<>();
				List<Integer> myActiveEventsMemberNumber = new ArrayList<>();
				for (Event event : myEvents) {
					if (event.getState() == State.ACTIVE) {
						myActiveEvents.add(event);
						if (getClubEventMemberByEventId(event.getId()) == null)
							myActiveEventsMemberNumber.add(0);
						else
							myActiveEventsMemberNumber
									.add(getClubEventMemberByEventId(
											event.getId()).size());
					} else if (event.getState() == State.BLOCKED)
						myBlockedEvents.add(event);
					else if (event.getState() == State.REQUESTED)
						myRequestedEvents.add(event);
				}

				// all events
				if (allEvents != null)
					Collections.sort(allEvents, new EventsByDate());

				// my events avas
				List<String> myActiveEventsAvas = new ArrayList<>();
				List<String> myBlockedEventsAvas = new ArrayList<>();
				List<String> myRequestedEventsAvas = new ArrayList<>();
				for (Event event : myActiveEvents)
					myActiveEventsAvas.add(getMediaById(event.getAvaId())
							.getPath().replace("\\", "/"));
				for (Event event : myBlockedEvents)
					myBlockedEventsAvas.add(getMediaById(event.getAvaId())
							.getPath().replace("\\", "/"));
				for (Event event : myRequestedEvents)
					myRequestedEventsAvas.add(getMediaById(event.getAvaId())
							.getPath().replace("\\", "/"));

				request.setAttribute("allEventsMemberNumber",
						allEventsMemberNumber);
				request.setAttribute("myActiveEventsMemberNumber",
						myActiveEventsMemberNumber);
				request.setAttribute("myActiveEvents", myActiveEvents);
				request.setAttribute("myBlockedEvents", myBlockedEvents);
				request.setAttribute("myRequestedEvents", myRequestedEvents);
				request.setAttribute("myActiveEventsAvas", myActiveEventsAvas);
				request.setAttribute("myBlockedEventsAvas", myBlockedEventsAvas);
				request.setAttribute("myRequestedEventsAvas",
						myBlockedEventsAvas);
				request.setAttribute("allEvents", allEvents);
				request.setAttribute("allEventsAvas", allEventsAvas);
				request.getRequestDispatcher("pages/events/events.jsp")
						.forward(request, response);
				request.removeAttribute("myActiveEvents");
				request.removeAttribute("myBlockedEvents");
				request.removeAttribute("myRequestedEvents");
				request.removeAttribute("myActiveEventsAvas");
				request.removeAttribute("myBlockedEventsAvas");
				request.removeAttribute("myRequestedEventsAvas");
				request.removeAttribute("allEvents");
				request.removeAttribute("allEventsAvas");
				request.removeAttribute("allEventsMemberNumber");
				request.removeAttribute("myActiveEventsMemberNumber");

				break;

			default:
				request.setAttribute("allEventsMemberNumber",
						allEventsMemberNumber);
				request.setAttribute("allEvents", allEvents);
				request.setAttribute("allEventsAvas", allEventsAvas);
				request.setAttribute("professions",
						ProfessionService.getProfessionAll());
				request.getRequestDispatcher("pages/events/events.jsp")
						.forward(request, response);
				request.removeAttribute("allEvents");
				request.removeAttribute("allEventsAvas");
				request.removeAttribute("allEventsMemberNumber");
				break;
			}
		} else if (request.getParameter("clubId") != null) {
			int clubId = 0;
			try {
				clubId = Integer.parseInt(request.getParameter("clubId"));
				Club club = getClubById(clubId);
				if (club == null)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				logger.error("no club has such id : "
						+ request.getParameter("clubId"));
				response.sendError(404);
				return;
			}
			List<Event> eventss = getEventByClubId(clubId);
			List<String> avas = new ArrayList<>();
			List<Event> events = new ArrayList<>();
			if (events != null) {
				for (int i = 0; i < eventss.size(); i++)
					if (eventss.get(i).getState() != State.ACTIVE)
						events.add(eventss.get(i));
				Collections.sort(events, new EventsByDate());
				for (Event event : events)
					avas.add(getMediaById(event.getAvaId()).getPath().replace(
							"\\", "/"));
			}
			List<Integer> eventsMemberNumber = new ArrayList<>();
			if (!events.isEmpty()) {
				for (Event event : events) {
					if (getClubEventMemberByEventId(event.getId()) == null)
						eventsMemberNumber.add(0);
					else {
						int k = 0;
						for (ClubEventMember member : getClubEventMemberByEventId(event
								.getId()))
							if (member.getState() == State.ACTIVE)
								k++;
						eventsMemberNumber.add(k);
						k = 0;
					}
				}
			}

			request.setAttribute("allEventsMemberNumber", eventsMemberNumber);
			request.setAttribute("allEvents", events);
			request.setAttribute("allEventsAvas", avas);
			request.setAttribute("professions",
					ProfessionService.getProfessionByState(State.ACTIVE));
			request.getRequestDispatcher("pages/events/events.jsp").forward(
					request, response);
			request.removeAttribute("allEvents");
			request.removeAttribute("all|EventsAvas");
			request.removeAttribute("allEventsMemberNumber");
		}
	}

	@Request(url = "clubs", method = "get")
	public static void clubsGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException, ReflectiveOperationException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		// all clubs
		List<Club> allClubs = getClubByState(State.ACTIVE);
		List<Integer> clubsMemberNumber = new ArrayList<>();
		if (allClubs != null) {
			Collections.sort(allClubs, new ClubsByMembersNumber());
			for (Club club : allClubs) {
				if (getClubEventMemberByClubId(club.getId()) == null)
					clubsMemberNumber.add(0);
				else {
					int k = 0;
					for (ClubEventMember member : getClubEventMemberByClubId(club
							.getId()))
						if (member.getState() == State.ACTIVE)
							k++;
					clubsMemberNumber.add(k);
					k = 0;
				}
			}
		}

		// all clubs avas
		List<String> allClubsAvas = new ArrayList<>();
		if (allClubs != null)
			for (Club club : allClubs) {
				allClubsAvas.add(getMediaById(club.getAvaId()).getPath()
						.replace("\\", "/"));
			}

		User user = new User();
		user.setRole(Role.UNKNOWN);
		if (userId != null) {
			user = UserService.getUserById(userId);
			if (user == null) {
				logger.error("no user with such id : " + userId);
				response.sendRedirect("index");
				return;
			}
		}

		if ((user.getRole() == Role.ADMIN)
				|| (user.getRole() == Role.MODERATOR)) {
			List<Club> requestedClubs = getClubByState(State.REQUESTED);

			request.setAttribute("clubsMemberNumber", clubsMemberNumber);
			request.setAttribute("requestedClubs", requestedClubs);
			request.setAttribute("allClubs", allClubs);

			request.getRequestDispatcher("pages/table/admin_clubs.jsp")
					.forward(request, response);

			request.removeAttribute("allClubs");
			request.removeAttribute("requestedClubs");
			request.removeAttribute("clubsMemberNumber");
			return;

		} else if (user.getRole() == Role.USER) {
			// my clubs
			List<ClubEventMember> memberer = getClubEventMemberByUserId(Integer
					.parseInt(request.getSession().getAttribute("userId")
							.toString()));
			List<ClubEventMember> clubMemberer = new ArrayList<>();
			if (memberer != null)
				for (ClubEventMember member : memberer)
					if (member.getClubId() != null
							&& member.getState() == State.ACTIVE)
						clubMemberer.add(member);
			List<Club> myClubs = new ArrayList<>();
			for (ClubEventMember member : clubMemberer) {
				myClubs.add(getClubById(member.getClubId()));
			}
			Collections.sort(myClubs, new ClubsByMembersNumber());
			List<Club> myActiveClubs = new ArrayList<>();
			List<Club> myBlockedClubs = new ArrayList<>();
			List<Club> myRequestedClubs = new ArrayList<>();
			List<Integer> myActiveClubsMemberNumber = new ArrayList<>();
			for (Club club : myClubs) {
				if (club.getState() == State.ACTIVE) {
					myActiveClubs.add(club);
					if (getClubEventMemberByClubId(club.getId()) == null)
						myActiveClubsMemberNumber.add(0);
					else
						myActiveClubsMemberNumber
								.add(getClubEventMemberByClubId(club.getId())
										.size());
				} else if (club.getState() == State.BLOCKED)
					myBlockedClubs.add(club);
				else if (club.getState() == State.REQUESTED)
					myRequestedClubs.add(club);
			}

			// my clubs avas
			List<String> myActiveClubsAvas = new ArrayList<>();
			List<String> myBlockedClubsAvas = new ArrayList<>();
			List<String> myRequestedClubsAvas = new ArrayList<>();
			for (Club club : myActiveClubs)
				myActiveClubsAvas.add(getMediaById(club.getAvaId()).getPath()
						.replace("\\", "/"));
			for (Club club : myBlockedClubs)
				myBlockedClubsAvas.add(getMediaById(club.getAvaId()).getPath()
						.replace("\\", "/"));
			for (Club club : myRequestedClubs)
				myRequestedClubsAvas.add(getMediaById(club.getAvaId())
						.getPath().replace("\\", "/"));

			request.setAttribute("myActiveClubsMemberNumber",
					myActiveClubsMemberNumber);
			request.setAttribute("clubsMemberNumber", clubsMemberNumber);
			request.setAttribute("allClubs", allClubs);
			request.setAttribute("allClubsAvas", allClubsAvas);
			request.setAttribute("myActiveClubsAvas", myActiveClubsAvas);
			request.setAttribute("myBlockedClubsAvas", myBlockedClubsAvas);
			request.setAttribute("myRequestedClubsAvas", myRequestedClubsAvas);
			request.setAttribute("myActiveClubs", myActiveClubs);
			request.setAttribute("myBlockedClubs", myBlockedClubs);
			request.setAttribute("myRequestedClubs", myRequestedClubs);
			request.getRequestDispatcher("pages/clubs/clubs.jsp").forward(
					request, response);
			request.removeAttribute("myActiveClubs");
			request.removeAttribute("myBlockedClubs");
			request.removeAttribute("myRequestedClubs");
			request.removeAttribute("myActiveClubsAvas");
			request.removeAttribute("myRequestededClubsAvas");
			request.removeAttribute("myActiveClubs");
			request.removeAttribute("allClubs");
			request.removeAttribute("allClubsAvas");
			request.removeAttribute("clubsMemberNumber");
			request.removeAttribute("myActiveClubsMemberNumber");
		} else {
			request.setAttribute("clubsMemberNumber", clubsMemberNumber);
			request.setAttribute("allClubs", allClubs);
			request.setAttribute("allClubsAvas", allClubsAvas);
			request.setAttribute("professions",
					ProfessionService.getProfessionAll());
			request.getRequestDispatcher("pages/clubs/clubs.jsp").forward(
					request, response);
			request.removeAttribute("allClubs");
			request.removeAttribute("allClubsAvas");
			request.removeAttribute("clubsMemberNumber");
		}
	}

	@Request(url = "cabinet", method = "get")
	@Roles({ Role.USER, Role.ADMIN })
	public static void cabinetGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			response.sendError(404);
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			response.sendRedirect("index");
			return;
		}

		switch (user.getRole()) {
		case ADMIN:
		case MODERATOR:
			request.setAttribute("professions",
					ProfessionService.getProfessionAll());
			request.setAttribute("requestedClubCount",
					ClubService.getNumberOfRequestedClubs());
			request.setAttribute("requestedEventCount",
					EventService.getNumberOfRequestedEvents());
			request.setAttribute("totalReports",
					ReportService.getNumberOfAllReports());
			request.setAttribute("newReports",
					ReportService.getNumberOfNewReports());
			request.setAttribute("usersCount",
					UserService.getNotDeletedUsersCount());

			request.setAttribute("booksCount",
					BookService.getNotDeletedBooksCount());

			request.setAttribute("clubsCount",
					ClubService.getNotDeletedClubsCount());

			request.setAttribute("eventsCount",
					EventService.getNotDeletedEventsCount());

			request.getRequestDispatcher("pages/admin/admin.jsp").forward(
					request, response);
			break;

		case USER:

			List<FormRecord> formRecords = FormRecordService
					.getFormRecordsByUserIdLimited(userId, 0, 10);
			if (formRecords != null) {

				Collections.sort(formRecords, new FormRecordsByDateFrom());
			}
			request.setAttribute("formRecords", formRecords);
			request.setAttribute("imagePath",
					MediaService.getMediaById(user.getAvaId()).getPath()
							.replace("\\", "/"));
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("middleName", user.getMiddleName());
			request.setAttribute("nationality", user.getNationality());
			request.setAttribute("education", user.getEducation());
			request.setAttribute("eduInstitution", user.getEduInstitution());
			String birthDate = new SimpleDateFormat("dd.MM.yyyy").format(user
					.getBirthDate());
			request.setAttribute("birthDate", birthDate);
			request.setAttribute("address", user.getAdress());
			request.setAttribute("phone", user.getPhone());
			request.setAttribute("profession", ProfessionService
					.getProfessionById(user.getProfessionId()).getName());

			request.getRequestDispatcher("pages/user/user.jsp").forward(
					request, response);
			break;

		default:
			logger.error("unknown user role " + user.getRole().toString());
			response.sendRedirect("index");
			break;
		}
	}

	@Request(url = "info", method = "get")
	@Roles({ Role.ADMIN })
	public static void infoGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new WrongInputException("There is no userId in session");
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (UserService.getUserById(userId).getRole() != Role.ADMIN)
				throw new WrongInputException("This user isnt an admin");
			List<ContentGroup> infos = ContentGroupService
					.getContentGroupByType(ContentGroupType.INFO);
			int infoId = infos.get(0).getId();
			List<ContentGroup> all = ContentGroupService
					.getContentGroupByParentId(infoId);
			List<Media> media = new ArrayList<>();
			List<String[]> photoTexts = new ArrayList<>();
			for (ContentGroup content : all) {
				media.add(MediaService
						.getMediaByContentGroupId(content.getId()).get(0));
				photoTexts.add(content.getText().split("<info!split!info>"));
			}
			List<String> imagePath = new ArrayList<>();
			for (Media image : media)
				imagePath.add(image.getPath().replace("\\", "/"));
			String[] textInfo = infos.get(0).getText()
					.split("<info!split!info>");
			request.setAttribute("imagePath1", imagePath.get(0));
			request.setAttribute("imagePath2", imagePath.get(1));
			request.setAttribute("imagePath3", imagePath.get(2));
			request.setAttribute("ua1", photoTexts.get(0)[0]);
			request.setAttribute("en1", photoTexts.get(0)[1]);
			request.setAttribute("ua2", photoTexts.get(1)[0]);
			request.setAttribute("en2", photoTexts.get(1)[1]);
			request.setAttribute("ua3", photoTexts.get(2)[0]);
			request.setAttribute("en3", photoTexts.get(2)[1]);
			request.setAttribute("text1", textInfo[0]);
			request.setAttribute("text2", textInfo[1]);
			if (request.getSession().getAttribute("goody") != null) {
				request.setAttribute("good", "not_null");
				request.getSession().setAttribute("goody", null);
			}
			request.getRequestDispatcher("pages/admin/info.jsp").forward(
					request, response);
			request.removeAttribute("good");
			request.removeAttribute("imagePath1");
			request.removeAttribute("imagePath2");
			request.removeAttribute("imagePath3");
			request.removeAttribute("text1");
			request.removeAttribute("text2");
			request.removeAttribute("ua1");
			request.removeAttribute("ua2");
			request.removeAttribute("ua3");
			request.removeAttribute("en1");
			request.removeAttribute("en2");
			request.removeAttribute("en3");
		} catch (WrongInputException e) {
			logger.warn(e.getMessage());
			response.sendError(404);
		}
	}

	@Request(url = "updateInfo", method = "post")
	@Roles({ Role.ADMIN })
	public static void infoPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "info image");
		Map<String, String> map = fileLoader.getParameterMap();
		List<ContentGroup> infos = ContentGroupService
				.getContentGroupByType(ContentGroupType.INFO);
		ContentGroup info = infos.get(0);
		String textUa = whiteSpaceReturner(map, "infoText1");
		String textEn = whiteSpaceReturner(map, "infoText2");
		info.setText(textUa + "<info!split!info>" + textEn);
		ContentGroupService.updateContentGroup(info);
		List<ContentGroup> all = ContentGroupService
				.getContentGroupByParentId(info.getId());
		String ua1 = whiteSpaceReturner(map, "ua1");
		String ua2 = whiteSpaceReturner(map, "ua2");
		String ua3 = whiteSpaceReturner(map, "ua3");
		String en1 = whiteSpaceReturner(map, "en1");
		String en2 = whiteSpaceReturner(map, "en2");
		String en3 = whiteSpaceReturner(map, "en3");
		all.get(0).setText(ua1 + "<info!split!info>" + en1);
		all.get(1).setText(ua2 + "<info!split!info>" + en2);
		all.get(2).setText(ua3 + "<info!split!info>" + en3);
		for (ContentGroup content : all)
			ContentGroupService.updateContentGroup(content);
		List<Media> media = new ArrayList<>();
		for (ContentGroup content : all)
			media.add(MediaService.getMediaByContentGroupId(content.getId())
					.get(0));
		try {
			if (fileLoader.getAllFilePathes() != null) {
				Map<String, String> imgMap = fileLoader
						.getAllRelativePathseMap();
				System.out.println(imgMap);
				if (imgMap.containsKey("image1")) {
					media.get(0).setPath(imgMap.get("image1"));
					MediaService.updateMedia(media.get(0));
				}
				if (imgMap.containsKey("image2")) {
					media.get(1).setPath(imgMap.get("image2"));
					MediaService.updateMedia(media.get(1));
				}
				if (imgMap.containsKey("image3")) {
					media.get(2).setPath(imgMap.get("image3"));
					MediaService.updateMedia(media.get(2));
				}
			}
		} catch (WrongInputException e) {
			logger.warn(e);
			response.sendError(404);
			return;
		}

		request.getSession().setAttribute("goody", "not_null");
		response.sendRedirect("cabinet");
	}

	@Request(url = "post_register", method = "get")
	public static void postRegisterGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/additional/post_register.jsp")
				.forward(request, response);
	}

	@Request(url = "userEventAva", method = "post")
	public static void loadClubAvaPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			ReflectiveOperationException, SQLException, WrongInputException {
		System.out.println("loadAva");
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request);
		User user = UserService.getUserById((Integer) request.getSession()
				.getAttribute("userId"));
		Media media = new Media();
		System.out.println(fileLoader.getFilePath());
		media.setType(fileLoader.getMediaType());
		media.setState(State.ACTIVE);
		media.setPath(fileLoader.getRelativePath());
		media.setName(fileLoader.getDefaultFileName());
		media = MediaService.callSaveMedia(media);
		user.setAvaId(media.getId());
		UserService.updateUser(user);
	}

	private static String whiteSpaceReturner(Map<String, String> map,
			String attributeName) {
		String result;
		if (map.get(attributeName) == null || map.get(attributeName).equals(""))
			result = " ";
		else
			result = map.get(attributeName);
		return result;
	}

}