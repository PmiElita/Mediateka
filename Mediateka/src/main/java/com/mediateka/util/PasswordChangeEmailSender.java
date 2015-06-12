package com.mediateka.util;

import com.mediateka.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class PasswordChangeEmailSender {

	 

    private static final String username="airfast.sup@gmail.com";
    private static final String password="ABab123456";
    private static final Properties properties = new Properties();
    
    private static final String serverUrl = "http://localhost:8080/Mediateka/changePassword"; 
 
    static {
    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.host", "smtp.gmail.com");
    	properties.put("mail.smtp.port", "587");
    	
//    	try {
//			properties.load( new FileInputStream("mail.properties"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
 

	
	
	public static void sendToken(User user, String uniqueToken) throws AddressException, MessagingException{
			
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(username));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
		
		message.setSubject("Password changing token");
		
		String text = 
				" <a href=\""
				+ serverUrl
				+ "?token="
				+ uniqueToken
				+ "\">click here</a> ";
		
		System.out.println("send this text:");
		System.out.println(text);
		
		message.setContent(text,"text/html; charset=utf-8");
		 
		
		Transport.send(message);
	}
}
