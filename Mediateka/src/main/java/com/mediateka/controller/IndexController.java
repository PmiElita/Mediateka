package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.service.ProfessionService;

@Controller
public class IndexController {

	@Request(url = "index", method = "get")
	public static void indexGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ReflectiveOperationException {

		// get list of profession's ids for registration form

		request.setAttribute("professions", ProfessionService.getProfessionAll());

		request.getRequestDispatcher("pages/index/index.jsp").forward(request, response);
	}
}
