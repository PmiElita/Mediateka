package com.mediateka.form;

import com.mediateka.annotation.DateField;
import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class MeetingRegistrationForm {

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 45, minLength = 1)
	String name;

	@DateField(format = "dd-MM-yyyy")
	String date;

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 5, minLength = 5)
	String timeFrom;

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 5, minLength = 5)
	String timeTill;

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 255, minLength = 0)
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTill() {
		return timeTill;
	}

	public void setTimeTill(String timeTill) {
		this.timeTill = timeTill;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MeetingRegistrationForm [name=" + name + ", date=" + date
				+ ", timeFrom=" + timeFrom + ", timeTill=" + timeTill
				+ ", description=" + description + "]";
	}
}