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
<jsp:include page="../form/new_event_form.jsp" />
<jsp:include page="../form/message_form.jsp" />
</head>

<body>
	<%
		Logger LOG = Logger.getLogger(this.getClass().getName());
	%>
	<%
		LOG.warn("There's a new man in Town!");
	%>

	<jsp:include page="../general/nav.jsp" />
	<jsp:include page="user_side_nav.jsp"/>
	<jsp:include page="user_central.jsp" />
	<jsp:include page="../general/footer.jsp" />
	<jsp:include page="../general/script.jsp" />
</body>
</html>