package com.mediateka.util;

import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Translator {

	private ResourceBundle bundle;
	
	public Translator(String propFile,
			HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		String lang = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lang")) {
					lang = cookie.getValue();
					break;
				}
			}
		}

		if (lang == null) {
			lang = "uk-UA";
		}

		switch (lang){
		case "uk-UA":
			propFile = propFile + "_uk_UA";
			break;
		case "en-US":
			propFile = propFile + "_en";
			break;
		}
		
		System.out.println(propFile);
		this.bundle = ResourceBundle.getBundle(propFile);
	}
	
	public String getMessage( String key) {
		return this.bundle.getString(key);
	}
}
