package com.mediateka.form;

public class BookRegistrationForm {

	String name;
	String author;
	String type;
	String meaning;
	String language;
	String media;

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