package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;

@Controller
public class MediaController {

	@Request(url="loadPhotos", method = "post")
	public static void loadPhotos(HttpServletRequest request, HttpServletResponse resposne) throws ServletException, WrongInputException, SQLException, ReflectiveOperationException{
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request);
		Map<String,String> parameterMap=fileLoader.getParameterMap();
		Integer albumId = Integer.parseInt(parameterMap.get("albumId"));
		if (albumId!=null){
			List<String>  pathes = fileLoader.getAllRelativePathes();
			List<String> names = fileLoader.getAllFileDefaultNames();
			for (int i=0; i<pathes.size(); i++){
				Media media = new Media();
				media.setContentGroupId(albumId);
				media.setPath(pathes.get(i));
				media.setState(State.ACTIVE);
				media.setType(MediaType.IMAGE);
				media.setName(names.get(i));
				MediaService.saveMedia(media);
			}
		}
	}
	
	@Request(url="photos", method="get")
	public static void getPhotos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ReflectiveOperationException, ServletException, IOException{
		Integer albumId = Integer.parseInt(request.getParameter("albumId"));
		if (albumId!=null){
			ContentGroup album = ContentGroupService.getContentGroupById(albumId);
			if (album!=null&&album.getType()==ContentGroupType.IMAGE){
			List<Media> photos = MediaService.getMediaByContentGroupId(albumId);
			request.setAttribute("albumId", albumId);
			if (album.getClubId()!=null){
				request.setAttribute("clubId", album.getClubId());
			} else if (album.getEventId()!=null){
				request.setAttribute("eventId", album.getEventId());
			}
			request.setAttribute("photos", photos);
			request.getRequestDispatcher("pages/content/album.jsp").forward(request, response);
			}
		} else {
			response.sendError(404);
		}
		
	}
}
