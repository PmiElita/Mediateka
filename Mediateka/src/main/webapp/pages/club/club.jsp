<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.mediateka.model.enums.Role"%>


<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>

<jsp:include page="../general/head.jsp" />
<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/viewImage.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>


</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<c:if test="${userRole eq Role.ADMIN}">
			<jsp:include page="../admin/admin_side_nav.jsp" />
		</c:if>

		<c:if test="${userRole eq Role.USER}">
			<jsp:include page="../user/user_side_nav.jsp" />
		</c:if>

		<jsp:include page="club_central.jsp" />
		
		<jsp:include page="record.jsp"/>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>