package com.mediateka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;



import java.sql.SQLException;

import com.mediateka.model.User;

import static com.mediateka.dao.statement.UserStatements.*;

import com.mediateka.util.ConnectionManager;
import com.mediateka.util.Transformer;

public class UserDAO {
      public void saveUser(User user) throws SQLException{
    	  try(Connection connection = ConnectionManager.getConnection()){
    	  PreparedStatement satement = connection.prepareStatement(INSERT_USER);
    	  Transformer.
    	  }
      }
}
