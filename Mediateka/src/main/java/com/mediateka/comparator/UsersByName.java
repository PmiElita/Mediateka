package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.User;

public class UsersByName implements Comparator<User> {
	public int compare(User user1, User user2) {
		int result = 0;
		if (!user1.getLastName().equals(user2.getLastName())) {
			if (user1.getLastName().compareTo(user2.getLastName()) > 0)
				result = 1;
			else if (user1.getLastName().compareTo(user2.getLastName()) < 0)
				result = -1;
		} else if (!user1.getFirstName().equals(user2.getFirstName())) {
			if (user1.getFirstName().compareTo(user2.getFirstName()) > 0)
				result = 1;
			else if (user1.getFirstName().compareTo(user2.getFirstName()) < 0)
				result = -1;
		} else if (!user1.getMiddleName().equals(user2.getMiddleName())) {
			if (user1.getMiddleName().compareTo(user2.getMiddleName()) > 0)
				result = 1;
			else if (user1.getMiddleName().compareTo(user2.getMiddleName()) < 0)
				result = -1;
		}
		return result;
	}
}