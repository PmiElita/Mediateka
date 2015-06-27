package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.mediateka.dao.statement.LikeRecordStatements.*;

import com.mediateka.model.LikeRecord;
import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class LikeRecordDAO {

	public static void saveLikeRecord(LikeRecord likeRecord)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(INSERT_LIKE_RECORD);

			Transformer.valueIntoPreparedStatement(statement, likeRecord,
					INSERT_LIKE_RECORD_ORDER);

			statement.executeUpdate();
		}

	}

	public static void updateLikeRecord(LikeRecord likeRecord)
			throws SQLException, ReflectiveOperationException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(UPDATE_LIKE_RECORD);

			Transformer.valueIntoPreparedStatement(statement, likeRecord,
					UPDATE_LIKE_RECORD_ORDER);

			statement.executeUpdate();

		}
	}

	public static LikeRecord getLikeRecordById(Integer id) throws SQLException,
			ReflectiveOperationException {

		LikeRecord likeRecord = new LikeRecord();
		likeRecord.setId(id);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(SELECT_LIKE_RECORD_BY_ID);

			Transformer.valueIntoPreparedStatement(statement, likeRecord,
					SELECT_LIKE_RECORD_BY_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs,
					LikeRecord.class);

		}
	}

	public static List<LikeRecord> getLikeRecordByUserId(Integer userId)
			throws SQLException, ReflectiveOperationException {

		LikeRecord likeRecord = new LikeRecord();
		likeRecord.setUserId(userId);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(SELECT_LIKE_RECORD_BY_USER_ID);

			Transformer.valueIntoPreparedStatement(statement, likeRecord,
					SELECT_LIKE_RECORD_BY_USER_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, LikeRecord.class);

		}
	}

	public static List<LikeRecord> getLikeRecordByContentGroupId(
			Integer contentGroupId) throws SQLException,
			ReflectiveOperationException {

		LikeRecord likeRecord = new LikeRecord();
		likeRecord.setContentGroupId(contentGroupId);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(SELECT_LIKE_RECORD_BY_CONTENT_GROUP_ID);

			Transformer.valueIntoPreparedStatement(statement, likeRecord,
					SELECT_LIKE_RECORD_BY_CONTENT_GROUP_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoList(rs, LikeRecord.class);

		}
	}

	public static LikeRecord getLikeRecordByUserIdAndContentGroupId(
			Integer userId, Integer contentGroupId) throws SQLException,
			ReflectiveOperationException {

		LikeRecord likeRecord = new LikeRecord();
		likeRecord.setContentGroupId(contentGroupId);
		likeRecord.setUserId(userId);

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement statement = connection
					.prepareStatement(SELECT_LIKE_RECORD_BY_USER_ID_AND_CONTENT_GROUP_ID);

			Transformer.valueIntoPreparedStatement(statement, likeRecord,
					SELECT_LIKE_RECORD_BY_USER_ID_AND_CONTENT_GROUP_ID_ORDER);

			ResultSet rs = statement.executeQuery();
			return Transformer.transformResultSetIntoObject(rs,
					LikeRecord.class);

		}
	}

}
