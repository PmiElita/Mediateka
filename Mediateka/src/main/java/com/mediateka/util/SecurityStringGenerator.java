package com.mediateka.util;

import java.security.SecureRandom;
import java.util.Random;

public class SecurityStringGenerator {
	 private static final Random RANDOM = new SecureRandom();
       public static String generateString(int length){
    	   String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

    	      StringBuilder result = new StringBuilder();
    	      for (int i=0; i<length; i++)
    	      {
    	          int index = (int)(RANDOM.nextDouble()*letters.length());
    	          result.append( letters.substring(index, index+1));
    	      }
    	      return result.toString();
    	   
       }
}
