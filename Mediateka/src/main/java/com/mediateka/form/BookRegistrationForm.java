package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class BookRegistrationForm {

	@Validation(regexp=RegExps.ONLY_CHARS, length=45)
	private String name;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, length=45)
	private String author;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, length=8)
	private String type;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, length=8)
	private String meaning;

	@Validation(regexp=RegExps.ONLY_DIGITS, length=8)
	private String language;
	
	@Validation(regexp=RegExps.ONLY_DIGITS, length=8)
	private String media;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "BookRegistrationForm [name=" + name + ", author=" + author
				+ ", type=" + type + ", meaning=" + meaning + ", language="
				+ language + ", media=" + media + "]";
	}

}