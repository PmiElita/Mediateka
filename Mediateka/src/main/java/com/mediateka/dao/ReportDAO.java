package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mediateka.dao.statement.ReportStatements;
import com.mediateka.model.Report;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ReportDAO {

	public static void saveReport(Report report) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(ReportStatements.INSERT_REPORT);
			Transformer.valueIntoPreparedStatement(statement, report,
					ReportStatements.INSERT_REPORT_ORDER);
			statement.executeUpdate();
		}
	}

	public static void updateReport(Report report) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(ReportStatements.UPDATE_REPORT);
			Transformer.valueIntoPreparedStatement(statement, report,
					ReportStatements.UPDATE_REPORT_ORDER);
			statement.executeUpdate();
		}
	}

	public static Report getReportById(Integer id) throws SQLException,
			ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(ReportStatements.SELECT_REPORT_BY_ID);
			Report report = new Report();
			report.setId(id);
			Transformer.valueIntoPreparedStatement(statement, report,
					ReportStatements.SELECT_REPORT_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					Report.class);
		}
	}

	public static List<Report> getResponses(Integer limit, Integer offset)
			throws SQLException {
		String GET_RESPONSES = "SELECT id, email, text, name, date, state "
				+ "FROM report WHERE  state <> 'DELETED' "
				+ "ORDER BY date DESC " + "LIMIT ? OFFSET ? ";

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(GET_RESPONSES);

			statement.setInt(1, limit);
			statement.setInt(2, offset);

			ResultSet rs = statement.executeQuery();

			ArrayList<Report> retval = new ArrayList<Report>();
			while (rs.next()) {
				Report report = new Report();
				report.setId(rs.getInt(1));
				report.setEmail(rs.getString(2));
				report.setText(rs.getString(3));
				report.setName(rs.getString(4));
				report.setDate(rs.getTimestamp(5));
				report.setState(State.valueOf(rs.getString(6).toUpperCase()));
				retval.add(report);
			}

			return retval;
		}

	}
	
	public static Integer getNumberOfNewReports() throws SQLException {

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement("SELECT count(*) FROM report WHERE  state = 'ACTIVE' ");

			ResultSet rs = statement.executeQuery();

			rs.next();
			return rs.getInt(1);
		}		
	}

	
	public static Integer getNumberOfAllReports() throws SQLException {

		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement("SELECT count(*) FROM report WHERE  state <> 'DELETED' ");

			ResultSet rs = statement.executeQuery();

			rs.next();
			return rs.getInt(1);
		}		
	}

}
