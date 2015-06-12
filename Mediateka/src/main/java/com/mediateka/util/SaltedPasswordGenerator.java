package com.mediateka.util;

import java.security.NoSuchAlgorithmException;

public class SaltedPasswordGenerator {

	
	public static String generate(String password, String salt) throws NoSuchAlgorithmException{
		
		return SHA256Hasher.hashSHA256(password + salt);
	}
}
