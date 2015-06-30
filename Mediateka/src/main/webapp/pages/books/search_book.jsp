<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mediateka.model.enums.Role"%>
<!DOCTYPE html >
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
			<div id="search_book_form"><jsp:include
					page="search_book_form.jsp" /></div>
			<div id="search_book_central"
				class="row main-activity section white container"><jsp:include
					page="search_book_central.jsp" /></div>
		</div>
	</div>
	<div style="margin-bottom: 1em"></div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>