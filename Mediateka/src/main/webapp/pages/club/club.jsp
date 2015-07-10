<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.Role"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />


<html>

<head>
<jsp:include page="../index/index_modal_login.jsp" />
<jsp:include page="../index/index_modal_register.jsp" />
<jsp:include page="../general/head.jsp" />
<script src="js/comment.js"></script>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
</head>

<body onload="connect();" onunload="disconnect();">

	<jsp:include page="../general/nav.jsp" />
	<div class="main">
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

			<jsp:include page="club_central.jsp" />
			<div class="container white">
				<div style="height: 3em"></div>
				<label id="clubId" hidden="true"><c:out value="${club.id}" /></label>
				<p>
					<fmt:message bundle="${msg}" key="description" />
					<c:out value="${club.description}" />
				</p>
				<br>
				<ul class="collapsible center" data-collapsible="accordion">
					<li><jsp:include page="../content/record.jsp" /></li>
				</ul>
			</div>

			<c:if test="${isSigned eq 'true'|| userRole eq Role.ADMIN}">
				<jsp:include page="chat_side_nav.jsp" />
			</c:if>

		</div>
		<input type="text" hidden="true" value="${userId}" id="userId" />
	</div>



	<jsp:include page="../general/footer.jsp" />

</body>
</html>