package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.Club;
import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;

@Controller
@MultipartConfig(location = "/data", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CreateClubController {
	

	
	

	@Request(url = "pages/club/createClub", method = "post")
	public static void createClubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
			
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "club_ava", "1");

		 Club club = new Club();
		 Media media = new Media();
		 media.setType(MediaType.IMAGE);
		 media.setPath(fileLoader.getFilePath());
		 media.setDescription("sadad");
		 media.setState(State.ACTIVE);
		 media.setContentGroupId(1);
		 System.out.println(media.toString());
		 MediaService.saveMedia(media);
		 club.setName(request.getParameter("club_name"));
		 club.setDescription(request.getParameter("club_description"));		
		 ClubService.saveClub(club);
		

		request.getRequestDispatcher("club_home.jsp").forward(request,
				response);

	}


}
