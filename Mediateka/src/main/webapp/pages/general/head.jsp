<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/general" var="msg" />

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><fmt:message bundle="${msg }" key="head.title" /></title>

<link type="text/css" rel="stylesheet" href="css/materialize.css"
	media="screen,projection" />

<link type="text/css" rel="stylesheet" href="css/my_style.css"
	media="screen,projection" />

<link type="text/css" rel="stylesheet" href="css/side_nav_style.css"
	media="screen,projection" />

<link type="text/css" rel="stylesheet" href="css/autocomplete.css"
	media="screen,projection" />

<link rel="stylesheet" type="text/css" href="css/DateTimePicker.css" />

<link rel="shortcut icon" type="image/png" href="images/fav_icon.png" />

<jsp:include page="../general/script.jsp" />