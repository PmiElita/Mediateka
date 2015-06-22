package com.mediateka.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messager {
       public static String getMessage(String key, String localeString) throws UnsupportedEncodingException{
    	   Locale locale = new Locale(localeString);
   		ResourceBundle messages=ResourceBundle.getBundle("messages", locale);
   		return new String(messages.getString(key).getBytes("ISO-8859-1"), "UTF-8");
       }
}
