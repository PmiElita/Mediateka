package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;

public class Media {

	@Column(name = "id")
	private Integer id;

	@Column(name = "type")
	private MediaType type;

	@Column(name = "path")
	private String path;

	@Column(name = "description")
	private String description;

	@Column(name = "content_group_id")
	private Integer contentGroupId;

	@Column(name = "state")
	private State state;

	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public Integer getContentGroupId() {
		return contentGroupId;
	}

	public void setContentGroupId(Integer contentGroupId) {
		this.contentGroupId = contentGroupId;
	}

	@Override
	public String toString() {
		return "Media [id=" + id + ", type=" + type + ", path=" + path
				+ ", description=" + description + ", contentGroupId="
				+ contentGroupId + ", state=" + state + ", name=" + name + "]";
	}
}