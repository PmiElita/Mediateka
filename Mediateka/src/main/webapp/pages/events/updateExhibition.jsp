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
					<fmt:message bundle="${msg}" key="update_exhibition" />
				</h3>
				<form name="updateExhibition" id="update_exhibition"
					action="UpdateExhibition" method="post"
					enctype="multipart/form-data">

					<div class="row">
						<fmt:message bundle="${msg}" key="update_exhibition.name" />
						<input type="text" id="name" name="name" required
							pattern=".{1,45}" value="${event.getName()}"><br>
					</div>

					<div class="row">
						<p id="wrongDate"></p>

						<div class="col s6">
							<label for="dateFrom" id="labelDateFrom"><fmt:message
									bundle="${msg}" key="update_exhibition.date_from" /></label> <input
								id="dateFrom" name="dateFrom" type="text" data-field="date"
								required onchange="dateChangeExhibition()" value="${dateFrom}">
							<div id="dtBox"></div>
						</div>

						<div class="col s6">
							<label for="dateTill" id="labelDateTill"><fmt:message
									bundle="${msg}" key="date_till" /></label> <input id="dateTill"
								name="dateTill" type="text" data-field="date" required
								onchange="dateChangeExhibition()" value="${dateTill}">
						</div>
					</div>

					<div class="row">
						<fmt:message bundle="${msg}" key="update_exhibition.description" />
						<textarea name="description" pattern=".{0,255}"
							class="materialize-textarea"><c:out
								value="${event.getDescription()}" /></textarea>
						<!-- XXX -->
						<fmt:message bundle="${msg}"
							key="update_exhibition.exhibition_avatar" />
						<input type="file" name="image" id="image"
							placeholder="book cover screenshot..."
							value="${event.getAvaId()}" onchange="readURL(this);"><img
							id="photo" src="${imagePath}"><br>
						<fmt:message bundle="${msg}" key="update_exhibition.state" />
						<c:if test="${event.state eq State.ACTIVE}">
							<input type="radio" name="state" value="active" checked>
							<fmt:message bundle="${msg}" key="update_exhibition.active" />
							<input type="radio" name="state" value="blocked">
							<fmt:message bundle="${msg}" key="update_exhibition.blocked" />
						</c:if>
						<c:if test="${event.state eq State.BLOCKED}">
							<input type="radio" name="state" value="active">
							<fmt:message bundle="${msg}" key="update_exhibition.active" />
							<input type="radio" name="state" value="blocked" checked>
							<fmt:message bundle="${msg}" key="update_exhibition.blocked" />
						</c:if>
						<br>
						<!-- XXX -->
						<input type="submit" id="submit" value="Update exhibition"
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