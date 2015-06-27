package com.mediateka.content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.transform.JDOMResult;

import com.google.gson.Gson;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FileLoader;
import com.mysql.fabric.Response;

public class CreateContent {

	public static void createContent(HttpServletRequest request, HttpServletResponse response,
			ContentGroupType contentGroupType) throws ServletException,
			SQLException, ReflectiveOperationException, IOException {

		HttpSession session = request.getSession();

		FileLoader fileLoader = new FileLoader();
		String type = null;
		if (session.getAttribute("clubId") != null) {
			type = "club" + session.getAttribute("clubId");
		} else if (session.getAttribute("eventId") != null) {
			type = "club" + session.getAttribute("clubId");
		}
		System.out.println(type.replaceAll("[0-9]", "") + "\\" + type);
		fileLoader
				.loadFile(request, type.replaceAll("[0-9]", "") + "\\" + type);
		ContentGroup contentGroup = new ContentGroup();
		contentGroup.setDislike(0);
		contentGroup.setLike(0);
		contentGroup.setName(fileLoader.getParameterMap().get("name"));
		contentGroup.setClubId((Integer) session.getAttribute("clubId"));
		contentGroup.setEventId((Integer) session.getAttribute("eventId"));
		contentGroup.setCreatorId((Integer) session.getAttribute("userId"));
		contentGroup.setType(contentGroupType);
		contentGroup.setState(State.ACTIVE);
		contentGroup.setText(fileLoader.getParameterMap().get("text"));
		contentGroup.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
		System.out.println(contentGroup);

		contentGroup = ContentGroupService.callSaveContentGroup(contentGroup);
		List<Media> mediaList = new ArrayList<Media>();
		try {
			if (fileLoader.getAllFilePathes() != null) {
				for (int i = 0; i < fileLoader.getAllFilePathes().size(); i++) {
					Media media = new Media();
					System.out.println(fileLoader.getFilePath());
					media.setType(fileLoader.getMediaTypes().get(i));
					media.setState(State.ACTIVE);
					media.setPath(fileLoader.getAllRelativePathes().get(i));
					media.setName(fileLoader.getAllFileDefaultNames().get(i));
					media.setContentGroupId(contentGroup.getId());
					Path source = Paths.get(fileLoader.getFilePath());
					try {
						System.out.println(Files.probeContentType(source));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mediaList.add(media);
					MediaService.saveMedia(media);
				}
			}
		} catch (WrongInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Document document = new Document(new Element("contentGroup"));
//		Element child = new Element("contentGroup");		
//		child.addContent(new Element("id", (String)contentGroup.getId().toString()));
//		JDOMResult result = new JDOMResult();
//		result.setDocument(document);
		
		
		Map<String, Object> recordMap = new HashMap<String, Object>();
		recordMap.put("contentGroup", contentGroup);
		if(mediaList != null){
			recordMap.put("mediaList", mediaList);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(new Gson().toJson(contentGroup).toString());
		response.getWriter().write(new Gson().toJson(recordMap));
		
		

	}

}
