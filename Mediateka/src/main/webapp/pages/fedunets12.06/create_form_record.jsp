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
<title>create_form_record</title>
</head>
<body>
	${message}
	<form id="create_form_record" action="CreateFormRecord" method="post">
		<fieldset>
		User id:<input type="number" name="userId" min="1" max="99999999999" required><br>
		Time till:<input type="time" name="timeTill" min="1" max="99999999999" required><br>
		Goal:<input type="radio" name="goal" value="book" checked>Book	<select size="6" name="book">
																	  		<option disabled>Book name</option>
																	  		<c:forEach var="item" items="${books}">
    																		<option value="${item.getId()}"><c:out value="${item.getName()}"/></option>
    																		</c:forEach>
   																	  	</select><br>
			 <input type="radio" name="goal" value="event">Event	<select size="6" name="event">
    																	<option disabled>Event name</option>
    																	<c:forEach var="item" items="${events}">
    																	<option value="${item.getId()}"><c:out value="${item.getName()}"/></option>
    																	</c:forEach>
   																	</select><br>
			 <input type="radio" name="goal" value="other">Other	<fieldset>
			 															<input type="radio" name="other" value="WI_FI" checked>Wi-fi
			 															<input type="radio" name="other" value="INTERNET">Internet<br>
			 														</fieldset>
		Comment:<textarea type="textarea" name="comment" placeholder="comment..." pattern=".{0,100}"></textarea><br>
   		<input type="submit" value="Create form record">
   		</fieldset>
	</form>
</body>
</html>