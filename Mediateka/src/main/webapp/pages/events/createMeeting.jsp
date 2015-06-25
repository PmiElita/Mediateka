<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>
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

					<h3>Create meeting</h3>
					${message}
					<form name="createMeeting" id="create_meeting"
						action="CreateMeeting" method="post">

						<div class="row">
							Name:<input type="text" id="name" name="name" required
								pattern=".{1,45}"><br>
						</div>

						<div class="row">
							<p id="wrongDate"></p>
							<p id="wrongTime"></p>

							<div class="col s4">
								<label for="date" id="labelDate">Date:</label> <input id="date"
									name="date" type="text" data-field="date" required
									onchange="meeting()">
								<div id="dtBox"></div>
							</div>

							<div class="col s4">
								<label for="timeFrom" id="labelTimeFrom">Time from:</label> <input
									id="timeFrom" name="timeFrom" type="text" data-field="time"
									required onchange="timeChangeMeeting()">

							</div>

							<div class="col s4">
								<label for="timeTill" id="labelDateTill">Time till:</label> <input
									id="timeTill" name="timeTill" type="text" data-field="time"
									required onchange="timeChangeMeeting()">
							</div>
						</div>

						<div class="row">
							Description:
							<textarea name="description" pattern=".{0,255}"
								class="materialize-textarea"></textarea>
							<input type="submit" id="submit" value="Create meeting"
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