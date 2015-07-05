package com.mediateka.service;


import java.sql.SQLException;

import java.util.List;

import com.mediateka.dao.ReportDAO;

import com.mediateka.model.Report;

public class ReportService {

	public static void saveReport(Report report) throws SQLException,
			ReflectiveOperationException {

		ReportDAO.saveReport(report);
	}

	public static void updateReport(Report report) throws SQLException,
			ReflectiveOperationException {

		ReportDAO.updateReport(report);
	}

	public static Report getReportById(Integer id) throws SQLException,
			ReflectiveOperationException {

		return ReportDAO.getReportById(id);
	}

	public static List<Report> getResponses(Integer limit, Integer offset)
			throws SQLException {

		return ReportDAO.getResponses(limit, offset);
	}

	public static Integer getNumberOfNewReports() throws SQLException {
		return ReportDAO.getNumberOfNewReports();

	}
	
	public static Integer getNumberOfAllReports() throws SQLException {
		return ReportDAO.getNumberOfAllReports();

	}
	
	
}
