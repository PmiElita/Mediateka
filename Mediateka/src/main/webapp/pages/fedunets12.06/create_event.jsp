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
<script src= "js/eventCreation.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<title>create_event</title>
</head>
<body>
	${message}
	<form name="createEvent" id="create_event" action="CreateEvent" method="post">
		Event name:<input type="text" id="name" name="name" placeholder="event name..." required pattern=".{1,45}"><br>
		Event type:<input type="radio" name="type" value="MEETING" onclick="handleClick(this);" checked>meeting
				   <input type="radio" name="type" value="EXHIBITION" onclick="handleClick(this);">exhibition<br>
		<p id="wrongDate"></p>
		Event date from:<input type="date" name="dateFrom" id="dateFrom" required onchange="dateChange()"><br>
		Event date till:<input type="date" name="dateTill" id="dateTill" required onchange="dateChange()"><br>
		Event description:<textarea type="textarea" name="description" placeholder="event description..." pattern=".{1,255}"></textarea><br>
		<input type="submit" id="submit" value="Create event">
	</form>	
</body>
</html>