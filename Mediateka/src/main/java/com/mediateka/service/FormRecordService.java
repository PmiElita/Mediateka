package com.mediateka.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.mediateka.dao.FormRecordDAO;
import com.mediateka.model.FormRecord;
import com.mediateka.model.enums.State;

public class FormRecordService {
	public static void saveFormRecord(FormRecord formRecord)
			throws SQLException, ReflectiveOperationException {
		FormRecordDAO.saveFormRecord(formRecord);
	}

	public static FormRecord getFormRecordById(Integer formRecordId)
			throws SQLException, ReflectiveOperationException {
		return FormRecordDAO.getFormRecordById(formRecordId);
	}

	public static List<FormRecord> getFormRecordByUserId(
			Integer formRecordUserId) throws SQLException,
			ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByUserId(formRecordUserId);
	}

	public static List<FormRecord> getFormRecordByAdminId(
			Integer formRecordAdminId) throws SQLException,
			ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByAdminId(formRecordAdminId);
	}

	public static List<FormRecord> getFormRecordByEventId(
			Integer formRecordEventId) throws SQLException,
			ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByEventId(formRecordEventId);
	}

	public static List<FormRecord> getFormRecordByBookId(
			Integer formRecordBookId) throws SQLException,
			ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByBookId(formRecordBookId);
	}

	public static List<FormRecord> getFormRecordByState(State formRecordState)
			throws SQLException, ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByState(formRecordState);
	}

	public static void updateFormRecordById(FormRecord formRecord)
			throws SQLException, ReflectiveOperationException {
		FormRecordDAO.updateFormRecordById(formRecord);
	}

	public static List<FormRecord> getFormRecordAll() throws SQLException,
			ReflectiveOperationException {
		return FormRecordDAO.getFormRecordAll();
	}

	public static List<FormRecord> getFormRecordsByDateRange(
			Timestamp dateFrom, Timestamp dateTill) throws SQLException,
			ReflectiveOperationException {
		return FormRecordDAO.getFormRecordsByDateRange(dateFrom, dateTill);
	}

	public static List<FormRecord> getFormRecordsByUserIdAndDateRange(
			Integer userId, Timestamp dateFrom, Timestamp dateTill)
			throws SQLException, ReflectiveOperationException {
		return FormRecordDAO.getFormRecordsByUserIdAndDateRange(userId,
				dateFrom, dateTill);
	}
}