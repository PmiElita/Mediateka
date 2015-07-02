package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
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

	private static final long WEEK_MILIS = 604800000;
	private static final long MONTH_MILIS = 2592000000l;

	@Request(url = "activity", method = "get")
	@Roles({Role.ADMIN, Role.USER})
	public static void getActivity(HttpServletRequest request,
			HttpServletResponse response) throws ReflectiveOperationException,
			SQLException, ServletException, IOException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		Timestamp currentDate = new Timestamp(new Date().getTime());
		Timestamp weekAgo = new Timestamp(currentDate.getTime() - WEEK_MILIS);

		List<FormRecord> formRecords = FormRecordService
				.getFormRecordsByUserIdAndDateRange(userId, weekAgo,
						currentDate); 
		if (formRecords!=null){
			
        Collections.sort(formRecords, new FormRecordsByDateFrom());
		}
        request.setAttribute("formRecords", formRecords);
		request.getRequestDispatcher("pages/activity/activity.jsp")
				.forward(request, response);
	}

	@Request(url = "reloadActivity", method = "get")
	public static void reloadActivity(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		String period = request.getParameter("period");
		List<FormRecord> result = null;
		Timestamp currentDate = new Timestamp(new Date().getTime());
		if (period != null) {

			if (period.equals("allTime")) {
				result = FormRecordService.getFormRecordByUserId(userId);
			} else {
				Timestamp periodDate;
				if (period.equals("month")) {
					periodDate = new Timestamp(currentDate.getTime()
							- MONTH_MILIS);
				} else {
					periodDate = new Timestamp(currentDate.getTime()
							- WEEK_MILIS);
				}
				result = FormRecordService.getFormRecordsByUserIdAndDateRange(
						userId, periodDate, currentDate);
			}
		} else {
			result = FormRecordService.getFormRecordsByUserIdAndDateRange(
					userId, new Timestamp(currentDate.getTime() - WEEK_MILIS),
					currentDate);
		}
		if (result!=null){
		Collections.sort(result, new FormRecordsByDateFrom());
		}
		request.setAttribute("formRecords", result);
		request.getRequestDispatcher("pages/activity/activity_central.jsp")
				.forward(request, response);
	}
}
