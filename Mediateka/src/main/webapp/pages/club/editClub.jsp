<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.State"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />
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

				<form id="editClub" action="editClub" name="editClub" method="post">
					<div class="row">
						<fmt:message bundle="${msg}" key="edit_club.club_name" />
						<input name="club_name" id="club_name" type="text"
							value="${club_name}" form="editClub" /> <br>
					</div>
					<div class="row">
						<textarea name="club_description" id="club_description " cols="40"
							rows="5" form="editClub">${club_description}</textarea>
					</div>
					<input type="submit" id="subbut" name="subbut" value="save"
						form="editClub"> <input hidden="true" name="clubId"
						value="${clubId}">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>