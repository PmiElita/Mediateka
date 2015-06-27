package com.mediateka.service;

import java.sql.SQLException;
import java.util.List;

import com.mediateka.dao.LikeRecordDAO;
import com.mediateka.model.LikeRecord;

public class LikeRecordService {

	public static void saveLikeRecord(LikeRecord likeRecord)
			throws SQLException, ReflectiveOperationException {
		LikeRecordDAO.saveLikeRecord(likeRecord);
	}

	public static void updateLikeRecord(LikeRecord likeRecord)
			throws SQLException, ReflectiveOperationException {
		LikeRecordDAO.updateLikeRecord(likeRecord);
	}

	public static LikeRecord getLikeRecordById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return LikeRecordDAO.getLikeRecordById(id);
	}

	public static List<LikeRecord> getLikeRecordByUserId(Integer userId)
			throws SQLException, ReflectiveOperationException {
		return LikeRecordDAO.getLikeRecordByUserId(userId);
	}

	public static List<LikeRecord> getLikeRecordByContentGroupId(
			Integer contentGroupId) throws SQLException,
			ReflectiveOperationException {
		return LikeRecordDAO.getLikeRecordByContentGroupId(contentGroupId);
	}

	public static LikeRecord getLikeRecordByUserIdAndContentGroupId(
			Integer userId, Integer contentGroupId) throws SQLException,
			ReflectiveOperationException {
		return LikeRecordDAO.getLikeRecordByUserIdAndContentGroupId(userId,
				contentGroupId);
	}

}
