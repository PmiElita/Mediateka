package com.mediateka.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import com.mediateka.content.CreateContent;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ClubRegistrationForm;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ClubService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

import static com.mediateka.service.ClubService.*;

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

		HttpSession session = request.getSession();
		session.setAttribute("userId", 1);

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
		club.setAvaId(2);
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
		new File(clubDir.getAbsolutePath() + "\\video").mkdir();
		new File(clubDir.getAbsolutePath() + "\\audios").mkdir();

		request.getRequestDispatcher("pages/club/loadAlbum.jsp").forward(
				request, response);

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
		CreateContent.createContent(request, ContentGroupType.IMAGE);
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
			if (request.getParameter("id") == null
					|| request.getParameter("id") == ""
					|| getClubById(Integer.parseInt(request.getParameter("id")
							.toString())) == null) {
				request.setAttribute("message", "No such club!");
				request.getRequestDispatcher("error404.jsp").forward(request,
						response);
				request.removeAttribute("message");
			} else {
				clubId = Integer
						.parseInt(request.getParameter("id").toString());
				Club club = getClubById(clubId);
				List<ContentGroup> records = ContentGroupService
						.getContentGroupByClubId(clubId);
				Map<Integer, List<Media>> mediaMap = new HashMap<Integer, List<Media>>();
				if (records != null) {
					for (ContentGroup contentGroup : records) {
						mediaMap.put(contentGroup.getId(), MediaService
								.getMediaContentGroupId(contentGroup.getId()));
					}
				}

				request.setAttribute("mediaMap", mediaMap);
				request.setAttribute("records", records);
				request.setAttribute("club", club);

				request.getRequestDispatcher("pages/club/club.jsp").forward(
						request, response);

				request.removeAttribute("mediaMap");
				request.removeAttribute("records");
				request.removeAttribute("club");
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
}
