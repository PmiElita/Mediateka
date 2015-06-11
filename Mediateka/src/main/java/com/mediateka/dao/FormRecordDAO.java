package com.mediateka.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;






import java.util.List;

import static com.mediateka.dao.statement.FormRecordStatements.*;

import com.mediateka.model.FormRecord;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class FormRecordDAO {
	
	public static void saveFormRecord(FormRecord formRecord) throws SQLException, ReflectiveOperationException{
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(INSERT_FORM_RECORD);
			Transformer.valueIntoPreparedStatement(statement, formRecord, INSERT_FORM_RECORD_ORDER);
			statement.executeUpdate();
		}
	}
	
	public static FormRecord getFormRecordById(Integer formRecordId) throws SQLException, ReflectiveOperationException{
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(SELECT_FORM_RECORD_BY_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setId(formRecordId);
			Transformer.valueIntoPreparedStatement(statement, formRecord, SELECT_FORM_RECORD_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet, FormRecord.class);
		}
	}
	
	public static List<FormRecord> getFormRecordByUserId(Integer formRecordUserId) throws SQLException, ReflectiveOperationException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(SELECT_FORM_RECORD_BY_USER_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setId(formRecordUserId);
			Transformer.valueIntoPreparedStatement(statement, formRecord, SELECT_FORM_RECORD_BY_USER_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet, FormRecord.class);
		}
	}
	
	public static List<FormRecord> getFormRecordByAdminId(Integer formRecordAdminId) throws SQLException, ReflectiveOperationException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(SELECT_FORM_RECORD_BY_ADMIN_ID);
			FormRecord formRecord = new FormRecord();
			formRecord.setId(formRecordAdminId);
			Transformer.valueIntoPreparedStatement(statement, formRecord, SELECT_FORM_RECORD_BY_ADMIN_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoList(resultSet, FormRecord.class);
		}
	}
	
	public static void updateEventById(FormRecord formRecord) throws SQLException, ReflectiveOperationException{
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(UPDATE_FORM_RECORD_BY_ID);			
			Transformer.valueIntoPreparedStatement(statement, formRecord, UPDATE_FORM_RECORD_BY_ID_ORDER);
			statement.executeUpdate();
		}
	}

}