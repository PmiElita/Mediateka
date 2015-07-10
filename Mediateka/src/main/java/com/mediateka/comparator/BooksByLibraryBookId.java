package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.Book;

public class BooksByLibraryBookId implements Comparator<Book> {

	@Override
	public int compare(Book b0, Book b1) {
		return b0.getLibraryBookId().compareTo(b1.getLibraryBookId());
	}

}
