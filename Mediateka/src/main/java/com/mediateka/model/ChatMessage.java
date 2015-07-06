package com.mediateka.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class ChatMessage {
	@Column(name = "id")
	private Integer id;

	@Column(name = "text")
	private String text;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "club_id")
	private Integer clubId;

	@Column(name = "state")
	private State state;

	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getFormatedDate(){
		return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(creationDate.getTime());
	}
	
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", text=" + text + ", userId="
				+ userId + ", clubId=" + clubId + ", state=" + state
				+ ", creationDate=" + creationDate + "]";
	}



}
