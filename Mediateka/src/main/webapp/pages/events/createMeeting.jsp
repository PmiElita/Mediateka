<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.log4j.Logger"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<script src= "js/eventCreation.js"></script>
<title>create_meeting</title>
</head>
<body>
	${message}
	<form name="createMeeting" id="create_meeting" action="CreateMeeting" method="post">
		Name:<input type="text" id="name" name="name" placeholder="meeting name..." required pattern=".{1,45}"><br>
		<p id="wrongDate"></p>
		<p id="wrongTime"></p>
		<label for="date" id="labelDate">Date:</label><input type="date" name="date" id="date" required onchange="meeting()"> 
		<label for="timeFrom" id="labelTimeFrom">Time from:</lable><input type="time" name="timeFrom" id="timeFrom" required onchange="timeChangeMeeting()"> 
		<label for="timeTill" id="labelDateTill">Time till:</lable><input type="time" name="timeTill" id="timeTill" required onchange="timeChangeMeeting()"><br>
		Description:<textarea type="textarea" name="description" placeholder="meeting description..." pattern=".{0,255}"></textarea><br>
		<input type="submit" id="submit" value="Create meeting">
	</form>	
</body>
</html>