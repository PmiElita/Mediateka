package com.mediateka.util;

public class RegExps {

	public static final String PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
	// public static final String PASSWORD = ".*";
	public static final String ONLY_CHARS = "^[A-Za-zА-Яа-яіїґІЇҐ’]+$";
	public static final String ONLY_DIGITS = "[0-9]*";
	public static final String PHONE_NUMBER = "^[0-9]{12}$";
	public static final String EMAIL = "^[A-Z,a-z,0-9_.]+@[A-Z,a-z,0-9_.]+$";
	public static final String ANY_CHARACTERS = ".*";

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getOnlyChars() {
		return ONLY_CHARS;
	}

	public static String getOnlyDigits() {
		return ONLY_DIGITS;
	}

	public static String getPhoneNumber() {
		return PHONE_NUMBER;
	}

	public static String getEmail() {
		return EMAIL;
	}

	public static String getAnyCharacters() {
		return ANY_CHARACTERS;
	}

}
