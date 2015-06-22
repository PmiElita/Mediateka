package com.mediateka.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.mediateka.model.User;
import com.mediateka.model.enums.State;

public class ShowUsers  extends SimpleTagSupport {

	private List<User> users;

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void doTag() throws JspException, IOException {

		if (users != null) {
		
			JspWriter out = getJspContext().getOut();
			try {
				for (User u : users) {
				out.write("<div>");
				out.write("<h3>"+u.getLastName()+"</h3>");
				out.write("<h3>"+u.getFirstName()+"</h3>");
				out.write("<h3>"+u.getMiddleName()+"</h3>");
                out.write("<label>birth date</label><h3>"+new SimpleDateFormat("dd.MM.yyyy").format(new Date(u.getBirthDate().getTime()))+"</h3>");
                out.write("<label>formular number</label><h3>"+u.getFormId()+"</h3>");
                out.write("<button value=\""+u.getId()+"\" onclick=\"blockUser(this)\">");
                if (u.getState()==State.ACTIVE){
                out.write("Block"); 
                } else if(u.getState()==State.BLOCKED){
                	 out.write("Unblock"); 
                }
                out.write("</button>");
                out.write("<a href=\"editUser\"><button>Edit</button></a>");
                out.write("<a href=\"addFormRecord\"><button>Add record</button></a>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JspWriter out = getJspContext().getOut();
			out.println("no users");
		}

	}
}
