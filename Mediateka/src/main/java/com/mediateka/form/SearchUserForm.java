package com.mediateka.form;


import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class SearchUserForm {
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, minLength=0, maxLength = 200)
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	
	
}
