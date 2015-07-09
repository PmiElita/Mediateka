package com.mediateka.content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mediateka.comparator.ContentGroupByDate;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;

public class CreateContent {

	public static void createContent(HttpServletRequest request,
			HttpServletResponse response, ContentGroupType contentGroupType)
			throws ServletException, SQLException,
			ReflectiveOperationException, IOException {

		HttpSession session = request.getSession();
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request);
		ContentGroup contentGroup = new ContentGroup();
		contentGroup.setDislike(0);
		contentGroup.setLike(0);
		contentGroup.setName(fileLoader.getParameterMap().get("name"));
		if (fileLoader.getParameterMap().get("clubId") != null) {
			contentGroup.setClubId(Integer.parseInt(fileLoader
					.getParameterMap().get("clubId")));
		} else if (fileLoader.getParameterMap().get("eventId") != null) {
			contentGroup.setEventId(Integer.parseInt(fileLoader
					.getParameterMap().get("eventId")));
		}
		contentGroup.setCreatorId((Integer) session.getAttribute("userId"));
		contentGroup.setType(contentGroupType);
		contentGroup.setState(State.ACTIVE);
		if (fileLoader.getParameterMap().get("text") != null) {
			contentGroup.setText(fileLoader.getParameterMap().get("text"));
		}
		contentGroup.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));

		contentGroup = ContentGroupService.callSaveContentGroup(contentGroup);
		List<Media> mediaList = new ArrayList<Media>();
		try {
			System.out.println(fileLoader.getAllFileDefaultNames() + " "
					+ fileLoader.getAllFilePathes());
		} catch (WrongInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (fileLoader.getAllFilePathes() != null) {
				for (int i = 0; i < fileLoader.getAllFilePathes().size(); i++) {
					Media media = new Media();
					media.setType(fileLoader.getMediaTypes().get(i));
					media.setState(State.ACTIVE);
					media.setPath(fileLoader.getAllRelativePathes().get(i)
							.replace("\\", "/"));
					media.setName(fileLoader.getAllFileDefaultNames().get(i));
					media.setContentGroupId(contentGroup.getId());
					mediaList.add(media);
					MediaService.saveMedia(media);
				}
			}
		} catch (WrongInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Media> images = new ArrayList<Media>();
		List<Media> videos = new ArrayList<Media>();
		List<Media> audios = new ArrayList<Media>();

		for (Media media : mediaList) {
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
		User user = UserService.getUserById(contentGroup.getCreatorId());

		Map<String, Object> recordMap = new HashMap<String, Object>();
		recordMap.put("contentGroup", contentGroup);
		recordMap.put("userFirstName", user.getFirstName());
		recordMap.put("userLastName", user.getLastName());
		if (images != null) {
			recordMap.put("imageList", images);
		}
		if (videos != null) {
			recordMap.put("videoList", videos);
		}
		if (audios != null) {
			recordMap.put("audioList", audios);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(recordMap));

	}

	public static void loadContent(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, ServletException, IOException {

		int clubId = 0;
		int eventId = 0;
		System.out.println(request.getParameter("recordId"));
		Integer recordId = Integer.parseInt(request.getParameter("recordId"));
		ContentGroup contentGroup = ContentGroupService
				.getContentGroupById(recordId);
		List<ContentGroup> records = new ArrayList<ContentGroup>();
		records.add(contentGroup);
		if (contentGroup.getClubId() != null) {
			clubId = contentGroup.getClubId();
		} else if (contentGroup.getEventId() != null) {
			eventId = contentGroup.getEventId();
		}

		setContent(request, response, records);

		if (clubId != 0) {
			request.setAttribute("clubId", clubId);
		}
		if (eventId != 0) {
			request.setAttribute("eventId", eventId);
		}
		request.setAttribute("index", request.getParameter("index"));

		request.getRequestDispatcher("pages/content/record_central.jsp").forward(
				request, response);

	}

	public static void setContent(HttpServletRequest request,
			HttpServletResponse response, List<ContentGroup> records)
			throws ReflectiveOperationException, SQLException {
		Map<Integer, List<Media>> mediaMap = new HashMap<Integer, List<Media>>();
		Map<Integer, List<Media>> imageMap = new HashMap<Integer, List<Media>>();
		Map<Integer, List<Media>> videoMap = new HashMap<Integer, List<Media>>();
		Map<Integer, List<Media>> audioMap = new HashMap<Integer, List<Media>>();
		Map<Integer, Media> posterMap = new HashMap<Integer, Media>();
		Map<Integer, String> creatorRecordMap = new HashMap<Integer, String>();
		Map<Integer, String> creatorAvaRecordMap = new HashMap<Integer, String>();
		if (records != null) {
			Collections.sort(records, new ContentGroupByDate());
			for (ContentGroup record : records) {
				if (record.getState() == State.ACTIVE) {
					User creator = UserService.getUserById(record
							.getCreatorId());
					String cratorName = creator.getFirstName() + " "
							+ creator.getLastName();

					if (MediaService.getMediaById(creator.getAvaId()) != null) {

						String creatorAvaPath = MediaService.getMediaById(
								creator.getAvaId()).getPath();
						creatorAvaRecordMap.put(record.getId(), creatorAvaPath);
					}
					creatorRecordMap.put(record.getId(), cratorName);

					List<Media> images = new ArrayList<Media>();
					List<Media> videos = new ArrayList<Media>();
					List<Media> audios = new ArrayList<Media>();
					mediaMap.put(record.getId(),
							MediaService.getMediaContentGroupId(record.getId()));
					List<Media> medias = MediaService
							.getMediaContentGroupId(record.getId());
					if (medias != null) {
						for (Media media : medias) {
							System.out.println(media);
							if (media.getType().equals(MediaType.IMAGE)) {
								images.add(media);
							}
							if (media.getType().equals(MediaType.VIDEO)) {
								videos.add(media);
								List<Media> posters = MediaService
										.getMediaByPathRegex(media
												.getPath()
												.substring(
														media.getPath()
																.lastIndexOf(
																		'\\') + 1,
														media.getPath()
																.lastIndexOf(
																		'.'))
												+ ".jpg");
								if (posters != null) {
									if (posters.size() > 1) {
										System.out.println("owww fuck!");
									}
									Media poster = posters.get(0);
									if (poster != null) {
										posterMap.put(media.getId(), poster);
									}
								}
							}
							if (media.getType().equals(MediaType.AUDIO)) {
								audios.add(media);
							}
						}
						if (images != null) {
							if (images.size() > 0) {
								imageMap.put(record.getId(), images);
							}

						}
						if (videos != null) {
							if (videos.size() > 0) {
								videoMap.put(record.getId(), videos);
							}

						}
						if (audios != null) {
							if (audios.size() > 0) {
								audioMap.put(record.getId(), audios);
							}

						}

					} else {
						images = videos = audios = null;
					}

				}
			}

		}
		System.out.println(records);
		request.setAttribute("creatorAva", creatorAvaRecordMap);
		request.setAttribute("posterMap", posterMap);
		request.setAttribute("mediaMap", mediaMap);
		request.setAttribute("imageMap", imageMap);
		request.setAttribute("videoMap", videoMap);
		request.setAttribute("audioMap", audioMap);
		request.setAttribute("records", records);
		request.setAttribute("creatorName", creatorRecordMap);
	}

}
