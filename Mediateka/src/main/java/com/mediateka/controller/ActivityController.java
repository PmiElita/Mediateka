package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.annotation.Roles;
import com.mediateka.comparator.FormRecordsByDateFrom;
import com.mediateka.model.FormRecord;
import com.mediateka.model.enums.Role;
import com.mediateka.service.FormRecordService;

@Controller
public class ActivityController {

	@Request(url = "activity", method = "get")
	@Roles({ Role.ADMIN, Role.USER })
	public static void getActivity(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, ServletException, IOException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");

		List<FormRecord> formRecords = FormRecordService.getUserActivity(userId, "week", "anyType");
		
		if (formRecords != null) {

			Collections.sort(formRecords, new FormRecordsByDateFrom());
		}
		request.setAttribute("formRecords", formRecords);
		request.getRequestDispatcher("pages/activity/activity.jsp").forward(
				request, response);
	}
	
	@Request(url = "reloadActivity", method = "get")
	@Roles({ Role.ADMIN, Role.USER })
	public static void reloadActivity(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		String period = request.getParameter("period");
		String type = request.getParameter("type");

		List<FormRecord> result = FormRecordService.getUserActivity(userId,
				period, type);
		if (result != null) {
			Collections.sort(result, new FormRecordsByDateFrom());
		}
		request.setAttribute("formRecords", result);
		request.getRequestDispatcher("pages/activity/activity_central.jsp")
				.forward(request, response);
	}

}
