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

<style>
.book-cover-t {
	color: white;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}

.book-cover-href {
	color: blue;
	text-shadow: white 1.0px 0.0px, white 1.0px 1.0px, white 0.0px 1.0px,
		white -1.0px 1.0px, white -1.0px 0.0px, white -1.0px -1.0px, white
		0.0px -1.0px, white 1.0px -1.0px, white 0.0 0.0 3.0px, white 0.0 0.0
		3.0px, white 0.0 0.0 3.0px, white 0.0 0.0 3.0px, white 0.0 0.0 3.0px,
		white 0.0 0.0 3.0px, white 0.0 0.0 3.0px, white 0.0 0.0 3.0px;
}
</style>

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

			<div class="container center">
				<div class="row" style="margin-left: -5em">
					<div class="col s4 offset-s3">
						<h2 class="book-cover-t right">Book title</h2>
					</div>

					<div class="col s1 left">
						<div style="margin-top:1.75em" class="fixed-action-btn">
							<a href="searchBook" class="btn-floating btn-medium green">+ </a>
							<ul style="font-size:0.75em">
								<li><a class="btn-floating btn-large blue book-cover-href" style="color:black">Search</a></li>

									<li><a href="UpdateBook"
										class="btn-floating yellow darken-1 book-cover-href" style="color:black">Edit</a></li>

							</ul>
						</div>
					</div>



				</div>
				<img alt="Book name" src="images/book_title.jpg" width="45%" style="margin-top:-7em">

				<div class="container section white">
					<h5>Info</h5>
					<p>It is a wonderful story of adventurous life by J. C.
						Kalloun.</p>

				</div>
			</div>

		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>