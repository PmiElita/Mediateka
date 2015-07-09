package com.mediateka.dao;

import static com.mediateka.dao.statement.FormRecordStatements.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.mediateka.model.FormRecord;
import com.mediateka.model.enums.State;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class FormRecordDAO {
	public static void saveFormRecord(FormRecord formRecord)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_FORM_RECORD);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					INSERT_FORM_RECORD_ORDER);
			statement.executeUpdate();
		}
	}

	public static FormRecord getFormRecordById(Integer formRecordId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setId(formRecordId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordByUserId(
			Integer formRecordUserId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_USER_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setUserId(formRecordUserId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BY_USER_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordByBookId(
			Integer formRecordBookId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_BOOK_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setBookId(formRecordBookId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BY_BOOK_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordByEventId(
			Integer formRecordEventId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_EVENT_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setEventId(formRecordEventId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BY_EVENT_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordByAdminId(
			Integer formRecordAdminId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_ADMIN_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setId(formRecordAdminId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BY_ADMIN_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordByState(State state)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_STATE);
			FormRecord formRecord = new FormRecord();
			formRecord.setState(state);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BY_STATE_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static void updateFormRecordById(FormRecord formRecord)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_FORM_RECORD_BY_ID);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					UPDATE_FORM_RECORD_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

	public static List<FormRecord> getFormRecordAll() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_ALL);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordsByDateRange(
			Timestamp dateFrom, Timestamp dateTill) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_GET_FORM_RECORDS_BY_DATE_RANGE);
			statement.setTimestamp(1, dateFrom);
			statement.setTimestamp(2, dateTill);
			ResultSet resultSet = statement.executeQuery();

			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordsByUserIdAndDateRange(
			Integer userId, Timestamp dateFrom, Timestamp dateTill)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			CallableStatement statement = connection
					.prepareCall(CALL_GET_FORM_RECORDS_BY_USER_ID_AND_DATE_RANGE);
			statement.setInt(1, userId);
			statement.setTimestamp(2, dateFrom);
			statement.setTimestamp(3, dateTill);
			ResultSet resultSet = statement.executeQuery();

			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static int getFormRecordCountByBookId(Integer bookId)
			throws SQLException, ReflectiveOperationException {
		int result = 0;
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_COUNT_BY_BOOK_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setBookId(bookId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_COUNT_BY_BOOK_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(0);
			}

		}
		return result;
	}

	public static List<FormRecord> getFormRecordBookAndUserId(Integer userId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BOOK_BY_USER_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setUserId(userId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_BOOK_BY_USER_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordGoalByUserId(Integer userId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_GOAL_BY_USER_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setUserId(userId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_GOAL_BY_USER_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordEventAndUserId(Integer userId)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_EVENT_BY_USER_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setUserId(userId);
			Transformer.valueIntoPreparedStatement(statement, formRecord,
					SELECT_FORM_RECORD_EVENT_BY_USER_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}

	public static List<FormRecord> getFormRecordsByUserIdLimited(
			Integer userId, int offset, int limit) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(SELECT_FORM_RECORD_BY_USER_ID_AND_LIMIT);
			statement.setInt(1, userId);
			statement.setInt(2, offset);
			statement.setInt(3, limit);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet,
					FormRecord.class);
		}
	}
	
	public static List<FormRecord> getUserActivity(Integer userId,
			String period, String type) throws SQLException, ReflectiveOperationException{
		try(Connection connection = ConnectionManager. getConnection()){
			PreparedStatement statement = connection.prepareStatement(CALL_GET_ACTIVITY);
			statement.setInt(1,  userId);
			statement.setString(2, period);
			statement.setString(3, type);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet, FormRecord.class);
		}
	}
}