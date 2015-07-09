package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.comparator.BookByFormRecordCount;
import com.mediateka.exception.WrongInputException;
import com.mediateka.form.SearchBookForm;
import com.mediateka.model.Book;
import com.mediateka.service.BookLanguageService;
import com.mediateka.service.BookMeaningService;
import com.mediateka.service.BookService;
import com.mediateka.service.BookTypeService;
import com.mediateka.service.MediaService;
import com.mediateka.util.FormValidator;
import com.mediateka.util.ObjectFiller;

@Controller
public class SearchBookController {

	private static final int BOOKS_COUNT_ON_PAGE = 3;

	@Request(url = "searchBook", method = "get")
	public static void getSearchBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		List<Book> books = BookService.getBookAll();
		Map<String, Book> bookItems = new LinkedHashMap<String, Book>();
		boolean haveMoreResults = false;
		if (books!=null){
		Collections.sort(books, new BookByFormRecordCount());
	
	
	
			for (int i = 0; i < books.size() && i < BOOKS_COUNT_ON_PAGE; i++) {
				bookItems.put(
						MediaService.getMediaById(books.get(i).getMediaId())
								.getPath().replace("\\", "/"),books.get(i));
			}
		
		if (books.size() > BOOKS_COUNT_ON_PAGE) {
			haveMoreResults = true;
		}
		}
		
		request.setAttribute("haveMoreResults", haveMoreResults);
		request.setAttribute("index", 1);
		request.setAttribute("bookItems", bookItems);
		request.setAttribute("bookTypes", BookTypeService.getBookTypeAll());
		request.setAttribute("bookMeanings",
				BookMeaningService.getBookMeaningAll());
		request.setAttribute("bookLanguages",
				BookLanguageService.getBookLanguageAll());
		request.getRequestDispatcher("pages/books/search_book.jsp").forward(
				request, response);
	}

	@Request(url = "get_books_by_regexp", method = "get")
	public static void getBooksByRegexp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {
		String regexp = request.getParameter("query");
		List<String> result = new ArrayList<String>();
		List<Book> books = BookService.getBooksByRegexp(regexp);
		if (books != null) {
			for (Book book : books) {
				result.add("\"" + book.getName() + "\" " + book.getAuthor());
			}
		}
		Collections.sort(result);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("query", request.getParameter("query"));
		map.put("suggestions", result);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}

	@Request(url = "getMoreBooks", method = "get")
	public static void getMoreBooks(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, ServletException, IOException, IllegalArgumentException, WrongInputException {
		SearchBookForm searchBookForm = new SearchBookForm();
		ObjectFiller.fill(searchBookForm, request);
		FormValidator.validate(searchBookForm);
		List<Book> books = new ArrayList<Book>();
		Integer typeId = null;
		Integer languageId = null;
		Integer meaningId = null;
		if (searchBookForm.getType() != "") {
			typeId = BookTypeService.getBookTypeById(
					Integer.parseInt(searchBookForm.getType())).getId();
		}
		if (searchBookForm.getMeaning() != "") {
			meaningId = BookMeaningService.getBookMeaningById(
					Integer.parseInt(searchBookForm.getMeaning())).getId();
		}
		if (searchBookForm.getLanguage() != "") {
			languageId = BookLanguageService.getBookLanguageById(
					Integer.parseInt(searchBookForm.getLanguage())).getId();
		}
		List<Book> temp;
		if (searchBookForm.getQuery() != "") {
			temp = BookService.getBooksByRegexp(searchBookForm.getQuery());
		} else {
			temp = BookService.getBookAll();
		}
		for (Book book : temp) {

			if (typeId != null && book.getTypeId() != typeId) {
				continue;
			}
			if (meaningId != null && book.getMeaningId() != meaningId) {
				continue;
			}
			if (languageId != null && book.getLanguageId() != languageId) {
				continue;
			}
			books.add(book);
		}
		Integer index = Integer.parseInt(request.getParameter("index"));
		Collections.sort(books, new BookByFormRecordCount());
		boolean haveMoreResults = false;

		Map<String, Book> bookItems = new LinkedHashMap<String, Book>();
		if (books != null) {
			for (int i = index * BOOKS_COUNT_ON_PAGE; i < books.size()
					&& i < (index + 1) * BOOKS_COUNT_ON_PAGE; i++) {
				bookItems.put(
						MediaService.getMediaById(books.get(i).getMediaId())
								.getPath().replace("\\", "/"), books.get(i));
			}
		}
		if (books.size() > (index + 1) * BOOKS_COUNT_ON_PAGE) {
			haveMoreResults = true;
		}
		request.setAttribute("haveMoreResults", haveMoreResults);
		request.setAttribute("index", index + 1);
		request.setAttribute("bookItems", bookItems);
		request.getRequestDispatcher("pages/books/search_book_central.jsp")
				.forward(request, response);
	}

	@Request(url = "getBooksBySearchOptions", method = "get")
	public static void getBooksBySearchOptions(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, WrongInputException, SQLException,
			ReflectiveOperationException, ServletException, IOException {
		SearchBookForm searchBookForm = new SearchBookForm();
		ObjectFiller.fill(searchBookForm, request);
		FormValidator.validate(searchBookForm);
		List<Book> books = new ArrayList<Book>();
		List<Book> temp;
		if (searchBookForm.getQuery().indexOf("\"")!=-1){
		String bookName=searchBookForm.getQuery().substring(1).split("\" ")[0];
		String bookAuthor = searchBookForm.getQuery().split("\" ")[1];
		temp=BookService.getBooksByNameAndAuthor(bookName, bookAuthor);
		} else if (searchBookForm.getQuery() != "") {
			temp = BookService.getBooksByRegexp(searchBookForm.getQuery());
		} else {
			temp = BookService.getBookAll();
		}
		Integer typeId = null;
		Integer languageId = null;
		Integer meaningId = null;
		if (searchBookForm.getType() != "") {
			typeId = BookTypeService.getBookTypeById(
					Integer.parseInt(searchBookForm.getType())).getId();
		}
		if (searchBookForm.getMeaning() != "") {
			meaningId = BookMeaningService.getBookMeaningById(
					Integer.parseInt(searchBookForm.getMeaning())).getId();
		}
		if (searchBookForm.getLanguage() != "") {
			languageId = BookLanguageService.getBookLanguageById(
					Integer.parseInt(searchBookForm.getLanguage())).getId();
		}
		if (temp!=null){
		for (Book book : temp) {

			if (typeId != null && book.getTypeId() != typeId) {
				continue;
			}
			if (meaningId != null && book.getMeaningId() != meaningId) {
				continue;
			}
			if (languageId != null && book.getLanguageId() != languageId) {
				continue;
			}
			books.add(book);
		}
		}
		Collections.sort(books, new BookByFormRecordCount());
		boolean haveMoreResults = false;
		Map<String, Book> bookItems = new LinkedHashMap<String, Book>();
		if (books != null) {
			for (int i = 0; i < books.size() && i < BOOKS_COUNT_ON_PAGE; i++) {
				bookItems.put(
						MediaService.getMediaById(books.get(i).getMediaId())
								.getPath().replace("\\", "/"), books.get(i));
			}
		}
		if (books.size() > BOOKS_COUNT_ON_PAGE) {
			haveMoreResults = true;
		}
		request.setAttribute("haveMoreResults", haveMoreResults);
		request.setAttribute("index", 1);
		request.setAttribute("bookItems", bookItems);
		request.getRequestDispatcher("pages/books/search_book_central.jsp")
				.forward(request, response);
	}
}
