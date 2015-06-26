<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.State"%>

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

				<h3>Update exhibition</h3>

				${message}
				<form name="updateExhibition" id="update_exhibition"
					action="UpdateExhibition" method="post" enctype="multipart/form-data">

					<div class="row">
						Name:<input type="text" id="name" name="name" required
							pattern=".{1,45}" value="${event.getName()}"><br>
					</div>

					<div class="row">
						<p id="wrongDate"></p>

						<div class="col s6">
							<label for="dateFrom" id="labelDateFrom">Date from:</label> <input
								id="dateFrom" name="dateFrom" type="text" data-field="date" required onchange="dateChangeExhibition()" value="${dateFrom}">
							<div id="dtBox"></div>
						</div>

						<div class="col s6">
							<label for="dateTill" id="labelDateTill">Date till:</label> <input
								id="dateTill" name="dateTill" type="text" data-field="date" required onchange="dateChangeExhibition()" value="${dateTill}">
						</div>
					</div>

					<div class="row">
						Description:
						<textarea name="description" pattern=".{0,255}"
							class="materialize-textarea"><c:out value="${event.getDescription()}"/></textarea>
							<!-- XXX -->
						Exhibition avatar:<input type="file" name="image" id="image" placeholder="book cover screenshot..." value="${event.getAvaId()}" onchange="readURL(this);"><img id="photo" src="${imagePath}"><br>
						State:<c:if test="${event.state eq State.ACTIVE}"><input type="radio" name="state" value="active" checked>Active <input type="radio" name="state" value="blocked">Blocked</c:if>
			  				  <c:if test="${event.state eq State.BLOCKED}"><input type="radio" name="state" value="active">Active <input type="radio" name="state" value="blocked" checked>Blocked</c:if><br>
							<!-- XXX -->
						<input type="submit" id="submit" value="Update exhibition"
							class="btn">
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>