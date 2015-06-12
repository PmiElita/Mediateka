<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.log4j.Logger"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create_book</title>
</head>
<body>
	${message}
	<form id="create_event" action="CreateEvent" method="post">
		Book name:<input type="text" name="name" placeholder="book name..." pattern=".{1,45}"><br>
		Book author from:<input type="text" name="author" placeholder="book author..." pattern=".{1,45}"><br>
		<p><select size="6" multiple name="type">
    		<option disabled>Выберите героя</option>
    		<option value="Чебурашка">Чебурашка</option>
   		</select></p>
   		<p><select size="6" multiple name="meaning">
    		<option disabled>Выберите героя</option>
    		<option value="Чебурашка">Чебурашка</option>
   		</select></p>
   		<p><select size="6" multiple name="language">
    		<option disabled>Выберите героя</option>
    		<option value="Чебурашка">Чебурашка</option>
   		</select></p>
	</form>
</body>
</html>