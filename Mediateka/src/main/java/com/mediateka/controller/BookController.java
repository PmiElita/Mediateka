package com.mediateka.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.Book;
import com.mediateka.model.BookMeaning;
import com.mediateka.model.Media;
import com.mediateka.model.User;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.BookMeaningService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.RegExps;

import static com.mediateka.service.MediaService.*;
import static com.mediateka.service.BookMeaningService.*;
import static com.mediateka.service.BookTypeService.*;
import static com.mediateka.service.BookLanguageService.*;
import static com.mediateka.service.BookService.*;

@Controller
public class BookController {
	private static Logger logger = Logger.getLogger(BookController.class);

	// create book

	@Request(url = "books", method = "get")
	public static void goToBooksPageGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		request.getRequestDispatcher("pages/books/books.jsp").forward(request,
				response);
	}

	@Request(url = "CreateBook", method = "get")
	public static void bookCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
		request.setAttribute("book_meaning",
				getBookMeaningByState(State.ACTIVE));
		request.setAttribute("book_language",
				getBookLanguageByState(State.ACTIVE));

		logger.debug((getBookTypeByState(State.ACTIVE) == null));
		request.getRequestDispatcher("pages/books/create_book.jsp").forward(
				request, response);
		request.removeAttribute("book_type");
		request.removeAttribute("book_meaning");
		request.removeAttribute("book_language");
	}

	@Request(url = "CreateBook", method = "post")
	public static void bookCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SecurityException, IllegalArgumentException, SQLException,
			ReflectiveOperationException {
		try {
			new File(request.getServletContext().getRealPath("")
					+ "media\\book ava\\images").mkdir();
			FileLoader fileLoader = new FileLoader();
			fileLoader.loadFile(request, "book ava");
			HashMap<String, String> parameterMap = fileLoader.getParameterMap();

			// book name valid
			if (parameterMap.get("name") == null
					|| parameterMap.get("name").equals("")) {
				throw new WrongInputException("Book name is empty.");
			} else if (parameterMap.get("name").length() > 45) {
				throw new WrongInputException("Book name to long. ");
			}

			// book author valid
			if (parameterMap.get("author") == null
					|| parameterMap.get("author").equals("")) {
				throw new WrongInputException("Book author is empty. ");
			} else if (parameterMap.get("author").length() > 45) {
				throw new WrongInputException("Book author to long. ");
			}

			// book meaning valid
			int meaningId = -1;
			if (parameterMap.get("meaning") == null
					|| parameterMap.get("meaning").equals("")) {
				throw new WrongInputException("Book meaning is empty. ");
			} else {
				try {
					meaningId = Integer.parseInt(parameterMap.get("meaning"));
					if (getBookMeaningById(meaningId) == null) {

						throw new WrongInputException("No such book meaning. ");
					}
				} catch (NumberFormatException e) {
					throw new WrongInputException("No such book meaning. ");
				}
			}

			// book type valid
			int typeId = -1;
			if (parameterMap.get("type") == null
					|| parameterMap.get("type").equals("")) {

				throw new WrongInputException("Book type is empty. ");
			} else {
				try {
					typeId = Integer.parseInt(parameterMap.get("type"));
					if (getBookTypeById(typeId) == null) {

						throw new WrongInputException("No such book type. ");
					}
				} catch (NumberFormatException e) {
					throw new WrongInputException("No such book type. ");
				}
			}

			// book language valid
			int languageId = -1;
			if (parameterMap.get("language") == null
					|| parameterMap.get("language").equals("")) {

				throw new WrongInputException("Book language is empty. ");
			} else {
				try {
					languageId = Integer.parseInt(parameterMap.get("language"));
					if (getBookLanguageById(languageId) == null) {

						throw new WrongInputException("No such book language. ");
					}
				} catch (NumberFormatException e) {
					throw new WrongInputException("No such book language. ");

				}
			}

			// book media valid
			Media media = new Media();
			try {
				fileLoader.getAllFilePathes();
				media = new Media();
				media.setName(fileLoader.getDefaultFileName());
				media.setPath(fileLoader.getRelativePath());
				media.setType(MediaType.IMAGE);
				media.setState(State.ACTIVE);
				media = callSaveMedia(media);
			} catch (WrongInputException e) {
				media = getMediaById(2);
			}

			// logic

			Book book = new Book();
			book.setName(parameterMap.get("name"));
			book.setAuthor(parameterMap.get("author"));
			book.setState(State.ACTIVE);
			book.setMeaningId(meaningId);
			book.setTypeId(typeId);
			book.setLanguageId(languageId);
			book.setMediaId(media.getId());
			saveBook(book);
			String message = "Book added. ";

			request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
			request.setAttribute("book_meaning",
					getBookMeaningByState(State.ACTIVE));
			request.setAttribute("book_language",
					getBookLanguageByState(State.ACTIVE));
			request.setAttribute("message", message);

			request.getRequestDispatcher("pages/books/create_book.jsp")
					.forward(request, response);
			request.removeAttribute("message");
		} catch (WrongInputException e) {
			logger.warn(e);

			request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
			request.setAttribute("book_meaning",
					getBookMeaningByState(State.ACTIVE));
			request.setAttribute("book_language",
					getBookLanguageByState(State.ACTIVE));
			request.setAttribute("message", e.getMessage());

			request.getRequestDispatcher("pages/books/create_book.jsp")
					.forward(request, response);
			request.removeAttribute("message");
			request.removeAttribute("book_type");
			request.removeAttribute("book_meaning");
			request.removeAttribute("book_language");
		}

	}

	// update book

	@Request(url = "UpdateBook", method = "get")
	public static void bookUpdateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getSession().setAttribute("bookId", 3);
		int bookId = Integer.parseInt(request.getSession()
				.getAttribute("bookId").toString());
		Book book = getBookById(bookId);

		Media media = getMediaById(book.getMediaId());
		String imagePath = media.getPath().replace("\\", "/");
		request.setAttribute("imagePath", imagePath);

		request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
		request.setAttribute("book_meaning",
				getBookMeaningByState(State.ACTIVE));
		request.setAttribute("book_language",
				getBookLanguageByState(State.ACTIVE));
		request.setAttribute("book", book);
		request.getRequestDispatcher("pages/books/update_book.jsp").forward(
				request, response);

		request.removeAttribute("book");
		request.removeAttribute("book_type");
		request.removeAttribute("book_meaning");
		request.removeAttribute("book_language");
		request.removeAttribute("imagePath");
	}

	@Request(url = "UpdateBook", method = "post")
	public static void bookUpdatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		try {
			FileLoader fileLoader = new FileLoader();
			fileLoader.loadFile(request, "book ava");
			HashMap<String, String> parameterMap = fileLoader.getParameterMap();

			// book name valid
			if (parameterMap.get("name") == null
					|| parameterMap.get("name").equals("")) {
				throw new WrongInputException("Book name is empty. ");
			} else if (parameterMap.get("name").length() > 45) {
				throw new WrongInputException("Book name to long. ");
			}

			// book author valid
			if (parameterMap.get("author") == null
					|| parameterMap.get("author").equals("")) {
				throw new WrongInputException("Book author is empty. ");
			} else if (parameterMap.get("author").length() > 45) {
				throw new WrongInputException("Book author to long. ");
			}

			// book meaning valid
			int meaningId = -1;
			if (parameterMap.get("meaning") == null
					|| parameterMap.get("meaning").equals("")) {
				throw new WrongInputException("Book meaning is empty. ");
			} else {
				try {
					meaningId = Integer.parseInt(parameterMap.get("meaning"));
					if (getBookMeaningById(meaningId) == null) {

						throw new WrongInputException("No such book meaning. ");
					}
				} catch (NumberFormatException e) {
					throw new WrongInputException("No such book meaning. ");
				}
			}

			// book type valid
			int typeId = -1;
			if (parameterMap.get("type") == null
					|| parameterMap.get("type").equals("")) {

				throw new WrongInputException("Book type is empty. ");
			} else {
				try {
					typeId = Integer.parseInt(parameterMap.get("type"));
					if (getBookTypeById(typeId) == null) {

						throw new WrongInputException("No such book type. ");
					}
				} catch (NumberFormatException e) {
					throw new WrongInputException("No such book type. ");
				}
			}

			// book language valid
			int languageId = -1;
			if (parameterMap.get("language") == null
					|| parameterMap.get("language").equals("")) {

				throw new WrongInputException("Book language is empty. ");
			} else {
				try {
					languageId = Integer.parseInt(parameterMap.get("language"));
					if (getBookLanguageById(languageId) == null) {

						throw new WrongInputException("No such book language. ");
					}
				} catch (NumberFormatException e) {
					throw new WrongInputException("No such book language. ");

				}
			}

			// book media valid
			Media media = new Media();
			try {
				fileLoader.getAllFilePathes();
				media = new Media();
				media.setName(fileLoader.getDefaultFileName());
				media.setPath(fileLoader.getRelativePath());
				media.setType(MediaType.IMAGE);
				media.setState(State.ACTIVE);
				media = callSaveMedia(media);
			} catch (WrongInputException e) {
				int bookId = Integer.parseInt(request.getSession()
						.getAttribute("bookId").toString());
				Book book = getBookById(bookId);
				media = getMediaById(book.getMediaId());
			}

			// logic
			Book book = new Book();
			book.setName(parameterMap.get("name"));
			book.setAuthor(parameterMap.get("author"));
			book.setState(State.valueOf(parameterMap.get("state")));
			book.setMeaningId(meaningId);
			book.setTypeId(typeId);
			book.setLanguageId(languageId);

			book.setMediaId(media.getId());
			book.setId(Integer.parseInt(request.getSession()
					.getAttribute("bookId").toString()));
			updateBook(book);
			String message = "Book updated. ";

			String imagePath = media.getPath().replace("\\", "/");
			request.setAttribute("imagePath", imagePath);
			int bookId = Integer.parseInt(request.getSession()
					.getAttribute("bookId").toString());
			Book bookNew = getBookById(bookId);

			request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
			request.setAttribute("book_meaning",
					getBookMeaningByState(State.ACTIVE));
			request.setAttribute("book_language",
					getBookLanguageByState(State.ACTIVE));
			request.setAttribute("book", bookNew);
			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/books/create_book.jsp")
					.forward(request, response);
			request.removeAttribute("message");
			request.removeAttribute("book");
			request.removeAttribute("book_type");
			request.removeAttribute("book_meaning");
			request.removeAttribute("book_language");
			request.removeAttribute("imagePath");

		} catch (WrongInputException e) {
			logger.warn(e);
			int bookId = Integer.parseInt(request.getSession()
					.getAttribute("bookId").toString());
			Book book = getBookById(bookId);

			Media media = getMediaById(book.getMediaId());
			String imagePath = media.getPath().replace("\\", "/");
			request.setAttribute("imagePath", imagePath);

			request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
			request.setAttribute("book_meaning",
					getBookMeaningByState(State.ACTIVE));
			request.setAttribute("book_language",
					getBookLanguageByState(State.ACTIVE));
			request.setAttribute("book", book);
			request.setAttribute("message", e.getMessage());

			request.getRequestDispatcher("pages/books/create_book.jsp")
					.forward(request, response);
			request.removeAttribute("message");
			request.removeAttribute("book_type");
			request.removeAttribute("book_meaning");
			request.removeAttribute("book_language");
			request.removeAttribute("book");
			request.removeAttribute("imagePath");
		}
	}

	@Request(url = "sergij_test_add_meaning", method = "get")
	public static void addNewBookMeaningPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getRequestDispatcher(
				"pages/form/tmp_form_for_adding_book_related_stuff.jsp")
				.forward(request, response);
		return;
	}

	@Request(url = "sergij_test_add_meaning", method = "post")
	public static void addNewBookMeaning(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		String meaningName = request.getParameter("meaning");
		if (!meaningName.matches(RegExps.ONLY_CHARS)) {
			response.sendRedirect("index");
			return;
		}

		Integer myId = (Integer) request.getSession().getAttribute("userId");

		if (myId == null) {
			response.sendRedirect("index");
			return;
		}

		User me = UserService.getUserById(myId);
		if (me == null) {
			response.sendRedirect("index");
			request.getSession().invalidate();
			return;
		}

		if (me.getRole() != Role.ADMIN) {
			response.sendRedirect("index");
			return;
		}

		BookMeaning newMeaning = new BookMeaning();
		newMeaning.setName(meaningName);
		newMeaning.setState(State.ACTIVE);

		BookMeaningService.saveBookMeaning(newMeaning);
		
		
		response.sendRedirect(request.getRequestURL().toString());

	}

}