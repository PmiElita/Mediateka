package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.Event;

public class EventsByName implements Comparator<Event> {
	@Override
	public int compare(Event e1, Event e2) {
		int result = 0;
		if (e1.getName().compareTo(e2.getName()) > 0)
			result = -1;
		if (e1.getName().compareTo(e2.getName()) < 0)
			result = 1;
		return result;
	}
}