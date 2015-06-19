package com.mediateka.util;


import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class EmailSender {

	 

    private static final String username="airfast.sup@gmail.com";
    private static final String password="ABab123456";
    private static final Properties properties = new Properties();
    
    private static Logger logger = Logger.getLogger(EmailSender.class);
 
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
 

	public static void sendMail(String recipient, String subject, String body) throws MessagingException{
		
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(username));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

		message.setSubject(subject);
		

		message.setContent(body, "text/html; charset=utf-8");

		logger.debug("send email to " + recipient);
		
		Transport.send(message);
		
	}
	

}
