package com.mediateka.service;


import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.FormRecordDAO;
import com.mediateka.model.FormRecord;

public class FormRecordService {
	
	public static void saveFormRecord(FormRecord formRecord) throws SQLException, ReflectiveOperationException{
		FormRecordDAO.saveFormRecord(formRecord);
	}
	
	public static FormRecord getFormRecordById(Integer formRecordId) throws SQLException, ReflectiveOperationException{
		return FormRecordDAO.getFormRecordById(formRecordId);
	}
	
	public static List<FormRecord> getFormRecordByUserId(Integer formRecordUserId) throws SQLException, ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByUserId(formRecordUserId);
	}
	
	public static List<FormRecord> getFormRecordByAdminId(Integer formRecordAdminId) throws SQLException, ReflectiveOperationException {
		return FormRecordDAO.getFormRecordByAdminId(formRecordAdminId);
	}
	
	public static void updateEventById(FormRecord formRecord) throws SQLException, ReflectiveOperationException{
		FormRecordDAO.updateEventById(formRecord);
	}

}
