package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.Book;

public class BooksByName implements Comparator<Book> {
	@Override
	public int compare(Book b1, Book b2) {
		int result = 0;
		if (b1.getName().compareTo(b2.getName()) > 0)
			result = -1;
		if (b1.getName().compareTo(b2.getName()) < 0)
			result = 1;
		return result;
	}
}