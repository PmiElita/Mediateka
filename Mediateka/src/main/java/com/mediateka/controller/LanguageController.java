package com.mediateka.controller;



import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;

@Controller
public class LanguageController {
	@Request(url="chooseLanguage", method="get")
	public static void chooseLanguage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String lang = request.getParameter("lang");
		
		switch (lang){
		case "uk":
			response.addCookie(new Cookie("lang", "uk-UA"));
			break;

		default:
		case "en":
			response.addCookie(new Cookie("lang", "en-US"));
			break;
		}
		
		
		response.sendRedirect(request.getHeader("referer"));
		return;
	}
}
