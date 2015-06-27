package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class ClubRegistrationForm {

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 250, minLength = 1)
	private String name;

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 250, minLength = 0)
	private String description;

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

}
