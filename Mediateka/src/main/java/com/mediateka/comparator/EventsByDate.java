package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.Event;

public class EventsByDate implements Comparator<Event> {

	private static long currentTime = new java.util.Date().getTime();

	@Override
	public int compare(Event event1, Event event2) {

		int result = 0;

		long timeFrom1 = event1.getDateFrom().getTime();
		long timeFrom2 = event2.getDateFrom().getTime();
		long timeTill1 = event1.getDateTill().getTime();
		long timeTill2 = event2.getDateTill().getTime();

		if (isInCurrentTime(timeFrom1, timeTill1)
				&& !isInCurrentTime(timeFrom2, timeTill2))
			result = -1;
		else if (isInCurrentTime(timeFrom2, timeTill2)
				&& !isInCurrentTime(timeFrom1, timeTill1))
			result = 1;
		else if (isInCurrentTime(timeFrom1, timeTill1)
				&& isInCurrentTime(timeFrom2, timeTill2)) {
			if (timeFrom1 < timeFrom2)
				result = -1;
			else if (timeFrom2 < timeFrom1)
				result = 1;
			else if (timeTill1 < timeTill2)
				result = -1;
			else if (timeTill2 < timeTill1)
				result = 1;
		} else if (!isInCurrentTime(timeFrom1, timeTill1)
				&& !isInCurrentTime(timeFrom2, timeTill2)) {
			if (timeFrom1 < timeFrom2 && timeFrom1 < currentTime)
				result = 1;
			else if (timeFrom1 < timeFrom2 && timeFrom1 > currentTime)
				result = -1;
			else if (timeFrom2 < timeFrom1 && timeFrom2 < currentTime)
				result = -1;
			else if (timeFrom2 < timeFrom1 && timeFrom2 > currentTime)
				result = 1;
		}

		return result;

	}

	private boolean isInCurrentTime(long timeFrom, long timeTill) {
		boolean result = false;
		if (timeFrom < currentTime && timeTill > currentTime)
			result = true;
		return result;
	}
}