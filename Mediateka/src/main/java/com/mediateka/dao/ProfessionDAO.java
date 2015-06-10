package com.mediateka.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mediateka.dao.statement.ProfessionStatements.*;

import com.mediateka.model.Profession;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class ProfessionDAO {
	
	public static void saveProfession(Profession profession) throws SQLException, ReflectiveOperationException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(INSERT_PROFESSION);
			Transformer.valueIntoPreparedStatement(statement, profession, INSERT_PROFESSION_ORDER);
			statement.executeUpdate();
		}
	}
	
	public static Profession getProfessionById(Integer professionId) throws SQLException, ReflectiveOperationException{
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement statement = connection.prepareStatement(SELECT_PROFESSION_BY_ID);
			Profession profession = new Profession();
			profession.setId(professionId);
			Transformer.valueIntoPreparedStatement(statement, profession, SELECT_PROFESSION_BY_ID_ORDER);
			ResultSet resultSet = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(resultSet, Profession.class);
		}
	}

}
