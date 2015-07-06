package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.ContentGroupDAO;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.State;

public class ContentGroupService {
	public static void saveContentGroup(ContentGroup contentGroup)
			throws SQLException, ReflectiveOperationException {
		ContentGroupDAO.saveContentGroup(contentGroup);
	}
	
	public static ContentGroup callSaveContentGroup(ContentGroup contentGroup) throws SQLException, ReflectiveOperationException{
		return ContentGroupDAO.callSaveContentGroup(contentGroup);
	}

	public static ContentGroup getContentGroupById(Integer contentGroupId)
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupById(contentGroupId);
	}

	public static List<ContentGroup> getContentGroupByType(ContentGroupType type)
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupByType(type);
	}

	public static List<ContentGroup> getContentGroupByState(State state)
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupByState(state);
	}

	public static List<ContentGroup> getContentGroupByEventId(Integer eventId)
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupByEventId(eventId);
	}

	public static List<ContentGroup> getContentGroupByClubId(Integer clubId)
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupByClubId(clubId);
	}

	public static List<ContentGroup> getContentGroupByParentId(Integer parentId)
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupByParentId(parentId);
	}

	public static List<ContentGroup> getContentGroupByCreatorId(
			Integer creatorId) throws ReflectiveOperationException,
			SQLException {
		return ContentGroupDAO.getContentGroupByCreatorId(creatorId);
	}

	public static List<ContentGroup> getContentGroupByNameRegexId(
			String nameRegex) throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupByNameRegexId(nameRegex);
	}
	
	public static List<ContentGroup> getContentGroupByClubIdAndState(Integer clubId, State state) throws SQLException, ReflectiveOperationException{
		return ContentGroupDAO.getContentGroupByClubIdAndState(clubId, state);
	}
	
	public static List<ContentGroup> getContentGroupByEventIdAndState(Integer eventId, State state) throws SQLException, ReflectiveOperationException{
		return ContentGroupDAO.getContentGroupByEventIdAndState(eventId, state);
	}
	
	public static List<ContentGroup> getContentGroupByClubIdAndStateAndType(Integer clubId, State state, ContentGroupType contentGroupType) throws SQLException, ReflectiveOperationException{
		return ContentGroupDAO.getContentGroupByClubIdAndStateAndType(clubId, state, contentGroupType);
	}

	public static List<ContentGroup> getContentGroupAll()
			throws ReflectiveOperationException, SQLException {
		return ContentGroupDAO.getContentGroupAll();
	}

	public static void updateContentGroup(ContentGroup contentGroup)
			throws ReflectiveOperationException, SQLException {
		ContentGroupDAO.updateContentGroup(contentGroup);
	}
	
}