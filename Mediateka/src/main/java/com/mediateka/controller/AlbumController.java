package com.mediateka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;



@Controller
public class AlbumController {
	
	
	@Request(url = "viewAlbumPhoto", method = "get")
	public static void viewAlbumPhoto(HttpServletRequest request, HttpServletResponse response){
		
		
		
		
		
	}

}
