package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class SearchBookForm {
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, minLength=0, maxLength = 200)
	private String query;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, minLength=0, maxLength = 200)
	private String type;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, minLength=0, maxLength = 200)
	private String meaning;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, minLength=0, maxLength = 200)
	private String language;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
}
