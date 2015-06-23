<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.mediateka.model.enums.State"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<script src= "js/eventCreation.js"></script>
<title>update_meeting</title>
</head>
<body>
	${message}
	<form name="updateMeeting" id="update_meeting" action="UpdateMeeting" method="post">
		Name:<input type="text" id="name" name="name" placeholder="meeting name..." required pattern=".{1,45}" value="${event.getName()}"><br>
		<p id="wrongDate"></p>
		<p id="wrongTime"></p>
		<label for="date" id="labelDate">Date:</label><input type="date" name="date" id="date" required onchange="meeting()" value="${dateFrom}"> 
		<label for="timeFrom" id="labelTimeFrom">Time from:</lable><input type="time" name="timeFrom" id="timeFrom" required onchange="timeChangeMeeting()" value="${timeFrom}"> 
		<label for="timeTill" id="labelDateTill">Time till:</lable><input type="time" name="timeTill" id="timeTill" required onchange="timeChangeMeeting()" value="${timeTill}"><br>
		Description:<textarea type="textarea" name="description" placeholder="meeting description..." pattern=".{0,255}"><c:out value="${event.getDescription()}"/></textarea><br>
		Meeting avatar:<input type="file" name="image" id="image" placeholder="book cover screenshot..." value="${event.getAvaId()}" onchange="readURL(this);"><img id="photo" src="${imagePath}"><br>
		State:<c:if test="${event.state eq State.ACTIVE}"><input type="radio" name="state" value="avtive" checked>Active <input type="radio" name="state" value="blocked">Blocked</c:if>
			  <c:if test="${event.state eq State.BLOCKED}"><input type="radio" name="state" value="avtive">Active <input type="radio" name="state" value="blocked" checked>Blocked</c:if><br>
		<input type="submit" id="submit" value="Create meeting">
	</form>	
</body>
</html>