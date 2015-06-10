package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;



import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediateka.model.User;

import static com.mediateka.dao.statement.UserStatements.*;

import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class UserDAO {
      public void saveUser(User user) throws SQLException, ReflectiveOperationException{
    	  try(Connection connection = ConnectionManager.getConnection()){
    	  PreparedStatement statement = connection.prepareStatement(INSERT_USER);
    	  Transformer.valueIntoPreparedStatement(statement, user, INSERT_USER_ORDER);
    	  statement.executeUpdate();
    	  }
      }
      
      public User getUserById(Integer userId) throws ReflectiveOperationException, SQLException{
    	  try(Connection connection = ConnectionManager.getConnection()){
        	  PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
        	  User user = new User();
        	  user.setId(userId);
        	  Transformer.valueIntoPreparedStatement(statement, user, INSERT_USER_ORDER);
        	  ResultSet resultSet=statement.executeQuery();
        	  return Transformer.transformResultSetIntoObject(resultSet, User.class);
        	  }
      }
}
