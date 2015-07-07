package com.mediateka.controller;

import static com.mediateka.service.ClubEventMemberService.*;
import static com.mediateka.service.ClubService.*;
import static com.mediateka.service.EventService.*;
import static com.mediateka.service.MediaService.getMediaById;
import static com.mediateka.service.UserService.getUserById;
import static com.mediateka.service.ContentGroupService.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.ChatMessageByCreationDate;
import com.mediateka.comparator.UsersByName;
import com.mediateka.content.CreateContent;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ClubRegistrationForm;
import com.mediateka.model.ChatMessage;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Event;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.pair.ChatMessageUserCardPair;
import com.mediateka.service.ChatMessageService;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ClubService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.service.UserCardService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

@Controller
public class ClubController {

	private static Logger logger = Logger.getLogger(ClubController.class);



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

	// @Request(url = "loadAlbum", method = "get")
	// public static void createAlbumGet(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// HttpSession session = request.getSession();
	// session.setAttribute("clubId", 1);
	// session.setAttribute("userId", 2);
	// request.getRequestDispatcher("pages/club/loadAlbum.jsp").forward(
	// request, response);
	//
	// }

	@Request(url = "loadAlbum", method = "post")
	public static void createAlbumPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {

		CreateContent.createContent(request, response, ContentGroupType.IMAGE);

	}

