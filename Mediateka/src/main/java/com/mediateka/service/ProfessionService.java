package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.ProfessionDAO;
import com.mediateka.model.Profession;

public class ProfessionService {
	
	public static void saveProfession(Profession profession) throws SQLException, ReflectiveOperationException {
		ProfessionDAO.saveProfession(profession);
	}
	
	public static Profession getProfessionById(Integer professionId) throws SQLException, ReflectiveOperationException{
		return ProfessionDAO.getProfessionById(professionId);
	}

}
