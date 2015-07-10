package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class Book {
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "author")
	private String author;

	@Column(name = "state")
	private State state;

	@Column(name = "type_id")
	private Integer typeId;

	@Column(name = "meaning_id")
	private Integer meaningId;

	@Column(name = "language_id")
	private Integer languageId;

	@Column(name = "media_id")
	private Integer mediaId;

	@Column(name = "description")
	private String description;

	@Column(name = "library_book_id")
	private String libraryBookId;
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getMeaningId() {
		return meaningId;
	}

	public void setMeaningId(Integer meaningId) {
		this.meaningId = meaningId;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibraryBookId() {
		return libraryBookId;
	}

	public void setLibraryBookId(String libraryBookId) {
		this.libraryBookId = libraryBookId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", state=" + state + ", typeId=" + typeId + ", meaningId="
				+ meaningId + ", languageId=" + languageId + ", mediaId="
				+ mediaId + "]";
	}
}