package com.mediateka.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.User;
import com.mediateka.util.ObjectFiller;

@Controller
public class IndexController {
   
	@Request(url="index", method="get")
	public static void indexGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("pages/main/main.jsp").forward(request, response);
	}
	
	@Request(url="index", method="post")
	public static void indexPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	
	}
}