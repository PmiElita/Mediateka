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

</head>

<body>

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
			<label id="clubId" hidden="true"><c:out value="${club.id}"/></label>
			Description: <c:out value="${club.description}"/><br>
			<div class="container">
				<ul class="collapsible center" data-collapsible="accordion"
					style="margin-top: 5%">
					<li>
						<div class="collapsible-header" style="font-size: 2em">Add
							media</div>
						<div class="collapsible-body">
							<jsp:include page="record.jsp" />
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>