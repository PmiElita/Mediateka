package com.mediateka.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.dao.statement.ReportStatements;
import com.mediateka.model.ContentGroup;
import com.mediateka.model.Report;
import com.mediateka.model.enums.ContentGroupType;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.ContentGroupService;
import com.mediateka.service.ReportService;

@Controller
public class ResponseController {

	private static Logger logger = Logger.getLogger(LogInController.class);

	@Request(url = "responseForm", method = "get")
	public static void getResponseForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("pages/general/response_form.jsp").forward(
				request, response);
	}

	@Request(url = "sendResponse", method = "post")
	public static void postResponseForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		System.out.println("sending response");

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

	@Request(url = "getResponses", method = "get")
	public static void getResponses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		System.out.println("getting responses");

		Role myRole = (Role) request.getSession().getAttribute("userRole");

		if (myRole == null) {
			return;
		}

		if ((myRole != Role.ADMIN) && (myRole != Role.MODERATOR)) {
			return;
		}

		Integer offset;
		Integer limit;
		try {
			offset = Integer.parseInt(request.getParameter("offset"));
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (Exception e) {
			return;
		}

		if ((offset < 0) || (limit > 100)) {
			return;
		}

		// return list of responses
		List<Report> responses = ReportService.getResponses(limit, offset);
		if (responses == null) {
			return;
		}

		JSONArray returnValue = new JSONArray();
		for (Report report : responses) {
			JSONObject reportJson = new JSONObject();
			reportJson.put("id", report.getId().toString());
			reportJson.put("name", report.getName());
			reportJson.put("email", report.getEmail());
			reportJson.put("timestamp", report.getDate().toString());
			reportJson.put("text", report.getText());
			
			boolean newFlag = false;
			if (report.getState() == State.ACTIVE) {
				newFlag = true;
			}
			reportJson.put("newFlag", newFlag);
			returnValue.add(reportJson);
		}

		response.getWriter().write(returnValue.toJSONString());
	}

	@Request(url = "showResponsesPage", method = "get")
	public static void showResponsesPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		System.out.println("show responses page");
		Role myRole = (Role) request.getSession().getAttribute("userRole");

		if (myRole == null) {
			return;
		}

		if ((myRole != Role.ADMIN) && (myRole != Role.MODERATOR)) {
			response.sendRedirect("index");
			return;
		}

		request.getRequestDispatcher("pages/responses/show_responses.jsp")
				.forward(request, response);
	}

	@Request(url = "markResponseAsReaded", method = "get")
	public static void markResponseAsReaded(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		System.out.println("mark response as readed");

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

		Report report =  ReportService.getReportById(responseId);
		if (report == null) {
			return;
		}

		report.setState(State.BLOCKED);
		ReportService.updateReport(report);
		return;
	}

	@Request(url = "deleteResponse", method = "get")
	public static void deleteResponse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		System.out.println("delete response");
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

		Report report =  ReportService.getReportById(responseId);
		if (report == null) {
			return;
		}

		report.setState(State.DELETED);
		ReportService.updateReport(report);
		return;
	}

	
	@Request(url = "sendResponseToReport", method = "get")
	public static void sendResponseToReport(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

	}	
}
