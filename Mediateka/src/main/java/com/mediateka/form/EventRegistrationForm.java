package com.mediateka.form;

public class EventRegistrationForm {

	String type;
	String name;
	String date_from;
	String date_till;
	String description;

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

	public String getDate_from() {
		return date_from;
	}

	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}

	public String getDate_till() {
		return date_till;
	}

	public void setDate_till(String date_till) {
		this.date_till = date_till;
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
				+ ", dateFrom=" + date_from + ", dateTill=" + date_till
				+ ", description=" + description + "]";
	}

}