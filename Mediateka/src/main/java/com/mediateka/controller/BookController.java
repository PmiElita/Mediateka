package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.Book;
import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.State;
import com.mediateka.util.FileLoader;

import static com.mediateka.service.MediaService.*;
import static com.mediateka.service.BookMeaningService.*;
import static com.mediateka.service.BookTypeService.*;
import static com.mediateka.service.BookLanguageService.*;
import static com.mediateka.service.BookService.*;

@Controller
public class BookController {

	@Request(url = "CreateBook", method = "get")
	public static void BookCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
		request.setAttribute("book_meaning",
				getBookMeaningByState(State.ACTIVE));
		request.setAttribute("book_language",
				getBookLanguageByState(State.ACTIVE));
		request.getRequestDispatcher("pages/fedunets12.06/create_book.jsp")
				.forward(request, response);
		request.removeAttribute("book_type");
		request.removeAttribute("book_meaning");
		request.removeAttribute("book_language");
	}

	@Request(url = "CreateBook", method = "post")
	public static void BookCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SecurityException, IllegalArgumentException, SQLException,
			ReflectiveOperationException {

		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "book ava");
		HashMap<String, String> parameterMap = fileLoader.getParameterMap();

		StringBuilder message = new StringBuilder();
		boolean fail = false;

		// book name valid
		if (parameterMap.get("name") == null
				|| parameterMap.get("name").equals("")) {
			fail = true;
			message.append("Book name is empty. ");
		} else if (parameterMap.get("name").length() > 45) {
			fail = true;
			message.append("Book name to long. ");
		}

		// book author valid
		if (parameterMap.get("author") == null
				|| parameterMap.get("author").equals("")) {
			fail = true;
			message.append("Book author is empty. ");
		} else if (parameterMap.get("author").length() > 45) {
			fail = true;
			message.append("Book author to long. ");
		}

		// book meaning valid
		int meaningId = -1;
		if (parameterMap.get("meaning") == null
				|| parameterMap.get("meaning").equals("")) {
			fail = true;
			message.append("Book meaning is empty. ");
		} else {
			try {
				meaningId = Integer.parseInt(parameterMap.get("meaning"));
				if (getBookMeaningById(meaningId).getName() == null) {
					fail = true;
					message.append("No such book meaning. ");
				}
			} catch (NumberFormatException e) {
				fail = true;
				message.append("No such book meaning. ");
			}
		}

		// book type valid
		int typeId = -1;
		if (parameterMap.get("type") == null
				|| parameterMap.get("type").equals("")) {
			fail = true;
			message.append("Book type is empty. ");
		} else {
			try {
				typeId = Integer.parseInt(parameterMap.get("type"));
				if (getBookTypeById(typeId).getName() == null) {
					fail = true;
					message.append("No such book type. ");
				}
			} catch (NumberFormatException e) {
				fail = true;
				message.append("No such book type. ");
			}
		}

		// book language valid
		int languageId = -1;
		if (parameterMap.get("language") == null
				|| parameterMap.get("language").equals("")) {
			fail = true;
			message.append("Book language is empty. ");
		} else {
			try {
				languageId = Integer.parseInt(parameterMap.get("language"));
				if (getBookLanguageById(languageId).getName() == null) {
					fail = true;
					message.append("No such book language. ");
				}
			} catch (NumberFormatException e) {
				fail = true;
				message.append("No such book language. ");
			}
		}

		// book media valid
		Media media = new Media();
		try {
			media.setName(fileLoader.getDefaultFileName());
			media.setPath(fileLoader.getRelativePath());
			media.setType(MediaType.IMAGE);
			media.setState(State.ACTIVE);
			media = callSaveMedia(media);
		} catch (WrongInputException e) {
			fail = true;
			message.append("Wrong image loading. ");
		}

		// logic
		if (!fail) {
			Book book = new Book();
			book.setName(parameterMap.get("name"));
			book.setAuthor(parameterMap.get("author"));
			book.setState(State.ACTIVE);
			book.setMeaningId(meaningId);
			book.setTypeId(typeId);
			book.setLanguageId(languageId);
			book.setMediaId(media.getId());
			saveBook(book);
			message.append("Book added. ");
		}

		request.setAttribute("message", message.toString());
		request.getRequestDispatcher("pages/fedunets12.06/create_book.jsp")
				.forward(request, response);
		request.removeAttribute("message");

	}
}