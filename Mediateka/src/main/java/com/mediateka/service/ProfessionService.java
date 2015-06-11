package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ProfessionDAO;
import com.mediateka.model.Profession;
import com.mediateka.model.enums.State;

public class ProfessionService {
	public static void saveProfession(Profession profession)
			throws SQLException, ReflectiveOperationException {
		ProfessionDAO.saveProfession(profession);
	}

	public static void updateProffesion(Profession profession)
			throws SQLException, ReflectiveOperationException {
		ProfessionDAO.updateProfession(profession);
	}

	public static Profession getProfessionById(Integer professionId)
			throws SQLException, ReflectiveOperationException {
		return ProfessionDAO.getProfessionById(professionId);
	}

	public static List<Profession> getProfessionByNameRegex(String name)
			throws SQLException, ReflectiveOperationException {
		return ProfessionDAO.getProfessionByNameRegex(name);
	}

	public static List<Profession> getProfessionByState(State state)
			throws SQLException, ReflectiveOperationException {
		return ProfessionDAO.getProfessionByState(state);
	}

	public static List<Profession> getProfessionAll() throws SQLException,
			ReflectiveOperationException {
		return ProfessionDAO.getProfessionAll();
	}
}