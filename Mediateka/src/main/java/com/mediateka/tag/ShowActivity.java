package com.mediateka.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.mediateka.model.Book;
import com.mediateka.model.Event;
import com.mediateka.model.FormRecord;
import com.mediateka.service.BookService;
import com.mediateka.service.EventService;

public class ShowActivity extends SimpleTagSupport {
	private List<FormRecord> formRecords;
	private String locale;

	public void setFormRecords(List<FormRecord> formRecords) {
		this.formRecords = formRecords;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void doTag() throws JspException, IOException {

		ResourceBundle messages;
		if (this.locale.equals("uk-UA")) {
			messages = ResourceBundle.getBundle("translations/activity_uk_UA");
		} else {
			messages = ResourceBundle.getBundle("translations/activity_en");
		}

		if (formRecords != null) {

			JspWriter out = getJspContext().getOut();
			try {
				out.write("<table class=\"bordered\">");
				out.write("<tr>");
				out.write("<th>" + messages.getString("date") + "</th>");

				out.write("<th>" + messages.getString("goal") + "</th>");

				out.write("<th>" + messages.getString("comment") + "</th>");

				out.write("</tr>");
				for (FormRecord formRecord : formRecords) {
					out.write("<tr>");
					out.write("<td>"
							+ new SimpleDateFormat("dd.MM.yyyy")
									.format(new Date(formRecord.getDateFrom()
											.getTime())) + "</td>");
					if (formRecord.getGoal() != null) {
						out.write("<td>" + formRecord.getGoal() + "</td>");
					} else if (formRecord.getBookId() != null) {
						Book book = BookService.getBookById(formRecord
								.getBookId());
						out.write("<td><a style=\"color:#000;\" href=\"bookPage?id="
								+ book.getId()
								+ "\">\""
								+ book.getName()
								+ "\" " + book.getAuthor() + "</a></td>");
					} else if (formRecord.getEventId() != null) {
						Event event = EventService.getEventById(formRecord
								.getEventId());
						out.write("<td><a style=\"color:#000;\" href=\"event?eventId="
								+ event.getId()
								+ "\">"
								+ event.getName()
								+ "</a></td>");
					}
					if (formRecord.getComment() != null) {
						out.write("<td>" + formRecord.getComment() + "</td>");
					} else {
						out.write("<td></td>");
					}
					out.write("</tr>");
				}
				out.write("</table>");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JspWriter out = getJspContext().getOut();
			out.println(messages.getString("no_records"));
		}

	}
}
