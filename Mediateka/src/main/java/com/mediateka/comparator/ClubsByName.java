package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.Club;

public class ClubsByName implements Comparator<Club> {
	@Override
	public int compare(Club club1, Club club2) {
		int result = 0;
		if (club1.getName().compareTo(club2.getName()) > 0)
			result = 1;
		else if (club1.getName().compareTo(club2.getName()) < 0)
			result = -1;
		return result;
	}
}