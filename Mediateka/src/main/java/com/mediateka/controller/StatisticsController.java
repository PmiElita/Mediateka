package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.service.StatisticService;

@Controller
public class StatisticsController {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(UserController.class);

	@Request(url = "bookStatistics", method = "get")
	public static void getBooksStatistics(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {

		Date start = new Date(1000);
		Date end = new Date(2000000000000L);
		
		
		Map< String, Map<String, Integer> > bookStatistics = StatisticService.getBookStatistics(start, end);
		
		
		request.setAttribute("statistics", bookStatistics);
		
		
		request.getRequestDispatcher("pages/table/bookStatistics.jsp").forward(
				request, response);
	}


	@Request(url = "userStatistics", method = "get")
	public static void getUserStatistics(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {

		Date start = new Date(1000);
		Date end = new Date(2000000000000L);
		
		
		Map< String, Map<String, Integer> > userStatistics = StatisticService.getUserStatistics(start, end);
		
		
		request.setAttribute("statistics", userStatistics);
		
		
		request.getRequestDispatcher("pages/table/statistics.jsp").forward(
				request, response);
	}


}
