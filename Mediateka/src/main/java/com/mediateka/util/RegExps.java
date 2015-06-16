package com.mediateka.util;

import java.util.regex.Pattern;

public class RegExps {

	
	public static Pattern passwordPattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");
	public static Pattern onlyCharsPattern = Pattern.compile("^[A-Za-zА-Яа-яіїґ’]+$");
	public static Pattern onlyDigitsPattern = Pattern.compile("[0-9]+");
	public static Pattern phoneNumberPattern = Pattern.compile("^[0-9]{12}$");
	public static Pattern emailPattern = Pattern
			.compile("^[A-Z,a-z,0-9_.]+@[A-Z,a-z,0-9_.]+$");

}
