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
public class CreateClubController {

	@Request(url = "pages/club/createClub", method = "post")
	public static void createClubPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "club_ava");

		
		Club club = new Club();
		Media media = new Media();

		media.setType(MediaType.IMAGE);
		try {
			media.setPath(fileLoader.getRelativePath());
		} catch (WrongInputException e) {	
			request.setAttribute("message", "wrong file type!");
			request.getRequestDispatcher("create_club.jsp").forward(request, response);		
		}
		
		media.setName(fileLoader.getDefaultFileName());
		media.setState(State.ACTIVE);
		media = MediaService.callSaveMedia(media);
		club.setName(fileLoader.getParameterMap().get(("club_name")));
		club.setDescription(fileLoader.getParameterMap().get(
				("club_description")));
		club.setAvaId(media.getId());
		ClubService.saveClub(club);
		System.out.println(club);
		System.out.println(FileLoader.getServerMedia(request) + media.getPath());
		request.setAttribute("file", new File(FileLoader.getServerMedia(request) + media.getPath()));
		request.setAttribute("source", FileLoader.getServerMedia(request) + media.getPath());
		request.getRequestDispatcher("club_home.jsp")
				.forward(request, response);

	}

}
