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

	<c:if test="${userRole eq 'admin'}">
		<jsp:include page="../admin/admin_side_nav.jsp" />
	</c:if>

	<c:if test="${userRole eq 'user'}">
		<jsp:include page="../user/user_side_nav.jsp" />
	</c:if>

	<jsp:include page="club_central.jsp" />
	<jsp:include page="../general/footer.jsp" />
</body>
</html>