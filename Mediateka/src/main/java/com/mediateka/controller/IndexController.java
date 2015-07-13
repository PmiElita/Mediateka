package com.mediateka.controller;

import static com.mediateka.service.EventService.getEventByState;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.EventsByDate;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Event;
import com.mediateka.model.Media;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.EventType;
import com.mediateka.model.enums.State;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.MediaService;
import com.mediateka.service.ProfessionService;

@Controller
public class IndexController {

	@Request(url = "index", method = "get")
	public static void indexGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		long currentTime = new java.util.Date().getTime();
		List<Event> events = getEventByState(State.ACTIVE);
		List<Event> currentEvents = new ArrayList<>();
		if (events != null)
			for (Event event : events)
				if (event.getDateTill().getTime() > currentTime)
					currentEvents.add(event);
		Collections.sort(currentEvents, new EventsByDate());

		int size = currentEvents.size();

		if (size > 3)
			for (int i = 3; i < currentEvents.size(); i++)
				currentEvents.remove(i);
		else if (size < 3) {
			int difference = 3 - size;
			List<Event> oldEvents = new ArrayList<>();
			if (events != null)
				for (Event event : events)
					if (event.getDateTill().getTime() < currentTime)
						oldEvents.add(event);
			Collections.sort(oldEvents, new EventsByDate());
			if (oldEvents.size() >= difference)
				for (int i = 0; i < difference; i++)
					currentEvents.add(oldEvents.get(i));
			if (oldEvents.size() < difference)
				currentEvents.addAll(oldEvents);
		}

		List<String> dates = new ArrayList<>();
		List<String> times = new ArrayList<>();
		Timestamp dateFrom;
		Timestamp dateTill;
		String date = "It goes right now!";
		String time = "It goes right now!";
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat formatMeeting = new SimpleDateFormat("hh:mm");
		for (Event event : currentEvents) {
			dateFrom = event.getDateFrom();
			dateTill = event.getDateTill();
			if (event.getType() == EventType.MEETING) {
				date = format.format(dateFrom);
				time = formatMeeting.format(dateFrom) + "  -  "
						+ formatMeeting.format(dateTill);
				dates.add(date);
				times.add(time);
			} else if (event.getType() == EventType.EXHIBITION) {
				date = format.format(dateFrom) + "  -  "
						+ format.format(dateTill);
				dates.add(date);
				times.add("");
			}
		}
		ContentGroup info = ContentGroupService.getContentGroupByType(
				ContentGroupType.INFO).get(0);
		String textInfoUa = info.getText().split("<info!split!info>")[0];
		String textInfoEn = info.getText().split("<info!split!info>")[1];
		List<ContentGroup> allInfo = ContentGroupService
				.getContentGroupByParentId(info.getId());
		String image1ua = allInfo.get(0).getText().split("<info!split!info>")[0];
		String image1en = allInfo.get(0).getText().split("<info!split!info>")[1];
		String image2ua = allInfo.get(1).getText().split("<info!split!info>")[0];
		String image2en = allInfo.get(1).getText().split("<info!split!info>")[1];
		String image3ua = allInfo.get(2).getText().split("<info!split!info>")[0];
		String image3en = allInfo.get(2).getText().split("<info!split!info>")[1];
		List<Media> media = new ArrayList<>();
		for (ContentGroup content : allInfo)
			media.add(MediaService.getMediaByContentGroupId(content.getId())
					.get(0));
		List<String> imageTextUa = new ArrayList<>();
		imageTextUa.add(image1ua);
		imageTextUa.add(image2ua);
		imageTextUa.add(image3ua);
		List<String> imageTextEn = new ArrayList<>();
		imageTextEn.add(image1en);
		imageTextEn.add(image2en);
		imageTextEn.add(image3en);
		List<String> imagePath = new ArrayList<>();
		for (Media image : media)
			imagePath.add(image.getPath().replace("\\", "/"));
		System.out.println(imageTextUa);
		System.out.println(imageTextEn);

		request.setAttribute("randomic", randomizeClass());
		request.setAttribute("textInfoUa", textInfoUa);
		request.setAttribute("textInfoEn", textInfoEn);
		request.setAttribute("imageTextUa", imageTextUa);
		request.setAttribute("imageTextEn", imageTextEn);
		request.setAttribute("imagePath", imagePath);
		request.setAttribute("dates", dates);
		request.setAttribute("times", times);
		request.setAttribute("events", currentEvents);
		request.setAttribute("professions",
				ProfessionService.getProfessionAll());
		request.getRequestDispatcher("pages/index/index.jsp").forward(request,
				response);
		request.removeAttribute("events");
		request.removeAttribute("professions");
		request.removeAttribute("dates");
		request.removeAttribute("times");
		request.removeAttribute("textInfoUa");
		request.removeAttribute("textInfoEn");
		request.removeAttribute("imageTextUa");
		request.removeAttribute("imageTextEn");
		request.removeAttribute("imagePath");
		request.removeAttribute("randomic");
	}

	private static String randomizeClass() {
		String result = "caption center-align";
		double random = new Random().nextDouble();
		if (random < 0.33)
			result = "caption left-align";
		else if (random > 0.67)
			result = "caption right-align";
		return result;
	}
}
