package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class BookType {

	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="state")
	private State state;
}
