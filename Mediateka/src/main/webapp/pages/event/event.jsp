<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/event_page" var="msg" />

<html>

<head>
<jsp:include page="../general/head.jsp" />
<jsp:include page="../index/index_modal_login.jsp" />
<jsp:include page="../index/index_modal_register.jsp" />
<jsp:include page="../../js/record.js.jsp" />
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
</head>

<body>
	<script type="text/javascript" src="js/eventComment.js"></script>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />



		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<c:if test="${userRole eq Role.ADMIN}">
				<jsp:include page="../admin/admin_side_nav.jsp" />
			</c:if>

			<c:if test="${userRole eq Role.USER}">
				<jsp:include page="../user/user_side_nav.jsp" />
			</c:if>

			<jsp:include page="event_central.jsp" />
			<label id="eventId" hidden="true"><c:out value="${event.id}" /></label>
			<div class="container white">
				<div style="height: 3em"></div>
				<p>
					<fmt:message bundle="${msg}" key="description" />
					<c:out value="${event.description}" />
				</p>
				<br>
				<ul class="collapsible center" data-collapsible="accordion">
					<li><jsp:include page="../content/record.jsp" /></li>
				</ul>
			</div>
		</div>
		<input type="text" hidden="true" value="${userId}" id="userId" />
	</div>
	<label hidden id="currentUserId">${userId }</label>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>