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
<title>create_event</title>
</head>
<body>
	${message}
	<form id="create_event" action="CreateEvent" method="post">
		Event name:<input type="text" name="name" placeholder="event name..." pattern=".{1,45}"><br>
		Event date from:<input type="date" name="date_from" required><br>
		Event date till:<input type="date" name="date_till" required><br>
		Event type:<input type="radio" name="type" value="meeting" checked>meeting<input type="radio" name="type" value="exhibition">exhibition<br>
		Event description:<textarea type="textarea" name="description" placeholder="event description..." pattern=".{1,255}"></textarea><br>
		<input type="submit" value="Create event">
	</form>
</body>
</html>