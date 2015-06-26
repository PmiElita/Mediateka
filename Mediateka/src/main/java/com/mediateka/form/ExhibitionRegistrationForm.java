package com.mediateka.form;

import com.mediateka.annotation.DateField;
import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class ExhibitionRegistrationForm {

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 45, minLength = 1)
	private String name;

	@DateField(format = "dd.MM.yyyy")
	private String dateFrom;

	@DateField(format = "dd.MM.yyyy")
	private String dateTill;

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 255, minLength = 0)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTill() {
		return dateTill;
	}

	public void setDateTill(String dateTill) {
		this.dateTill = dateTill;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EventRegistrationForm [name=" + name + ", dateFrom=" + dateFrom
				+ ", dateTill=" + dateTill + ", description=" + description
				+ "]";
	}

}