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
<title>create_exhibition</title>
</head>
<body>
	${message}
	<form name="createExhibition" id="create_exhibition" action="CreateExhibition" method="post">
		Name:<input type="text" id="name" name="name" placeholder="exhibition name..." required pattern=".{1,45}"><br>
		<p id="wrongDate"></p>
		<label for="dateFrom" id="labelDateFrom">Date from:</label><input type="date" name="dateFrom" id="dateFrom" required onchange="dateChangeExhibition()"> 
		<label for="dateTill" id="labelDateTill">Date till:</lable><input type="date" name="dateTill" id="dateTill" required onchange="dateChangeExhibition()"><br>
		Description:<textarea type="textarea" name="description" placeholder="exhibition description..." pattern=".{0,255}"></textarea><br>
		<input type="submit" id="submit" value="Create exhibition">
	</form>	
</body>
</html>