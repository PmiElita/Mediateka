package com.mediateka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;

@Controller
public class BookController {

	@Request(url = "CreateBook", method = "get")
	public static void BookCreateGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("pages/fedunets12.06/create_book.jsp")
				.forward(request, response);

	}

	@Request(url = "CreateBook", method = "post")
	public static void BookCreatePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}