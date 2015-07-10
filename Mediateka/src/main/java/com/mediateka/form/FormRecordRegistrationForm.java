package com.mediateka.form;

public class FormRecordRegistrationForm {

	private String formId;
	private String timeTill;
	private String goal;
	private String book;
	private String event;
	private String other;
	private String comment;

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getTimeTill() {
		return timeTill;
	}

	public void setTimeTill(String timeTill) {
		this.timeTill = timeTill;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "FormRecordForm [formId=" + formId + ", timeTill=" + timeTill
				+ ", book=" + book + ", event=" + event + ", other=" + other
				+ ", comment=" + comment + "]";
	}
}