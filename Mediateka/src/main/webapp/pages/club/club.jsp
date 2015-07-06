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
<jsp:include page="../index/index_modal_login.jsp" />
<jsp:include page="../index/index_modal_register.jsp" />
<jsp:include page="../general/head.jsp" />

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
			<div style="height:3em"></div>
				<label id="clubId" hidden="true"><c:out value="${club.id}" /></label>
					<p>Description:
					<c:out value="${club.description}" /></p>
					<br>
				<ul class="collapsible center" data-collapsible="accordion">
					<li><jsp:include page="record.jsp" /></li>
				</ul>
			</div>
		</div>
	            <input type="text" hidden="true" value="${userId}" id ="userId"/>
           
	</div>
	<c:if test="${!(isSigned==null)}">
	<jsp:include page="chat_side_nav.jsp"/>
	</c:if>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>