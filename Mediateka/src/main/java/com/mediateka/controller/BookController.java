package com.mediateka.controller;

import static com.mediateka.service.BookLanguageService.getBookLanguageById;
import static com.mediateka.service.BookLanguageService.getBookLanguageByState;
import static com.mediateka.service.BookMeaningService.getBookMeaningById;
import static com.mediateka.service.BookMeaningService.getBookMeaningByState;
import static com.mediateka.service.BookService.getBookById;
import static com.mediateka.service.BookService.saveBook;
import static com.mediateka.service.BookService.updateBook;
import static com.mediateka.service.BookTypeService.getBookTypeById;
import static com.mediateka.service.BookTypeService.getBookTypeByState;
import static com.mediateka.service.MediaService.callSaveMedia;
import static com.mediateka.service.MediaService.getMediaById;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.exception.WrongInputException;
import com.mediateka.model.Book;
import com.mediateka.model.Media;
import com.mediateka.model.enums.MediaType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.BookLanguageService;
import com.mediateka.service.BookMeaningService;
import com.mediateka.service.BookService;
import com.mediateka.service.BookTypeService;
import com.mediateka.service.MediaService;
import com.mediateka.service.UserService;
import com.mediateka.util.FileLoader;
import com.mediateka.util.Translator;

@Controller
public class BookController {
	private static Logger logger = Logger.getLogger(BookController.class);

