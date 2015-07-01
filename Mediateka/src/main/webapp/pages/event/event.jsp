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
<jsp:include page="../index/index_modal_login.jsp" />
<jsp:include page="../index/index_modal_register.jsp" />
</head>

<body>
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
			</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>