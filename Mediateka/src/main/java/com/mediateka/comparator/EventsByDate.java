package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.Event;

public class EventsByDate implements Comparator<Event> {
	@Override
	public int compare(Event event1, Event event2) {
		int result = 0;
		long currentTime = new java.util.Date().getTime();
		long timeFrom1 = event1.getDateFrom().getTime();
		long timeFrom2 = event2.getDateFrom().getTime();
		long timeTill1 = event1.getDateFrom().getTime();
		long timeTill2 = event2.getDateFrom().getTime();
		if (timeFrom1 < currentTime && timeFrom2 < currentTime
				&& timeTill1 > currentTime && timeTill2 > currentTime) {
			if (timeTill1 < timeTill2)
				result = -1;
			else if (timeTill1 > timeTill2)
				result = 1;
		} else if (timeTill1 < currentTime && timeTill2 < currentTime) {
			if (timeTill1 > timeTill2)
				result = -1;
			else if (timeTill1 < timeTill2)
				result = 1;
		} else if (timeFrom1 > currentTime && timeFrom2 > currentTime) {
			if (timeFrom1 < timeFrom2)
				result = -1;
			else if (timeFrom1 > timeFrom2)
				result = 1;
		} else {
			if (timeTill1 > timeTill2)
				result = -1;
			else if (timeTill1 < timeTill2)
				result = 1;
		}
		return result;
	}
}