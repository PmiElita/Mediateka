package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class BookLanguage {
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private State state;

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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "BookLanguage [id=" + id + ", name=" + name + ", state=" + state
				+ "]";
	}
}