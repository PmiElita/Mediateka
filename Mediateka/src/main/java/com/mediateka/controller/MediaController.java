package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ClubEventMemberType;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ClubEventMemberService;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;

@Controller
public class MediaController {
	private static Logger logger = Logger.getLogger(MediaController.class);
	@Request(url="loadPhotos", method = "post")
	public static void loadPhotos(HttpServletRequest request,
			HttpServletResponse resposne) throws ServletException,
			WrongInputException, SQLException, ReflectiveOperationException,
			IOException {
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
			Map<String, String> result = new HashMap<String, String>();
			result.put("albumId", albumId.toString());
			resposne.setContentType("application/json");
			resposne.getWriter().write(new Gson().toJson(result));
		}
	}
	
	@Request(url="photos", method="get")
	public static void getPhotos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ReflectiveOperationException, ServletException, IOException{
		Integer albumId = Integer.parseInt(request.getParameter("albumId"));
		Integer userId = null;
		try {
			userId = Integer.parseInt( request.getSession().getAttribute("userId").toString());
		} catch (NullPointerException|NumberFormatException e){
			logger.info("unlogined user get photos", e);
		}
		if (albumId!=null){
			ContentGroup album = ContentGroupService.getContentGroupById(albumId);
			if (album!=null&&album.getType()==ContentGroupType.IMAGE){
			List<Media> photos = MediaService.getMediaByContentGroupId(albumId);
			request.setAttribute("albumId", albumId);
			ClubEventMemberType userMemberType = null;
			if (album.getClubId()!=null){
				if (userId!=null){
				userMemberType = ClubEventMemberService.getClubEventMemberByUserIdAndClubId(userId, album.getClubId()).getType();
				}
				request.setAttribute("clubId", album.getClubId());
			} else if (album.getEventId()!=null){
				if (userId!= null){
				userMemberType = ClubEventMemberService.getClubEventMemberByUserIdAndEventId(userId, album.getEventId()).getType();
				}
				request.setAttribute("eventId", album.getEventId());
			}
			request.setAttribute("memberType", userMemberType);
			request.setAttribute("albumName", album.getName());
			request.setAttribute("photos", photos);
			request.getRequestDispatcher("pages/content/album.jsp").forward(request, response);
			}
		} else {
			response.sendError(404);
		}
		
	}
	
	@Request(url = "viewNewPhoto", method = "get")
	public static void viewNewAlbumGet(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, ServletException, IOException {


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
			request.getRequestDispatcher("pages/content/photos.jsp").forward(request, response);
			}
		} else {
			response.sendError(404);
		}
	}
}
