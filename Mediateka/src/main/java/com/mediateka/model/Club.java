package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class Club {

	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "ava_id")
	private Integer avaId;

	@Column(name ="state")
	private State state;
	
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

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
		return "Club [id=" + id + ", name=" + name + ", description="
				+ description + ", creatorId="  + ", avaId=" + avaId
				+ "]";
	}

}
