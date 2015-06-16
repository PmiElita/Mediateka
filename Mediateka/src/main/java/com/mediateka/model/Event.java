package com.mediateka.model;

import java.sql.Timestamp;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.*;

public class Event {

	@Column(name = "id")
	private Integer id;

	@Column(name = "type")
	private EventType type;

	@Column(name = "name")
	private String name;

	@Column(name = "date_from")
	private Timestamp dateFrom;

	@Column(name = "date_till")
	private Timestamp dateTill;

	@Column(name = "club_id")
	private Integer clubId;

	@Column(name = "state")
	private State state;

	@Column(name = "description")
	private String description;

	@Column (name = "ava_id")
	private Integer avaId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTill() {
		return dateTill;
	}

	public void setDateTill(Timestamp dateTill) {
		this.dateTill = dateTill;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvaId() {
		return avaId;
	}

	public void setAvaId(Integer avaId) {
		this.avaId = avaId;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", name=" + name
				+ ", dateFrom=" + dateFrom + ", dateTill=" + dateTill
				+ ", clubId=" + clubId + ", description=" + description + "]";
	}

}