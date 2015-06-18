package com.mediateka.form;

import com.mediateka.annotation.DateField;
import com.mediateka.annotation.EnumField;
import com.mediateka.annotation.Validation;
import com.mediateka.model.enums.EventType;
import com.mediateka.util.RegExps;

public class EventRegistrationForm {

	@EnumField(enumClass=EventType.class)
	private String type;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength = 250, minLength=1)
	private String name;
	
	@DateField(format = "dd.MM.yyyy")
	private String dateFrom;
	
	@DateField(format= "dd.MM.yyyy")
	private String dateTill;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength = 250, minLength=0)
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
		return "ClubRegistrationForm [type=" + type + ", name=" + name
				+ ", dateFrom=" + dateFrom + ", dateTill=" + dateTill
				+ ", description=" + description + "]";
	}

}