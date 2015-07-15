<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_event" var="msg" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>
<jsp:include page="../../js/eventCreation.js.jsp"></jsp:include>

<script src="js/eventCreation.js"></script>
<jsp:include page="../general/head.jsp" />
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<jsp:include page="../user/user_side_nav.jsp" />

			<div id="creation_form">
				<div class="container section white">

					<h3>
						<fmt:message bundle="${msg}" key="meeting.create_meeting" />
					</h3>
					${message}
					<form name="createMeeting" id="create_meeting"
						action="CreateMeeting" method="post">

						<div class="row">
							<fmt:message bundle="${msg}" key="meeting.name" />
							<input type="text" id="name" name="name" required
								pattern=".{1,250}"><br>
						</div>

						<div class="row">
							<p id="wrongDate"></p>
							<p id="wrongTime"></p>

							<div class="col s4">
								<label for="date" id="labelDate"><fmt:message
										bundle="${msg}" key="meeting.date" /></label> <input id="date"
									name="date" type="text" data-field="date" required
									onchange="meeting()">
								<div id="dtBox"></div>
							</div>

							<div class="col s4">
								<label for="timeFrom" id="labelTimeFrom"><fmt:message
										bundle="${msg}" key="meeting.time_from" /></label> <input
									id="timeFrom" name="timeFrom" type="text" data-field="time"
									required onchange="timeChangeMeeting()">

							</div>

							<div class="col s4">
								<label for="timeTill" id="labelDateTill"><fmt:message
										bundle="${msg}" key="meeting.time_till" /></label> <input
									id="timeTill" name="timeTill" type="text" data-field="time"
									required onchange="timeChangeMeeting()">
							</div>
						</div>

						<div class="row">
							<fmt:message bundle="${msg}" key="meeting.description" />
							<textarea name="description" pattern=".{0,255}"
								class="materialize-textarea"></textarea>
							<input type="submit" id="submit"
								value="<fmt:message bundle="${msg}" key="meeting.create_meeting_button" />"
								class="btn" disabled="true">
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />

</body>
</html>