	@Request(url = "bookPage", method = "get")
	public static void goToBookPageGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = BookService.getBookById(bookId);
		if (book == null || book.getState() == State.DELETED) {
			logger.warn("User " + request.getSession().getAttribute("userId")
					+ "trying to get unexisted/deleted book");
			response.sendError(404);
			return;
		}
		String avaPath = MediaService.getMediaById(book.getMediaId()).getPath()
				.replace('\\', '/');
		request.setAttribute("book", book);
		request.setAttribute("bookType",
				BookTypeService.getBookTypeById(book.getTypeId()).getName());
		request.setAttribute("bookMeaning", BookMeaningService
				.getBookMeaningById(book.getMeaningId()).getName());
		request.setAttribute("bookLanguage", BookLanguageService
				.getBookLanguageById(book.getLanguageId()).getName());
		request.setAttribute("avaPath", avaPath);
		request.getRequestDispatcher("pages/books/book_page.jsp").forward(
				request, response);
	}

	@Request(url = "books", method = "get")
	public static void goToBooksPageGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		if (request.getSession().getAttribute("userId") == null
				|| UserService.getUserById(
						Integer.parseInt(request.getSession()
								.getAttribute("userId").toString())).getRole() != Role.ADMIN
				|| UserService.getUserById(
						Integer.parseInt(request.getSession()
								.getAttribute("userId").toString())).getState() != State.ACTIVE)
			response.sendError(404);
		else
			request.getRequestDispatcher("pages/books/books.jsp").forward(
					request, response);
	}

	// create book
	@Request(url = "CreateBook", method = "get")
	public static void bookCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {
		if (request.getSession().getAttribute("userId") == null
				|| UserService.getUserById(
						Integer.parseInt(request.getSession()
								.getAttribute("userId").toString())).getRole() != Role.ADMIN
				|| UserService.getUserById(
						Integer.parseInt(request.getSession()
								.getAttribute("userId").toString())).getState() != State.ACTIVE) {
			response.sendError(404);
		} else {
			request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
			request.setAttribute("book_meaning",
					getBookMeaningByState(State.ACTIVE));
			request.setAttribute("book_language",
					getBookLanguageByState(State.ACTIVE));
			request.setAttribute("imgPath",
					getMediaById(3).getPath().replace("\\", "/"));
			logger.debug((getBookTypeByState(State.ACTIVE) == null));
			request.getRequestDispatcher("pages/books/create_book.jsp")
					.forward(request, response);
			request.removeAttribute("book_type");
			request.removeAttribute("book_meaning");
			request.removeAttribute("book_language");
			request.removeAttribute("imgPath");
		}
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
		if (request.getSession().getAttribute("userId") == null
				|| UserService.getUserById(
						Integer.parseInt(request.getSession()
								.getAttribute("userId").toString())).getRole() != Role.ADMIN
				|| UserService.getUserById(
						Integer.parseInt(request.getSession()
								.getAttribute("userId").toString())).getState() != State.ACTIVE) {
			response.sendError(404);
			return;
		}

		int bookId = Integer
				.parseInt(request.getParameter("bookId").toString());
		Book book = getBookById(bookId);
		String message = request.getParameter("message");

		if (book != null && book.getState() != State.DELETED) {
			request.setAttribute("book_type", getBookTypeByState(State.ACTIVE));
			request.setAttribute("book_meaning",
					getBookMeaningByState(State.ACTIVE));
			request.setAttribute("book_language",
					getBookLanguageByState(State.ACTIVE));
			request.setAttribute("book", book);
			request.getRequestDispatcher("pages/books/update_book.jsp")
					.forward(request, response);

			request.setAttribute("message", message);
			request.removeAttribute("book");
			request.removeAttribute("book_type");
			request.removeAttribute("book_meaning");
			request.removeAttribute("book_language");
			request.removeAttribute("imagePath");
		} else {
			response.sendError(404);
		}
	}

	@Request(url = "UpdateBook", method = "post")
	public static void bookUpdatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		FileLoader fileLoader = new FileLoader();
		fileLoader.loadFile(request, "book ava");
		HashMap<String, String> parameterMap = fileLoader.getParameterMap();
		try {
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

			if (parameterMap.get("description") == null) {
				throw new WrongInputException("Book author is empty. ");
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
				Integer bookId = Integer.parseInt(parameterMap.get("id"));
				Book book = getBookById(bookId);
				media = getMediaById(book.getMediaId());
			}

			// logic
			Integer bookId = Integer.parseInt(parameterMap.get("id"));
			Book book = BookService.getBookById(bookId);
			book.setName(parameterMap.get("name"));
			book.setAuthor(parameterMap.get("author"));
			book.setMeaningId(meaningId);
			book.setTypeId(typeId);
			book.setLanguageId(languageId);
			book.setDescription(parameterMap.get("description"));
			book.setMediaId(media.getId());
			updateBook(book);
			String message = "Book updated. ";
			String redirectUrl = request.getHeader("referer");
			int indexEnd = redirectUrl.indexOf('&');
			if (indexEnd == -1) {
				indexEnd = redirectUrl.length();
			}
			redirectUrl = redirectUrl.substring(0, indexEnd);

			response.sendRedirect(redirectUrl + "&message=" + message);

		} catch (WrongInputException e) {
			logger.warn(e.getMessage(), e);
			String redirectUrl = request.getHeader("referer");
			int indexEnd = redirectUrl.indexOf('&');
			if (indexEnd == -1) {
				indexEnd = redirectUrl.length();
			}
			redirectUrl = redirectUrl.substring(0, indexEnd);

			response.sendRedirect(redirectUrl + "&message=" + e.getMessage());

		}
	}

	@Request(url = "getBookNameByRegexp", method = "get")
	public static void getBookNameByRegexp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {

		String regexp = request.getParameter("query");
		List<String> bookNames = new ArrayList<String>();
		List<Book> books = BookService.getBookByNameRegex(regexp);
		if (books != null) {
			for (Book book : books) {
				bookNames.add(book.getName());
			}
		}
		Collections.sort(bookNames);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("query", request.getParameter("name"));
		map.put("suggestions", bookNames);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));

	}

	@Request(url = "getAuthorsByRegexp", method = "get")
	public static void getAuthorsByRegexp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, IOException {

		String regexp = request.getParameter("query");
		List<String> authors = new ArrayList<String>();
		List<Book> books = BookService.getBookByAuthorRegex(regexp);
		if (books != null) {
			for (Book book : books) {
				authors.add(book.getAuthor());
			}
		}
		Collections.sort(authors);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("query", request.getParameter("query"));
		map.put("suggestions", authors);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));

	}

	@Request(url = "blockBook", method = "post")
	public static void blockBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ReflectiveOperationException, SQLException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = BookService.getBookById(bookId);
		if (book == null || book.getState() == State.DELETED) {
			logger.warn("trying to block deleted/unexisted book");
			response.sendError(404);
			return;
		}
		String buttonText;
		String toastText;
		Translator translator = new Translator("translations/book_update", request);
		if (book.getState() == State.ACTIVE) {
			book.setState(State.BLOCKED);
			buttonText = translator.getMessage("unblock_book");
			toastText = translator.getMessage("toast.book_was_blocked");
		} else {
			book.setState(State.ACTIVE);
			buttonText = translator.getMessage("block_book");
			toastText = translator.getMessage("toast.book_was_unblocked");
		}
		BookService.updateBook(book);
		Map<String, String> responseData = new HashMap<String, String>();
		responseData.put("buttonText", buttonText);
		responseData.put("toastText", toastText);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(responseData));
	}

	@Request(url = "deleteBook", method = "post")
	public static void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ReflectiveOperationException, SQLException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = BookService.getBookById(bookId);
		if (book == null) {
			logger.warn("trying to delete unexisted book");
			response.sendError(404);
			return;
		}
		String deleteButton;
		String toastText;
		String displayButton;
		if (book.getState() != State.DELETED) {
			book.setState(State.DELETED);
			deleteButton = "Restore book";
			toastText = "Book was deleted";
			displayButton = "none";
		} else {
			book.setState(State.ACTIVE);
			deleteButton = "Delete book";
			toastText = "Book was restored";
			displayButton = "block";
		}
		BookService.updateBook(book);

		Map<String, String> responseData = new HashMap<String, String>();
		responseData.put("buttonText", deleteButton);
		responseData.put("blockButton", "Block book");
		responseData.put("toastText", toastText);
		responseData.put("displayButton", displayButton);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(responseData));
	}
}