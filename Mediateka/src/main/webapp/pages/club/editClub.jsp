<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />



<html>
<head>
<jsp:include page="../general/head.jsp" />
<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/viewImage.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>


	<form id="editClub" action="editClub" name="editClub" method="post"
		enctype="multipart/form-data"></form>
	<div>
		<div class="input-field col s5">
			<input name="club_name" id="club_name" type="text"
				value="${club.name}" form="editClub" /> <fmt:message bundle="${msg}" key="edit_club.club_name" />
		</div>
		<div class="input-field col s5">
			<i class="mdi-action-account-circle prefix"></i>
			<textarea name="club_description" id="club_description " cols="40"
				rows="5" form="editClub">${club.description}</textarea>
		</div>
		<div id="selectedFiles">
			<img id="photo" src="${clubAva}">
		</div>
		<div class="input-field col s5">
			<input type="file" name="image" id=image form="editClub"
				onchange="readURL(this);">
		</div>
		<input type="submit" id="subbut" name="subbut" value="save"
			form="editClub">

	</div>

</body>
</html>