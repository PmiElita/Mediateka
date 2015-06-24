package com.mediateka.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Translator {

	public static String getMessage(String propFile,
			HttpServletRequest httpRequest, String key) {
		
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
		
		
		return ResourceBundle.getBundle(propFile).getString(key);
	}
}
