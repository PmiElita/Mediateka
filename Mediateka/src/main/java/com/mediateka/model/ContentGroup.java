package com.mediateka.model;

import java.sql.Timestamp;
import java.util.Comparator;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.State;

public class ContentGroup implements Comparable<ContentGroup>{

	@Column(name = "id")
	private Integer id;

	@Column(name = "type")
	private ContentGroupType type;

	@Column(name = "name")
	private String name;

	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "creation_date")
	private Timestamp creationDate;

	@Column(name = "text")
	private String text;

	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "club_id")
	private Integer clubId;

	@Column(name = "state")
	private State state;

	@Column(name = "like")
	private Integer like;

	@Column(name = "dislike")
	private Integer dislike;

	@Column(name = "parent_id")
	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public void setType(ContentGroupType type) {
		this.type = type;
	}

	public ContentGroupType getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
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

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getDislike() {
		return dislike;
	}

	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ContentGroup [id=" + id + ", type=" + type + ", name=" + name
				+ ", creatorId=" + creatorId + ", creationDate=" + creationDate
				+ ", text=" + text + ", eventId=" + eventId + ", clubId="
				+ clubId + ", state=" + state + ", like=" + like + ", dislike="
				+ dislike + ", parentId=" + parentId + "]";
	}

	@Override
	public int compareTo(ContentGroup o) {
		// TODO Auto-generated method stub
		return o.getCreationDate().compareTo(this.getCreationDate());
	}


	

}