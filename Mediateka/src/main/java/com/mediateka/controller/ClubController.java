package com.mediateka.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.ClubRegistrationForm;
import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ClubService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

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
		club.setAvaId(1);
		club = ClubService.callSaveClub(club);
		clubEventMember.setClubId(club.getId());
		clubEventMember.setUserId(Integer.parseInt(session.getAttribute(
				"userId").toString()));
		clubEventMember.setState(State.ACTIVE);
		clubEventMember.setType(ClubEventMemberType.CREATOR);
		ClubEventMemberService.saveClubEventMember(clubEventMember);
		File clubDir = new File(request.getServletContext().getRealPath("")
				+ "media\\club\\club#" + club.getId());
		clubDir.mkdirs();
		new File(clubDir.getAbsolutePath() + "\\images").mkdir();
		new File(clubDir.getAbsolutePath() + "\\video").mkdir();
		new File(clubDir.getAbsolutePath() + "\\audios").mkdir();

		request.getRequestDispatcher("pages/club/loadAlbum.jsp").forward(
				request, response);

	}

	@Request(url = "editClub", method = "get")
	public static void editClubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ReflectiveOperationException, SQLException {
		HttpSession session = request.getSession();
		//change set atribute to atribute from sesion
		Club club = ClubService.getClubById(5);
		session.setAttribute("club", club);
		request.setAttribute("clubAva", MediaService.getMediaById(club.getAvaId()).getPath().replace("\\", "/"));
		request.getRequestDispatcher("pages/club/editClub.jsp").forward(
				request, response);

	}

	@Request(url = "editClub", method = "post")
	public static void editClubPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		String message = "wrong input data";
		try {
		
			FileLoader fileLoader = new FileLoader();
			fileLoader.loadFile(request, "club_ava");

			Media media = new Media();
			
			Club club = (Club) request.getSession().getAttribute("club");

			media.setType(MediaType.IMAGE);
			media.setPath(fileLoader.getRelativePath());

			media.setName(fileLoader.getDefaultFileName());
			media.setState(State.ACTIVE);
			media = MediaService.callSaveMedia(media);
			club.setName(fileLoader.getParameterMap().get(("club_name")));
			club.setDescription(fileLoader.getParameterMap().get(
					("club_description")));
			club.setAvaId(media.getId());
			ClubService.updateClub(club);
			//ClubService.saveClub(club);
			System.out.println(club);
			System.out.println(media.getPath());
			request.setAttribute("source", media.getPath().replace("\\", "/"));
			request.getRequestDispatcher("club_home.jsp").forward(request,
					response);

		} catch (WrongInputException e) {
			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/club/club.jsp").forward(request, response);
		}

	}

	@Request(url = "pages/club/loadAlbum", method = "post")
	public static void createAlbum(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
	
	@Request(url = "club", method = "get")
	public static void clubGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.getRequestDispatcher("pages/club/club.jsp").forward(request, response);
	}

	@Request(url = "club_videos", method = "get")
	public static void videosGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.getRequestDispatcher("pages/club/club_videos.jsp").forward(request, response);
	}}
