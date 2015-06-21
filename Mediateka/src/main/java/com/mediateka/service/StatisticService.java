package com.mediateka.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.mediateka.dao.StatisticsDAO;

public class StatisticService {

	public static Map<String, Map<String, Integer>> getBookStatistics(
			Date startTime, Date endTime) throws SQLException {
		
		return StatisticsDAO.getBookStatistics(startTime, endTime);
	}

	
	public static Map<String, Map<String, Integer>> getUserStatistics(
			Date startTime, Date endTime) throws SQLException {
		
		return StatisticsDAO.getUserStatistics(startTime, endTime);
	}

	

}
