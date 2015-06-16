package com.mediateka.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.Club;
import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;

@Controller
public class ClubController {

	@Request(url = "pages/club/createClub", method = "post")
	public static void createClubPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		Club club = new Club();

		club.setName(request.getParameter("club_name"));
		club.setDescription(request.getParameter("club_description"));	
		ClubService.saveClub(club);		
		request.getRequestDispatcher("club_home.jsp")
				.forward(request, response);

	}

	@Request(url = "pages/club/loadAlbum", method = "post")
	public static void createAlbum(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "clubalbum1");
		request.getRequestDispatcher("loadAlbum.jsp")
				.forward(request, response);
	}

}
