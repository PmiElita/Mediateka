<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@page import="com.mediateka.model.enums.EventType"%>
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
<script src= "js/imageView.js"></script>
<script src= "js/eventCreation.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<title>update_event</title>
</head>
<body>
	${message}
	<form name="updateEvent" id="update_event" action="UpdateEvent" method="post" enctype="multipart/form-data">
		Event name:<input type="text" name="name" placeholder="event name..." pattern=".{1,45}" value="${event.getName()}"><br>
		<c:choose>
		<c:when test="${event.type eq EventType.MEETING}">
		Event type:<input type="radio" name="type" value="meeting" checked>meeting<input type="radio" name="type" value="exhibition">exhibition<br>
		</c:when>
		<c:otherwise>
		Event type:<input type="radio" name="type" value="meeting">meeting<input type="radio" name="type" value="exhibition" checked>exhibition<br>
		</c:otherwise>
		</c:choose>
		<p id="wrongDate"></p>
		Event date from:<input type="date" name="dateFrom" id="dateFrom" required onchange="dateChange()" value="${dateFrom}"><br>
		Event date till:<input type="date" name="dateTill" id="dateTill" required onchange="dateChange()" value="${dateTill}"><br>
		Event description:<textarea type="textarea" name="description" placeholder="event description..." pattern=".{1,255}"><c:out value="${event.getDescription()}"/></textarea><br>
		Event avatar:<input type="file" name="image" placeholder="event avatar..." value="#" onchange="readURL(this);"><img id="photo" src="${imagePath}"><br>
		<c:choose>
		<c:when test="${event.state eq State.ACTIVE}">
		Event state:<input type="radio" name="state" value="active" checked>active <input type="radio" name="state" value="blocked">blocked<br>
		</c:when>
		<c:otherwise>
		Event state:<input type="radio" name="state" value="active">active <input type="radio" name="state" value="blocked" checked>blocked<br>
		</c:otherwise>
		</c:choose>
		<input type="submit" id="submit" value="Update event">
	</form>	
</body>
</html>