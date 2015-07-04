package com.mediateka.model;

import java.sql.Timestamp;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class Report {

	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date")
	private Timestamp date;

	@Column(name = "state")
	private State state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", email=" + email + ", text=" + text
				+ ", name=" + name + ", date=" + date + ", state=" + state
				+ "]";
	}
	
	
	
}
