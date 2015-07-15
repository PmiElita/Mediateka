package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.annotation.Roles;
import com.mediateka.model.Report;
import com.mediateka.model.User;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ReportService;
import com.mediateka.service.UserService;
import com.mediateka.util.EmailSender;

@Controller
public class ResponseController {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(LogInController.class);

	@Request(url = "responseForm", method = "get")
	public static void getResponseForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ReflectiveOperationException, SQLException {

		request.getRequestDispatcher("pages/general/response_form.jsp")
				.forward(request, response);
	}

	@Request(url = "sendResponse", method = "post")
	public static void postResponseForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {


		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String formBody = request.getParameter("response");

		if (name == null) {
			name = "";
		}

		if (email == null) {
			email = "";
		}

		if ((formBody == null) || (formBody.equals(""))) {
			return;
		}

		Report report = new Report();

		report.setDate(new Timestamp(new java.util.Date().getTime()));
		report.setName(name);
		report.setEmail(email);
		report.setState(State.ACTIVE);
		report.setText(formBody);

		ReportService.saveReport(report);
		return;
	}



	@Request(url = "showResponsesPage", method = "get")
	@Roles({Role.ADMIN})
	public static void showResponsesPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		Role myRole = (Role) request.getSession().getAttribute("userRole");

		if (myRole == null) {
			return;
		}

		if ((myRole != Role.ADMIN) && (myRole != Role.MODERATOR)) {
			response.sendRedirect("index");
			return;
		}

		Integer offset;
		Integer limit;
		try {
			offset = Integer.parseInt(request.getParameter("offset"));
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (Exception e) {
			response.sendRedirect("showResponsesPage?offset=0&limit=5");
			return;
		}

		if (offset < 0){
			return;
		}
		
		if ((limit <= 0) || (limit > 100)){
			return;
		}
		HashMap<Integer, String> admins = new HashMap<Integer, String>();
		List<Report> reports = ReportService.getResponses(limit, offset);
		for (Report r : reports){
			if (r.getState() == State.ACTIVE){
				r.setState(State.BLOCKED);
				ReportService.updateReport(r);
				r.setState(State.ACTIVE);
			}
			
			if (r.getAdminId() != null){
				User admin = UserService.getUserById(r.getAdminId());
				admins.put(r.getAdminId(), admin.getFirstName());
			}
		}

		request.setAttribute("admins", admins);
		Integer totalReports = ReportService.getNumberOfAllReports();
		
		request.setAttribute("responses", reports);

		request.setAttribute("limit", limit);
		request.setAttribute("offset", offset);

		request.setAttribute("first_page", 0);		
		request.setAttribute("last_page", totalReports / limit);
		
		request.setAttribute("current_page", offset / limit);

		request.getRequestDispatcher("pages/responses/show_responses.jsp")
				.forward(request, response);
	}

	@Request(url = "markResponseAsReaded", method = "get")
	@Roles({ Role.ADMIN})
	public static void markResponseAsReaded(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {


		Role myRole = (Role) request.getSession().getAttribute("userRole");

		if (myRole == null) {
			return;
		}

		if ((myRole != Role.ADMIN) && (myRole != Role.MODERATOR)) {
			response.sendRedirect("index");
			return;
		}

		Integer responseId;
		try {
			responseId = Integer.parseInt(request.getParameter("responseId"));
		} catch (Exception e) {
			return;
		}

		Report report = ReportService.getReportById(responseId);
		if (report == null) {
			return;
		}

		report.setState(State.BLOCKED);
		ReportService.updateReport(report);
		return;
	}

	@Request(url = "deleteResponse", method = "get")
	@Roles({Role.ADMIN})
	public static void deleteResponse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		Role myRole = (Role) request.getSession().getAttribute("userRole");

		if (myRole == null) {
			return;
		}

		if ((myRole != Role.ADMIN) && (myRole != Role.MODERATOR)) {
			response.sendRedirect("index");
			return;
		}

		Integer responseId;
		try {
			responseId = Integer.parseInt(request.getParameter("responseId"));
		} catch (Exception e) {
			return;
		}

		Report report = ReportService.getReportById(responseId);
		if (report == null) {
			return;
		}

		report.setState(State.DELETED);
		ReportService.updateReport(report);
		return;
	}

	@Request(url = "sendResponseToReport", method = "get")
	@Roles({Role.ADMIN})
	public static void sendResponseToReport(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException, MessagingException {

		Role myRole = (Role) request.getSession().getAttribute("userRole");

		if (myRole == null) {
			return;
		}

		if ((myRole != Role.ADMIN) && (myRole != Role.MODERATOR)) {
			response.sendRedirect("index");
			return;
		}

		Integer reportId;
		String email;
		String text;
		

		try {
			reportId = Integer.parseInt(request.getParameter("reportId"));
		} catch (Exception e) {
			return;
		}

		Report report =ReportService.getReportById(reportId); 
		if (report == null) {
			return;
		}
		email = report.getEmail();
		text = request.getParameter("text");
		if ((email == null) || (text == null)) {
			return;
		}


		// quote report body
		StringBuilder sb = new StringBuilder();

		sb.append("<blockquote>");
		for (String line : report.getText().split("\\r?\\n")){
			sb.append(line).append("<br>\n");
		}
		sb.append("</blockquote>\n");

		sb.append("<p>");
		for(String line : text.split("\\r?\\n")){
			sb.append(line).append("<br>\n");
		}
		sb.append("</p>");
		

		String mailBody = sb.toString();

		EmailSender.sendMail(email, "Mediateka", mailBody);

		report.setAdminId((Integer)request.getSession().getAttribute("userId"));
		report.setResponse(mailBody);
		ReportService.updateReport(report);
	}
}
