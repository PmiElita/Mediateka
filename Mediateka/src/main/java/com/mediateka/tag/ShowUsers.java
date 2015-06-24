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

public class ShowUsers extends SimpleTagSupport {

	private List<User> users;

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void doTag() throws JspException, IOException {

		if (users != null) {

			JspWriter out = getJspContext().getOut();
			try {
				for (User u : users) {
					out.write("<div class=\"user\">");
					out.write("<div class=\"row\">");
					out.write("<label class=\"user_label\">" + u.getLastName()
							+ " " + u.getFirstName() + " " + u.getMiddleName()
							+ "</label>");
					out.write("</div>");
					out.write("<div class=\"row\">");
					out.write("<label class=\"text_label\">Birth date:</label><span>"
							+ new SimpleDateFormat("dd.MM.yyyy")
									.format(new Date(u.getBirthDate().getTime()))
							+ "</span>");
					out.write("</div>");
					out.write("<div class=\"row\">");
					out.write("<label class=\"text_label\">Formular number: </label><span>"
							+ u.getFormId() + "</span>");
					out.write("</div>");
					out.write("<div class=\"row\">");
					out.write("<button class=\"waves-effect waves-teal btn-flat\" value=\""
							+ u.getId() + "\" onclick=\"blockUser(this)\">");
					if (u.getState() == State.ACTIVE) {
						out.write("Block");
					} else if (u.getState() == State.BLOCKED) {
						out.write("Unblock");
					}
					out.write("</button>");
					out.write("<a class=\"waves-effect waves-teal btn-flat\" href=\"editUser\">Edit</a>");
					out.write("<a class=\"waves-effect waves-teal btn-flat\" href=\"addFormRecord\">Add record</a>");
					out.write("</div>");
					out.write("</div>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
