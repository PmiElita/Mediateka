<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.State"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/event_page" var="msg" />
<html>

<head>
<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<script src="js/eventCreation.js"></script>
<jsp:include page="../general/head.jsp" />
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />
		<div id="creation_form">
			<div class="container">

				<h3>
					<fmt:message bundle="${msg}" key="update_meeting" />
				</h3>
				<form name="updateMeeting" id="update_meeting"
					action="UpdateMeeting" method="post" enctype="multipart/form-data">

					<div class="row">
						<fmt:message bundle="${msg}" key="update_meeting.name" />
						<input type="text" id="name" name="name" required
							pattern=".{1,45}" value="${event.getName()}"><br>
					</div>

					<div class="row">
						<p id="wrongDate"></p>
						<p id="wrongTime"></p>

						<div class="col s4">
							<label for="date" id="labelDate"><fmt:message
									bundle="${msg}" key="update_meeting.date" /></label> <input id="date"
								name="date" type="text" data-field="date" required
								onchange="meeting()" value="${dateFrom}">
							<div id="dtBox"></div>
						</div>

						<div class="col s4">
							<label for="timeFrom" id="labelTimeFrom"><fmt:message
									bundle="${msg}" key="update_meeting.time_from" /></label> <input
								id="timeFrom" name="timeFrom" type="text" data-field="time"
								required onchange="timeChangeMeeting()" value="${timeFrom}">

						</div>

						<div class="col s4">
							<label for="timeTill" id="labelDateTill"><fmt:message
									bundle="${msg}" key="update_meeting.time_till" /></label> <input
								id="timeTill" name="timeTill" type="text" data-field="time"
								required onchange="timeChangeMeeting()" value="${timeTill}">
						</div>
					</div>

					<div class="row">
						<fmt:message bundle="${msg}" key="update_meeting.description" />
						<textarea name="description" pattern=".{0,255}"
							class="materialize-textarea"><c:out
								value="${event.getDescription()}" /></textarea>

						<!-- XXX -->
						<fmt:message bundle="${msg}" key="update_meeting.meeting_avatar" />
						<input type="file" name="image" id="image"
							placeholder="book cover screenshot..."
							value="${event.getAvaId()}" onchange="readURL(this);"><img
							id="photo" src="${imagePath}"><br> State:
						<c:if test="${event.state eq State.ACTIVE}">
							<input type="radio" name="state" value="active" checked><fmt:message bundle="${msg}" key="update_meeting.active"/> <input
								type="radio" name="state" value="blocked"><fmt:message bundle="${msg}" key="update_meeting.blocked"/></c:if>
						<c:if test="${event.state eq State.BLOCKED}">
							<input type="radio" name="state" value="active"><fmt:message bundle="${msg}" key="update_meeting.active"/><input
								type="radio" name="state" value="blocked" checked><fmt:message bundle="${msg}" key="update_meeting.blocked"/></c:if>
						<br>
						<!-- XXX -->

						<input type="submit" id="submit" value="Update meeting"
							class="btn">
					</div>
					<input hidden name="eventId" value="${eventId}">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>