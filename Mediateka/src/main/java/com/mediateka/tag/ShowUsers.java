package com.mediateka.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.MediaService;

public class ShowUsers extends SimpleTagSupport {

	private List<User> users;
	private String locale;
	private Integer adminId;
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	
	
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public void doTag() throws JspException, IOException {

		ResourceBundle messages;
		if (this.locale.equals("uk-UA")) {
			messages = ResourceBundle.getBundle("translations/users_uk_UA");
		} else {
			messages = ResourceBundle.getBundle("translations/users_en");
		}
		
		
		
		
		
		if (users != null) {

			JspWriter out = getJspContext().getOut();
			try {
				for (User u : users) {
					out.write("<div class=\"user\">");
					out.write("<div class=\"row user-item\" >");
					out.write("<div class=\"col s3\">");
					out.write("<img height=\"120px\" src=\""+MediaService.getMediaById(u.getAvaId()).getPath().replace('\\', '/')+"\">");
					out.write("</div>");
					out.write("<div class=\"col s6 \">");					
					out.write("<div class=\"row\">");
					out.write("<label class=\"user_label\">" + u.getLastName()
							+ " " + u.getFirstName() + " " + u.getMiddleName()
							+ "</label>");
					out.write("</div>");
					out.write("<div class=\"row\">");
					out.write("<label class=\"text_label\">"
							+ messages.getString("birthDate")
							+ "</label><span>"
							+ new SimpleDateFormat("dd.MM.yyyy")
									.format(new Date(u.getBirthDate().getTime()))
							+ "</span>");
					out.write("</div>");
					out.write("<div class=\"row\">");
					out.write("<label class=\"text_label\">"
							+ messages.getString("formularNumber")
							+ "</label><span>" + u.getFormId() + "</span>");
					out.write("</div>");
					out.write("</div>");
					out.write("<div class=\"row\">");
					if (u.getId()!=adminId){
					out.write("<button class=\"waves-effect waves-teal btn-flat\" value=\""
							+ u.getId() + "\" style=\"margin-right:10px;\" onclick=\"blockUser(this)\">");
					if (u.getState() == State.ACTIVE) {
						out.write(messages.getString("button.block"));
					} else if (u.getState() == State.BLOCKED) {
						out.write(messages.getString("button.unblock"));
					}
					out.write("</button>");
					}
					out.write("<a class=\"waves-effect waves-teal btn-flat\" href=\"editUser?userId=" + u.getId() + "\">"
							+ messages.getString("button.edit") + "</a>");
					out.write("<a class=\"waves-effect waves-teal btn-flat\" href=\"CreateFormRecord?userId="+u.getId()+"\">"
							+ messages.getString("button.addRecord") + "</a>");
					if (u.getId()!=adminId){
					out.write("<button class=\"waves-effect waves-teal btn-flat\" value=\""
							+ u.getId() + "\" style=\"padding:0;\" onclick=\"makeAdmin(this)\">");
					if (u.getRole() !=Role.ADMIN) {
						out.write(messages.getString("button.makeAdmin"));
					} else if (u.getRole() ==Role.ADMIN) {
						out.write(messages.getString("button.makeUser"));
					}
					out.write("</button>");
					}
					out.write("</div>");
				
					out.write("</div>");
					out.write("</div>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
