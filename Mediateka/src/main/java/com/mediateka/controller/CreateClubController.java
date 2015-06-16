package com.mediateka.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.Club;
import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.service.ClubService;

@Controller
public class CreateClubController {

	@Request(url = "pages/club/createClub", method = "post")
	public static void createClubGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		final String SAVE_DIR = "uploadFiles";
		
		String appPath = request.getServletContext().getRealPath("avaClub");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + File.separator + SAVE_DIR;
		
		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		
//		for (Part part : request.getParts()) {
//			String fileName = extractFileName(part);
//			part.write(savePath + File.separator + fileName);
//		}

		request.setAttribute("message", "Upload has been done successfully!");
		request.getRequestDispatcher("club_home.jsp").forward(
				request, response);
	


//		Club club = new Club();
//		Media media = new Media();
//		media.setType(MediaType.IMAGE);
//		club.setName(request.getParameter("club_name"));
//		club.setDescription(request.getParameter("club_description"));
//
//		ClubService.saveClub(club);

	}
	
/**
 * Extracts file name from HTTP header content-disposition
 */
private static String extractFileName(Part part) {
	String contentDisp = part.getHeader("content-disposition");
	String[] items = contentDisp.split(";");
	for (String s : items) {
		if (s.trim().startsWith("filename")) {
			return s.substring(s.indexOf("=") + 2, s.length()-1);
		}
	}
	return "";
}

}