	@Request(url = "viewNewAlbum", method = "get")
	public static void viewNewAlbumGet(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, ServletException, IOException {
		System.out.println("viewNewAlbum");
		System.out.println("createContent");
		int clubId = 0;
		System.out.println(request.getParameter("albumId"));
		Integer albumId = Integer.parseInt(request.getParameter("albumId"));
		ContentGroup contentGroup = ContentGroupService
				.getContentGroupById(albumId);
		List<ContentGroup> albums = new ArrayList<ContentGroup>();
		albums.add(contentGroup);
		if (contentGroup.getClubId() != null) {
			clubId = contentGroup.getClubId();
		}
		CreateContent.setContent(request, response, albums);

		if (clubId != 0) {
			request.setAttribute("clubId", clubId);
		}

		request.setAttribute("index", request.getParameter("index"));

		request.getRequestDispatcher("pages/club/albumList.jsp").forward(
				request, response);
	}

	@Request(url = "clubAlbums", method = "get")
	public static void ClubAlbumsGet(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		Integer clubId = Integer.parseInt(request.getParameter("clubId"));
		List<ContentGroup> contentGroups = ContentGroupService
				.getContentGroupByClubIdAndStateAndType(clubId, State.ACTIVE,
						ContentGroupType.IMAGE);
		System.out.println(contentGroups);
		CreateContent.setContent(request, response, contentGroups);
		Club club = ClubService.getClubById(clubId);
		request.setAttribute("clubName", club.getName());
		System.out.println("clubId " + clubId);
		request.setAttribute("clubId", clubId);
		request.getRequestDispatcher("pages/club/albums.jsp").forward(request,
				response);
		request.removeAttribute("clubName");
		request.removeAttribute("clubId");
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
							"clubId").toString())) == null
					|| getClubById(
							Integer.parseInt(request.getParameter("clubId")
									.toString())).getState() == State.DELETED) {
				request.setAttribute("message", "No such club!");
				request.getRequestDispatcher("error404.jsp").forward(request,
						response);
				request.removeAttribute("message");
			} else {
				clubId = Integer.parseInt(request.getParameter("clubId")
						.toString());
				Club club = getClubById(clubId);
				List<ContentGroup> records = ContentGroupService
						.getContentGroupByClubIdAndState(clubId, State.ACTIVE);

				CreateContent.setContent(request, response, records);

				List<ChatMessage> chatMessages = ChatMessageService
						.getChatMessageByClubId(clubId, 0,
								ChatController.MESSAGE_COUNT * 2);
				List<ChatMessageUserCardPair> chatMessageUserCardList = new ArrayList<ChatMessageUserCardPair>();

				if (chatMessages != null) {
					Collections.sort(chatMessages,
							new ChatMessageByCreationDate());
					for (int i = 0; i < chatMessages.size(); i++) {
						chatMessageUserCardList
								.add(new ChatMessageUserCardPair(chatMessages
										.get(i), UserCardService
										.getUserCardByUserId(chatMessages
												.get(i).getUserId())));
					}
				}

				String isSigned = "false";
				ClubEventMember member = null;
				if (request.getSession().getAttribute("userId") != null) {
					member = getClubEventMemberByUserIdAndClubId(
							(Integer) request.getSession().getAttribute(
									"userId"), clubId);
					if (member != null
							&& member.getType() == ClubEventMemberType.CREATOR)
						request.setAttribute("creator", "true");
				}

				if (member != null)
					if (member.getState() == State.ACTIVE)
						isSigned = "true";
					else if (member.getState() == State.BLOCKED
							|| member.getState() == State.DELETED)
						request.setAttribute("badGuy", true);

				List<Event> events = getEventByClubId(clubId);
				List<ContentGroup> albums = getContentGroupByClubId(clubId);
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
					request.setAttribute("albums", neededAlbums.size());
					request.setAttribute("videos", neededVideos.size());
					request.setAttribute("music", neededMusic.size());
				}
				if (events == null)
					request.setAttribute("events", 0);
				else if (events != null)
					request.setAttribute("events", events.size());

				request.setAttribute("imagePath", getMediaById(club.getAvaId())
						.getPath().replace("\\", "/"));
				request.setAttribute("chatMessages", chatMessageUserCardList);
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
				request.removeAttribute("creator");
				request.removeAttribute("albums");
				request.removeAttribute("videos");
				request.removeAttribute("music");
				request.removeAttribute("events");
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

		if (request.getParameter("id") == null) {
			response.getWriter().write("");
			return;
		}

		Integer clubId = Integer.parseInt(request.getParameter("id"));

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

		ClubEventMember member = getClubEventMemberByUserIdAndClubId(userId,
				clubId);

		if (member != null) {
			if (member.getState() == State.UNSIGNED) {
				member.setState(State.ACTIVE);
				updateClubEventMember(member);
			} else if (member.getState() == State.ACTIVE) {
				member.setState(State.UNSIGNED);
				updateClubEventMember(member);
			}
		} else if (member == null) {
			member = new ClubEventMember();
			member.setClubId(clubId);
			member.setState(State.ACTIVE);
			member.setType(ClubEventMemberType.MEMBER);
			member.setUserId(userId);
			saveClubEventMember(member);
		}
		response.sendRedirect("club?clubId=" + clubId);
	}

	@Request(url = "loadClubAva", method = "post")
	public static void loadClubAvaPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			ReflectiveOperationException, SQLException, WrongInputException {
		System.out.println("loadAva");
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request);
		Club club = ClubService.getClubById(Integer.parseInt(fileLoader
				.getParameterMap().get("clubId")));
		Media media = new Media();
		System.out.println(fileLoader.getFilePath());
		media.setType(fileLoader.getMediaType());
		media.setState(State.ACTIVE);
		media.setPath(fileLoader.getRelativePath());
		media.setName(fileLoader.getDefaultFileName());
		media = MediaService.callSaveMedia(media);
		club.setAvaId(media.getId());
		ClubService.updateClub(club);
	}

	@Request(url = "ClubUsers", method = "get")
	public static void showClubUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			// watching who is user
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException(
						"There is no userId in session. ");
			ClubEventMember user = getClubEventMemberByUserIdAndClubId(
					Integer.parseInt(request.getSession()
							.getAttribute("userId").toString()),
					Integer.parseInt(request.getParameter("clubId")));
			if (user.getType() == ClubEventMemberType.CREATOR)
				request.setAttribute("creator", true);
			// setting response of users
			if (request.getParameter("clubId") == null)
				throw new NumberFormatException();
			List<ClubEventMember> members = getClubEventMemberByClubId(Integer
					.parseInt(request.getParameter("clubId")));
			List<ClubEventMember> clubActiveMembers = new ArrayList<>();
			List<ClubEventMember> clubBlockedMembers = new ArrayList<>();
			if (members != null)
				for (ClubEventMember member : members) {
					if (member.getEventId() == null
							&& member.getState() == State.ACTIVE)
						clubActiveMembers.add(member);
					else if (member.getEventId() == null
							&& member.getState() == State.BLOCKED)
						clubBlockedMembers.add(member);
				}
			List<User> activeUsers = new ArrayList<>();
			List<User> blockedUsers = new ArrayList<>();
			if (!clubActiveMembers.isEmpty()) {
				for (ClubEventMember member : clubActiveMembers)
					activeUsers.add(getUserById(member.getUserId()));
				Collections.sort(activeUsers, new UsersByName());
			}
			if (!clubBlockedMembers.isEmpty())
				for (ClubEventMember member : clubBlockedMembers) {
					blockedUsers.add(getUserById(member.getUserId()));
					Collections.sort(blockedUsers, new UsersByName());
				}
			request.setAttribute("userId",
					request.getSession().getAttribute("userId"));
			request.setAttribute("clubId", request.getParameter("clubId"));
			if (activeUsers.size() > 0)
				request.setAttribute("activeUsers", activeUsers);
			if (blockedUsers.size() > 0)
				request.setAttribute("blockedUsers", blockedUsers);
			request.getRequestDispatcher("pages/club/club_users.jsp").forward(
					request, response);
			request.removeAttribute("activeUsers");
			request.removeAttribute("blockedUsers");
			request.removeAttribute("creator");
			request.removeAttribute("clubId");
			request.removeAttribute("userId");
		} catch (NumberFormatException e) {
			logger.error("no club with such id : "
					+ request.getParameter("clubId") + " " + e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		}
	}

	@Request(url = "activateClubUser", method = "get")
	public static void activateClubUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException();
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (request.getParameter("id") == null
					|| request.getParameter("cid") == null)
				throw new WrongInputException("There is no id or cid. ");
			userId = Integer.parseInt(request.getParameter("id"));
			int clubId = Integer.parseInt(request.getParameter("cid"));
			if (getUserById(userId) == null || getClubById(clubId) == null)
				throw new WrongInputException(
						"Ther is no club or user with such id. ");
			ClubEventMember member = getClubEventMemberByUserIdAndClubId(
					userId, clubId);
			member.setState(State.ACTIVE);
			updateClubEventMember(member);
			return;
		} catch (NumberFormatException e) {
			logger.error("no user id, or no SUCH user id : "
					+ request.getParameter("userId") + " " + e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		} catch (WrongInputException e) {
			logger.error(e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		}
	}

	@Request(url = "deleteClubUser", method = "get")
	public static void deleteClubUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException();
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (request.getParameter("id") == null
					|| request.getParameter("cid") == null)
				throw new WrongInputException("There is no id or cid. ");
			userId = Integer.parseInt(request.getParameter("id"));
			int clubId = Integer.parseInt(request.getParameter("cid"));
			if (getUserById(userId) == null || getClubById(clubId) == null)
				throw new WrongInputException(
						"Ther is no club or user with such id. ");
			ClubEventMember member = getClubEventMemberByUserIdAndClubId(
					userId, clubId);
			member.setState(State.DELETED);
			updateClubEventMember(member);
			return;
		} catch (NumberFormatException e) {
			logger.error("no user id, or no SUCH user id : "
					+ request.getParameter("userId") + " " + e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		} catch (WrongInputException e) {
			logger.error(e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		}
	}

	@Request(url = "blockClubUser", method = "get")
	public static void blockClubUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				throw new NumberFormatException();
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			if (request.getParameter("id") == null
					|| request.getParameter("cid") == null)
				throw new WrongInputException("There is no id or cid. ");
			userId = Integer.parseInt(request.getParameter("id"));
			int clubId = Integer.parseInt(request.getParameter("cid"));
			if (getUserById(userId) == null || getClubById(clubId) == null)
				throw new WrongInputException(
						"Ther is no club or user with such id. ");
			ClubEventMember member = getClubEventMemberByUserIdAndClubId(
					userId, clubId);
			member.setState(State.BLOCKED);
			updateClubEventMember(member);
			return;
		} catch (NumberFormatException e) {
			logger.error("no user id, or no SUCH user id : "
					+ request.getParameter("userId") + " " + e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		} catch (WrongInputException e) {
			logger.error(e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request,
					response);
		}
	}

	@Request(url = "creatorBlockClub", method = "get")
	public static void creatorBlockClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		creatorChangeClubState(request, response, State.BLOCKED);
	}

	@Request(url = "creatorUnblockClub", method = "get")
	public static void creatorUnblockClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		creatorChangeClubState(request, response, State.ACTIVE);
	}

	@Request(url = "creatorDeleteClub", method = "get")
	public static void creatorDeleteClub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {
		creatorChangeClubState(request, response, State.DELETED);
	}

	private static void creatorChangeClubState(HttpServletRequest request,
			HttpServletResponse response, State state) throws ServletException,
			IOException, ReflectiveOperationException, SQLException {
		if (request.getSession().getAttribute("userId") == null) {
			logger.warn("No user id in session");
			response.sendRedirect("index");
			return;
		}
		if (request.getParameter("clubId") == null) {
			logger.warn("No club id in session");
			response.sendRedirect("index");
			return;
		}
		Integer userId = 0;
		Integer clubId = 0;
		Club club = null;
		try {
			userId = (Integer) request.getSession().getAttribute("userId");
			clubId = Integer.parseInt(request.getParameter("clubId"));
			club = getClubById(clubId);
			if (club == null)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			logger.warn("Wrong format of userId or clubId, or no club with such id");
			response.sendRedirect("index");
			return;
		}

		User user = getUserById(userId);
		ClubEventMember member = getClubEventMemberByUserIdAndClubId(userId,
				clubId);

		if (user.getRole() != Role.USER) {
			logger.warn("User role is not user!");
			response.sendRedirect("index");
			return;
		}
		if (member == null || member.getType() != ClubEventMemberType.CREATOR) {
			logger.warn("Members dont have such permissions");
			response.sendRedirect("index");
			return;
		}

		club.setState(state);
		updateClub(club);
		if (state != State.DELETED)
			response.sendRedirect("club?clubId=" + clubId);
		else if (state == State.DELETED)
			response.sendRedirect("clubs");
	}
}