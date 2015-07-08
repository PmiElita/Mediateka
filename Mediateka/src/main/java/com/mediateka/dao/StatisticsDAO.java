package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mediateka.util.ConnectionManager;

public class StatisticsDAO {

	public static Map<String, Map<String, Integer>> getBookStatistics(
			Date startTime, Date endTime) throws SQLException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement("CALL getBooksStatistics(?, ? );");

			statement.setTimestamp(1,
					new java.sql.Timestamp(startTime.getTime()));
			statement
					.setTimestamp(2, new java.sql.Timestamp(endTime.getTime()));

			statement.execute();
			ResultSet rs = statement.getResultSet();
			if (rs == null)
				System.out.println("RS IS NULL");
			Map<String, Map<String, Integer>> returnValue = new HashMap<String, Map<String, Integer>>();

			while (rs.next()) {

				String groupName = rs.getString("group");
				String name = rs.getString("name");

				Map<String, Integer> group = returnValue.get(groupName);
				if (group == null) {
					returnValue.put(groupName, new HashMap<String, Integer>());
					group = returnValue.get(groupName);

				}
				group.put(name, rs.getInt("count"));
			}

			return returnValue;
		}

	}

	public static Map<String, Map<String, Integer>> getUserStatistics(
			Date startTime, Date endTime) throws SQLException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement("CALL getUserStatistics(?, ? );");

			statement.setDate(1, new java.sql.Date(startTime.getTime()));
			statement.setDate(2, new java.sql.Date(endTime.getTime()));

			statement.execute();
			ResultSet rs = statement.getResultSet();
			if (rs == null)
				System.out.println("RS IS NULL");
			Map<String, Map<String, Integer>> returnValue = new HashMap<String, Map<String, Integer>>();

			while (rs.next()) {

				String groupName = rs.getString("category");
				String name = rs.getString("subcategory");

				Map<String, Integer> group = returnValue.get(groupName);
				if (group == null) {
					returnValue.put(groupName, new HashMap<String, Integer>());
					group = returnValue.get(groupName);

				}
				group.put(name, rs.getInt("amount"));
			}

			return returnValue;
		}

	}

}
