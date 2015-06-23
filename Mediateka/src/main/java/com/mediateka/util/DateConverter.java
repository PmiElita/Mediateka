package com.mediateka.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.mediateka.exception.WrongInputException;

public class DateConverter {

	public static Timestamp convertIntoTimestamp(String date, String format)
			throws java.text.ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Timestamp result = new Timestamp(dateFormat.parse(date).getTime());
		return result;
	}

	public static int[] timeValid(String time) throws WrongInputException {
		int[] result = new int[2];
		String[] array = time.split(":");
		try {
			int hour = Integer.parseInt(array[0]);
			int minute = Integer.parseInt(array[1]);
			if (hour > 23 || hour < 0 || minute > 59 || minute < 0)
				throw new NumberFormatException();
			else {
				result[0] = hour;
				result[1] = minute;
			}
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new WrongInputException("Illegal time format");
		}
		return result;
	}
}