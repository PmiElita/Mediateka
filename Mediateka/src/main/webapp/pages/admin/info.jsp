<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="org.apache.log4j.Logger"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>

<jsp:include page="../general/head.jsp" />
<jsp:include page="../form/register_user_form.jsp" />
<jsp:include page="../form/new_event_form.jsp" />
<jsp:include page="../form/message_form.jsp" />
<jsp:include page="../table/user_table.jsp" />
</head>

<body>
	<%
		Logger LOG = Logger.getLogger(this.getClass().getName());
	%>
	<%
		LOG.warn("There's a new man in Town!");
	%>

	<jsp:include page="../general/nav.jsp" />
	<jsp:include page="admin_side_nav.jsp" />

	<div class="main-info">
		<div class="section white">
			<div class="container">
				<textarea id="infoText" name="infoText" class="materialize-textarea info">		Перша львівська медіатека – це проект громадського центру нового типу, діє при Центральній бібліотеці для дорослих ім. Лесі	Українки. Медіатека – інтерактивний громадський центр, який
пропонує можливість участі у заходах та використання різноманітних ресурсів, включаючи Інтернет, CD та DVD-носії, електронні книги,
інтерактивні програми вивчення іноземних мов і комп’ютерних технологій, програми дитячого розвитку, комп’ютерні курси, музичну
студію, кінозал, дискусійні клуби, тренінги і гуртки для усіх. Приміщення доступне для людей з особливими потребами. На додачу до послуг традиційної бібліотеки громада міста може насолоджуватись сучасним комфортним дизайном центру та значно
ширшим спектром послуг.
Медіатека складається вона з трьох функціональних приміщень та студії звукозапису. У першому, найбільшому – комп’ютери, друге приміщення – кімната для юнацтва, третє приміщення – кінозал та конференц-зал.
</textarea>
			</div>
		</div>
	</div>


	<jsp:include page="../general/footer.jsp" />
	<jsp:include page="../general/script.jsp" />
</body>
</html>