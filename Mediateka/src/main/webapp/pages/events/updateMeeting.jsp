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
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<script src= "js/eventCreation.js"></script>
<jsp:include page="../general/head.jsp" />
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div id="creation_form">
			<div class="container">

				<h3>Create meeting</h3>
				${message}
				<form name="createMeeting" id="create_meeting"
					action="CreateMeeting" method="post">

					<div class="row">
						Name:<input type="text" id="name" name="name" required
							pattern=".{1,45}"  value="${event.getName()}"><br>
					</div>

					<div class="row">
						<p id="wrongDate"></p>
						<p id="wrongTime"></p>

						<div class="col s4">
							<label for="date" id="labelDate">Date:</label> <input id="date"
								name="date" type="text" data-field="date" required onchange="meeting()" value="${dateFrom}">
							<div id="dtBox"></div>
						</div>

						<div class="col s4">
							<label for="timeFrom" id="labelTimeFrom">Time from:</label> <input
								id="timeFrom" name="timeFrom" type="text" data-field="time" required onchange="timeChangeMeeting()" value="${timeFrom}">

						</div>

						<div class="col s4">
							<label for="timeTill" id="labelDateTill">Time till:</label> <input
								id="timeTill" name="timeTill" type="text" data-field="time" required onchange="timeChangeMeeting()" value="${timeTill}">
						</div>
					</div>

					<div class="row">
						Description:
						<textarea name="description" pattern=".{0,255}"
							class="materialize-textarea"><c:out value="${event.getDescription()}"/></textarea>
							
							<!-- XXX -->
							Meeting avatar:<input type="file" name="image" id="image" placeholder="book cover screenshot..." value="${event.getAvaId()}" onchange="readURL(this);"><img id="photo" src="${imagePath}"><br>
							State:<c:if test="${event.state eq State.ACTIVE}"><input type="radio" name="state" value="active" checked>Active <input type="radio" name="state" value="blocked">Blocked</c:if>
			 					  <c:if test="${event.state eq State.BLOCKED}"><input type="radio" name="state" value="active">Active <input type="radio" name="state" value="blocked" checked>Blocked</c:if><br>
							<!-- XXX -->
							
						<input type="submit" id="submit" value="Create meeting"
							class="btn" disabled="true">
					</div>

				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>