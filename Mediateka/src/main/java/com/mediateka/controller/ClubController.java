package com.mediateka.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.ChatMessageByCreationDate;
import com.mediateka.comparator.ContentGroupByDate;
import com.mediateka.content.CreateContent;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ClubRegistrationForm;
import com.mediateka.model.ChatMessage;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ChatMessageService;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ClubService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

import static com.mediateka.service.ClubEventMemberService.*;
import static com.mediateka.service.ClubService.*;
import static com.mediateka.service.MediaService.getMediaById;

@Controller
public class ClubController {

	private static Logger logger = Logger.getLogger(ClubController.class);

	@Request(url = "createClub", method = "get")
	public static void createClubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/club/create_club.jsp").forward(
				request, response);
	}

	@Request(url = "createClub", method = "post")
	public static void createClubPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		ClubRegistrationForm form = new ClubRegistrationForm();
		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			logger.warn("failed to validate registration form", e);
			response.sendRedirect("index");
			return;
		}

		Club club = new Club();
		ClubEventMember clubEventMember = new ClubEventMember();

		club.setName(form.getName());
		club.setDescription(form.getDescription());
		club.setState(State.REQUESTED);
		club.setAvaId(2);
		club = ClubService.callSaveClub(club);
		clubEventMember.setClubId(club.getId());
		clubEventMember.setUserId(Integer.parseInt(request.getSession()
				.getAttribute("userId").toString()));
		clubEventMember.setState(State.ACTIVE);
		clubEventMember.setType(ClubEventMemberType.CREATOR);
		ClubEventMemberService.saveClubEventMember(clubEventMember);
		File clubDir = new File(request.getServletContext().getRealPath("")
				+ "media\\club\\club" + club.getId());
		clubDir.mkdirs();
		new File(clubDir.getAbsolutePath() + "\\images").mkdir();
		new File(clubDir.getAbsolutePath() + "\\videos").mkdir();
		new File(clubDir.getAbsolutePath() + "\\audios").mkdir();

	}

	@Request(url = "editClub", method = "get")
	public static void editClubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		HttpSession session = request.getSession();
		// change set atribute to atribute from sesion
		Club club = ClubService.getClubById(1);
		session.setAttribute("clubId", club.getId());
		request.setAttribute("club", ClubService.getClubById((Integer) request
				.getSession().getAttribute("clubId")));
		request.setAttribute(
				"clubAva",
				MediaService.getMediaById(club.getAvaId()).getPath()
						.replace("\\", "/"));
		request.getRequestDispatcher("pages/club/editClub.jsp").forward(
				request, response);

	}

	@Request(url = "editClub", method = "post")
	public static void editClubPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		Club club = ClubService.getClubById((Integer) request.getSession()
				.getAttribute("clubId"));
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "club\\club" + club.getId());

		Media media = new Media();

		try {
			fileLoader.getAllFilePathes();
			media.setType(MediaType.IMAGE);
			media.setState(State.ACTIVE);
			media.setPath(fileLoader.getRelativePath());
			media.setName(fileLoader.getDefaultFileName());
			media = MediaService.callSaveMedia(media);
		} catch (WrongInputException e) {
			media = MediaService.getMediaById(club.getAvaId());
		}

		club.setName(fileLoader.getParameterMap().get(("club_name")));
		club.setDescription(fileLoader.getParameterMap().get(
				("club_description")));

		club.setAvaId(media.getId());
		ClubService.updateClub(club);
		request.setAttribute("source", media.getPath().replace("\\", "/"));
		request.getRequestDispatcher("pages/club/club.jsp").forward(request,
				response);

	}

	@Request(url = "loadAlbum", method = "get")
	public static void createAlbumGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("clubId", 1);
		session.setAttribute("userId", 2);
		request.getRequestDispatcher("pages/club/loadAlbum.jsp").forward(
				request, response);

	}

	@Request(url = "loadAlbum", method = "post")
	public static void createAlbumPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		request.getAttribute("clubId");
		CreateContent.createContent(request, response, ContentGroupType.IMAGE);
		request.getRequestDispatcher("pages/club/club.jsp").forward(request,
				response);

	}

	@Request(url = "record", method = "get")
	public static void createRecordGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("clubId", 1);
		session.setAttribute("userId", 2);
		request.getRequestDispatcher("pages/club/record.jsp").forward(request,
				response);

	}

	@Request(url = "record", method = "post")
	public static void createRecordPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getRequestDispatcher("pages/club/club.jsp").forward(request,
				response);
	}

	@Request(url = "club", method = "get")
	public static void clubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		try {
			int clubId = 0;
			if (request.getParameter("clubId") == null
					|| request.getParameter("clubId") == ""
					|| getClubById(Integer.parseInt(request.getParameter(
							"clubId").toString())) == null) {
				request.setAttribute("message", "No such club!");
				request.getRequestDispatcher("error404.jsp").forward(request,
						response);
				request.removeAttribute("message");
			} else {
				clubId = Integer.parseInt(request.getParameter("clubId")
						.toString());
				Club club = getClubById(clubId);
				List<ContentGroup> records = ContentGroupService
						.getContentGroupByClubId(clubId);

				CreateContent.setContent(request, response, records);

				List<ChatMessage> chatMessages = ChatMessageService
						.getChatMessageByClubId(clubId);

				Map<ChatMessage, String> map = new LinkedHashMap<ChatMessage, String>();
				if (chatMessages != null) {
					Collections.sort(chatMessages,
							new ChatMessageByCreationDate());
					for (int i = 0; i < ChatController.MESSAGE_COUNT
							&& i < chatMessages.size(); i++) {
						map.put(chatMessages.get(i),
								UserService.getUserById(
										chatMessages.get(i).getUserId())
										.getFirstName());
					}
				}

				String isSigned = "false";

				List<ClubEventMember> clubMembers = ClubEventMemberService
						.getClubEventMemberByClubId(club.getId());
				if (clubMembers != null)
					for (int i = 0; i < clubMembers.size(); i++)
						if (clubMembers.get(i).getEventId() != null)
							clubMembers.remove(i);

				User user = UserService.getUserById((Integer) request
						.getSession().getAttribute("userId"));
				if (clubMembers != null && user != null)
					for (ClubEventMember member : clubMembers) {
						if (member.getState() == State.ACTIVE
								&& (member.getUserId() == user.getId()))
							isSigned = "true";
						else if ((member.getState() == State.BLOCKED || member
								.getState() == State.DELETED)
								&& (member.getUserId() == user.getId()))
							request.setAttribute("badGuy", true);
					}

				request.setAttribute("imagePath", getMediaById(club.getAvaId())
						.getPath().replace("\\", "/"));
				request.setAttribute("chatMessages", map);
				request.setAttribute("isSigned", isSigned);								
				request.setAttribute("clubId", club.getId());
				request.setAttribute("club", club);				
				request.setAttribute("index", 0);
				request.getRequestDispatcher("pages/club/club.jsp").forward(
						request, response);

				request.removeAttribute("mediaMap");
				request.removeAttribute("imageMap");
				request.removeAttribute("videoMap");
				request.removeAttribute("audioMap");
				request.removeAttribute("records");
				request.removeAttribute("club");
				request.removeAttribute("clubId");
				request.removeAttribute("creatorName");
				request.removeAttribute("imagePath");
				request.removeAttribute("badGuy");
				request.removeAttribute("isSigned");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("message", "No such club!");
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
			request.removeAttribute("message");
		}
	}

	@Request(url = "club_videos", method = "get")
	public static void videosGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/club/club_videos.jsp").forward(
				request, response);
	}

	@Request(url = "activateClub", method = "get")
	public static void activateClub(HttpServletRequest request,
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

		Integer clubId = Integer.parseInt(idString);

		if (clubId == null) {
			return;
		}

		Club club = ClubService.getClubById(clubId);

		club.setState(State.ACTIVE);
		ClubService.updateClub(club);

		return;
	}

	@Request(url = "deleteClub", method = "get")
	public static void deleteClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			response.getWriter().write("");
			return;
		}

		User user = UserService.getUserById(userId);
		if (user == null) {
			logger.error("no user with such id : " + userId);
			response.getWriter().write("");
			return;
		}

		if (user.getRole() != Role.ADMIN) {
			response.getWriter().write("");
			return;
		}

		Integer clubId = Integer.parseInt(request.getParameter("id"));

		if (clubId == null) {
			response.getWriter().write("");
			return;
		}

		Club club = ClubService.getClubById(clubId);

		club.setState(State.DELETED);
		ClubService.updateClub(club);
		response.getWriter().write("");
		return;
	}

	@Request(url = "sendEmailToClubMembers", method = "get")
	public static void sendEmailToClubMembers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		String clubIdString = request.getParameter("clubId");
		if (clubIdString == null) {
			response.sendRedirect("index");
			return;
		}

		Integer clubId = Integer.parseInt(clubIdString);
		if (clubId == null) {
			response.sendRedirect("index");
			return;
		}

		// check if you are the creator of the club

		Integer myId = (Integer) request.getSession().getAttribute("userId");

		if (myId == null) {
			response.sendRedirect("index");
			return;
		}

		List<ClubEventMember> members = ClubEventMemberService
				.getClubEventMemberByClubId(clubId);
		if (members == null) {
			response.sendRedirect("index");
			return;
		}

		boolean IAmTheClubCreator = false;
		for (ClubEventMember member : members) {
			if ((member.getUserId().equals(myId))
					&& (member.getType() == ClubEventMemberType.CREATOR)) {
				IAmTheClubCreator = true;
				break;
			}
		}

		if (!IAmTheClubCreator) {
			response.sendRedirect("index");
			return;
		}
		request.setAttribute("clubId", clubId);
		request.getRequestDispatcher(
				"pages/club/send_email_to_members_form.jsp").forward(request,
				response);
		return;

	}

	@Request(url = "memberSignClub", method = "get")
	public static void memberSignClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		int userId = Integer.parseInt(request.getSession()
				.getAttribute("userId").toString());
		int clubId = 0;
		try {
			clubId = Integer.parseInt(request.getParameter("clubId"));
			Club club = getClubById(clubId);
			if (club == null)
				throw new NumberFormatException();
		} catch (NumberFormatException | IllegalStateException e) {
			logger.error("no club with such id : "
					+ request.getParameter("clubId"));
			response.sendRedirect("index");
			return;
		}
		boolean isMember = false;
		boolean blockedDeleted = false;
		List<ClubEventMember> member = getClubEventMemberByUserId(userId);
		ClubEventMember memberer = new ClubEventMember();

		if (member != null)
			for (int i = 0; i < member.size(); i++)
				if (member.get(i).getEventId() != null)
					member.remove(i);
		if (member != null)
			for (ClubEventMember mem : member) {
				if (mem.getClubId() == clubId && mem.getState() == State.ACTIVE) {
					isMember = true;
					memberer = mem;
				} else if (mem.getClubId() == clubId
						&& mem.getState() == State.UNSIGNED)
					memberer = mem;
				else if (mem.getClubId() == clubId
						&& (mem.getState() == State.BLOCKED || mem.getState() == State.DELETED))
					blockedDeleted = true;
			}
		System.out.println(memberer.getState());
		if (isMember) {
			memberer.setState(State.UNSIGNED);
			updateClubEventMember(memberer);
		} else if (!isMember && memberer.getState() != null
				&& memberer.getState() == State.UNSIGNED) {
			memberer.setState(State.ACTIVE);
			updateClubEventMember(memberer);
		} else if (!blockedDeleted && !isMember) {
			memberer.setClubId(clubId);
			memberer.setState(State.ACTIVE);
			memberer.setType(ClubEventMemberType.MEMBER);
			memberer.setUserId(userId);
			saveClubEventMember(memberer);
		}
		response.sendRedirect("club?clubId=" + clubId);
	}
}