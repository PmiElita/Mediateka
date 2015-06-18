package com.mediateka.util;

import java.net.MalformedURLException;
import java.net.URL;
 


import org.apache.commons.lang3.StringEscapeUtils;

import com.mediateka.exception.WrongInputException;

public class VideoFrameGenerator {

	private static String getFrameFromYoutube(URL url)
			throws WrongInputException {
		if  (!url.getPath().equals("/watch")){
			throw new WrongInputException("wrong url");
		}
		
		
		String videoId = null;
		String[] params = url.getQuery().split("&");
	    for (String param : params)  
	    {  
	        String name = param.split("=")[0];
	        
	        if (name.equals("v")){
		        videoId = param.split("=")[1];	        	
	        }

	    }  
		
	    if (videoId == null){
			throw new WrongInputException("wrong url");
	    }

	    StringEscapeUtils.escapeHtml4(videoId);
	    
		return "<iframe width=\"960\" height=\"720\" "
				+ "src=\"https://www.youtube-nocookie.com/embed/"
				+ 	    StringEscapeUtils.escapeHtml4(videoId)
				+ "\" frameborder=\"0\" allowfullscreen></iframe>";
		
	}
	
	
	/**
	 * input example: https://www.youtube.com/watch?v=INIaa1eee18
	 * output example: <iframe width="960" height="720" src="https://www.youtube-nocookie.com/embed/INIaa1eee18" frameborder="0" allowfullscreen></iframe>
	 * @param urlString 
	 * @throws MalformedURLException
	 * @throws WrongInputException
	 */

	public static String generateByURL(String urlString)
			throws MalformedURLException, WrongInputException {

		URL url = new URL(urlString);

		String host = url.getHost();

		switch (host) {
		case "www.youtube.com":
			// get frame for youtube
			return getFrameFromYoutube(url);
		default:
			throw new WrongInputException("wrong url");
		}
	}
	
	
	public static void main(String[] args) throws MalformedURLException, WrongInputException {
		String urlString = "https://www.youtube.com/watch?v=INIaa1eee18";
		System.out.println(urlString);
		System.out.println(generateByURL(urlString));
		
	}
}
