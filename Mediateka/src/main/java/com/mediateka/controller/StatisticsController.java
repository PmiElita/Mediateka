package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

		Integer year = null;
		Integer month = null;
		try{
			year = Integer.parseInt(request.getParameter("year"));
			month = Integer.parseInt(request.getParameter("month")) - 1;
		}catch(NumberFormatException e){
			year = Calendar.getInstance().get(Calendar.YEAR);
			month = Calendar.getInstance().get(Calendar.MONTH);
		}		
		
		request.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		//from the beginning of the year
		//till the beginning of the month
		Date start = new Calendar.Builder().setDate(year, 1, 1).build().getTime();
		Calendar cal = new Calendar.Builder().setDate(year, month, 1).build();
		cal.add(Calendar.SECOND, -1);
		Date end = cal.getTime();


		Map< String, Map<String, Integer> > bookStatistics = StatisticService.getBookStatistics(start, end);
		request.setAttribute("yearStatistics", bookStatistics);
		
		//for each day in this month

		List< Map<String, Map<String, Integer > > > listOfBookStatistics = new ArrayList< Map<String, Map<String, Integer > > >();
		
		int maximumDayOfMonth = new Calendar.Builder().setDate(year, month, 1).build().getMaximum(Calendar.DAY_OF_MONTH);
		int minimumDayOfMonth = new Calendar.Builder().setDate(year, month, 1).build().getMinimum(Calendar.DAY_OF_MONTH);
		for (int day = minimumDayOfMonth ; day <= maximumDayOfMonth ; day++){
			start =  new Calendar.Builder().setDate(year, month, day).build().getTime();
			end = start;
			
			Map< String, Map<String, Integer> > dayStatistics = StatisticService.getBookStatistics(start, end) ;  
			
			listOfBookStatistics.add( dayStatistics );
		}

		request.setAttribute("monthStatistics", listOfBookStatistics);
		
		request.getRequestDispatcher("pages/table/bookStatistics.jsp").forward(
				request, response);
	}

	@Request(url = "userStatistics", method = "get")
	public static void getUserStatistics(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		Date start = new Date(1000);
		Date end = new Date(2000000000000L);

		Map<String, Map<String, Integer>> userStatistics = StatisticService
				.getUserStatistics(start, end);

		request.setAttribute("statistics", userStatistics);

		request.getRequestDispatcher("pages/table/statistics.jsp").forward(
				request, response);
	}

}
