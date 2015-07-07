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
<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	z-index: 1000;
	text-align: center;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>
</head>


<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<div class="container section white">

				<c:choose>
					<c:when test="${userRole eq Role.USER}">
						<div class="row" style="margin-top: -1em">
							<div class="col s12">
								<ul class="tabs">
									<li class="tab col s4"><a href="#my_clubs"
										style="margin-left: 5em;">My clubs</a></li>
									<li class="tab col s4"><a href="#all_clubs">All clubs</a></li>

								</ul>
							</div>
							<jsp:include page="../user/user_side_nav.jsp" />

							<div id="my_clubs" style="min-height:31em"><jsp:include page="my_clubs.jsp" /></div>
							<div id="all_clubs" style="min-height:31em"><jsp:include page="all_clubs.jsp" /></div>
						</div>
					</c:when>
					<c:otherwise>
						<div id="all_clubs" style="min-height:31em"><jsp:include page="all_clubs.jsp" /></div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>