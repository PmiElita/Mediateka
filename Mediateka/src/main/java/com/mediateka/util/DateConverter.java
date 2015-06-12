package com.mediateka.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateConverter {

	public static Timestamp convertIntoTimestamp(String date)
			throws java.text.ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp result = new Timestamp(dateFormat.parse(date).getTime());
		return result;
	}

}