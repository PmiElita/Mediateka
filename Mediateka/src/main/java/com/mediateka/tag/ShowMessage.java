package com.mediateka.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.mediateka.util.Messager;



public class ShowMessage  extends SimpleTagSupport{ 
private String key;
private String locale;

public void setKey(String key) {
	this.key = key;
}
public void setLocale(String locale) {
	this.locale = locale;
}

public void doTag()
	      throws JspException, IOException{
	 JspWriter out = getJspContext().getOut();
	 String message = Messager.getMessage(key, locale);
	 if (message!= null){
		out.println(Messager.getMessage(key, locale));
	 } else {
		 out.println("error");
	 }
	    }
}
