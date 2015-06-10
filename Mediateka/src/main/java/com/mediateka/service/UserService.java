package com.mediateka.service;

import java.sql.SQLException;

import com.mediateka.dao.UserDAO;
import com.mediateka.model.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	
	   public void saveUser(User user) throws SQLException, ReflectiveOperationException{
		   userDAO.saveUser(user);
	   }
	   
	   public User getUserById(Integer userId) throws ReflectiveOperationException, SQLException{
		   return userDAO.getUserById(userId);
	   }
}
