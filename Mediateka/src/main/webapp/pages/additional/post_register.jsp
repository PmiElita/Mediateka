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
</head>

<body>
	<%
		Logger LOG = Logger.getLogger(this.getClass().getName());
	%>
	<%
		LOG.warn("There's a new man in Town!");
	%>


	<jsp:include page="../general/nav.jsp" />

	<div style="margin-top:10em; margin-right:0em" class="container offset-s4">
		<div class="row">
			<h1 class="col8 ">Thanks!</h1>
		</div>
		<div class="row">
			<p style="font-size:1.5em" class="col8">Check out your e-mail to confirm your registration.</p>
		</div>
	</div>
	<div style="min-height:195px">
	</div>
	<jsp:include page="../general/footer.jsp" />

	<jsp:include page="../general/script.jsp" />
</body>
</html>