package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.ClubEventUserType;

public class ClubEventUser {
	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "club_id")
	private Integer clubId;
	
	@Column(name = "event_id")
	private Integer eventId;
	
	@Column(name = "type")
	private ClubEventUserType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public ClubEventUserType getType() {
		return type;
	}

	public void setType(ClubEventUserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ClubEventUser [id=" + id + ", userId=" + userId + ", clubId="
				+ clubId + ", eventId=" + eventId + ", type=" + type + "]";
	}
	
	

}
