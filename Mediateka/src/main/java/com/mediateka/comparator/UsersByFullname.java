package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.User;

public class UsersByFullname implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {
		if (!u1.getLastName().equals(u2.getLastName())){
			return u1.getLastName().compareTo(u2.getLastName());
		}
		if (!u1.getFirstName().equals(u2.getFirstName())){
			return u1.getFirstName().compareTo(u2.getFirstName());
		}
		 return u1.getMiddleName().compareTo(u2.getMiddleName());
	}

}
