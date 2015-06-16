package com.mediateka.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateConverter {

	public static Timestamp convertIntoTimestamp(String date, String format)
			throws java.text.ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Timestamp result = new Timestamp(dateFormat.parse(date).getTime());
		return result;
	}

}