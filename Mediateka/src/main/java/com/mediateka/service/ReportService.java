package com.mediateka.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mediateka.dao.ReportDAO;
import com.mediateka.dao.statement.ReportStatements;
import com.mediateka.model.Report;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

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
}
