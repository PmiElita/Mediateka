package com.mediateka.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.ContentGroupByDate;
import com.mediateka.content.CreateContent;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ClubRegistrationForm;
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
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ClubService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

import static com.mediateka.service.ClubService.*;

@Controller
public class ClubController {
	
	
	private static final int defaultClubAvaId = 2; 

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

		HttpSession session = request.getSession();		

		ClubRegistrationForm form = new ClubRegistrationForm();
		ObjectFiller.fill(form, request);

		try {
			FormValidator.validate(form);
		} catch (WrongInputException e) {
			System.out.println(e);
			logger.warn("failed to validate registration form", e);
			response.sendRedirect("index");
			return;
		}

		Club club = new Club();
		ClubEventMember clubEventMember = new ClubEventMember();

		club.setName(form.getName());
		club.setDescription(form.getDescription());
		club.setState(State.BLOCKED);
		club.setAvaId(defaultClubAvaId);
		club = ClubService.callSaveClub(club);
		clubEventMember.setClubId(club.getId());
		clubEventMember.setUserId(Integer.parseInt(session.getAttribute(
				"userId").toString()));
		clubEventMember.setState(State.ACTIVE);
		clubEventMember.setType(ClubEventMemberType.CREATOR);
		ClubEventMemberService.saveClubEventMember(clubEventMember);
		File clubDir = new File(request.getServletContext().getRealPath("")
				+ "media\\club\\club" + club.getId());
		clubDir.mkdirs();
		new File(clubDir.getAbsolutePath() + "\\images").mkdir();
		new File(clubDir.getAbsolutePath() + "\\videos").mkdir();
		new File(clubDir.getAbsolutePath() + "\\audios").mkdir();

//		request.getRequestDispatcher("pages/club/loadAlbum.jsp").forward(
//				request, response);

	}

	@Request(url = "editClub", method = "get")
	public static void editClubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ReflectiveOperationException, SQLException {		
		// change set atribute to atribute from sesion
		Club club = ClubService.getClubById((Integer)(request.getAttribute("clubId")));
		request.setAttribute("clubId", club.getId());
		request.setAttribute("club", club);
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
						mediaMap.put(contentGroup.getId(), MediaService
								.getMediaContentGroupId(contentGroup.getId()));
						List<Media> medias = MediaService
								.getMediaContentGroupId(contentGroup.getId());
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

				User user = UserService.getUserById((Integer) request
						.getSession().getAttribute("userId"));
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
				request.setAttribute("clubId", club.getId());
				request.setAttribute("club", club);
				request.setAttribute("userName", name);

				request.getRequestDispatcher("pages/club/club.jsp").forward(
						request, response);

				request.removeAttribute("mediaMap");
				request.removeAttribute("imageMap");
				request.removeAttribute("videoMap");
				request.removeAttribute("audioMap");
				request.removeAttribute("records");
				request.removeAttribute("club");
				request.removeAttribute("clubId");
				request.removeAttribute("userName");
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

		System.out.println("5");
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
		if (clubId== null) {
			response.sendRedirect("index");
			return;
		}
		
		//check if you are the creator of the club
		
		Integer myId = (Integer) request.getSession().getAttribute("userId");
		
		if (myId == null){
			response.sendRedirect("index");
			return;
		}

		List<ClubEventMember> members = ClubEventMemberService.getClubEventMemberByClubId(clubId);
		if (members == null){
			response.sendRedirect("index");
			return;
		}
		
		boolean IAmTheClubCreator = false;
		for (ClubEventMember member : members){
			if ((member.getUserId().equals(myId)) && (member.getType() == ClubEventMemberType.CREATOR)){
				IAmTheClubCreator = true;
				break;
			}
		}

		if (!IAmTheClubCreator){
			response.sendRedirect("index");
			return;
		}
		request.setAttribute("clubId", clubId);
		request.getRequestDispatcher(
				"pages/club/send_email_to_members_form.jsp").forward(request,
				response);
		return;

	}
}
