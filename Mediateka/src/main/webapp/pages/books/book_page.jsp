<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.mediateka.model.enums.Role"%>
<%@page import="com.mediateka.model.enums.State"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>

<jsp:include page="../general/head.jsp" />
<jsp:include page="../index/index_modal_login.jsp" />
<jsp:include page="../index/index_modal_register.jsp" />
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
</head>

<style>
.book-cover-t {
	color: white;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
		width : 100%;
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

			<div class="container white center" style="min-height:33em; margin-bottom:0">
				<div class="row">
			  <c:if test="${userRole eq Role.ADMIN}">
			  <div class = "col s4"><a href ="UpdateBook?bookId=${book.id }"><button class="btn">Edit</button></a> </div>
			  </c:if>
					<div class="col s12 ">
						<h5 class="book-cover-t center">"${book.name}" ${book.author }</h5>
					</div>

					

				</div>
            <div class="row book-row">
				<img alt="Book name" src="${avaPath }" width="45%"
					>
            </div>
            <div class = "row book-row">
            <span>State: </span>
            <c:choose>
            <c:when test="${book.state eq State.ACTIVE }">
            <span class="stateValue">Free</span>
            </c:when>
            <c:otherwise>
            <span class="stateValue">Not free</span>
            </c:otherwise>
            </c:choose>
            
            </div>
            <div class="row">

								<div class="input-field col s4">
									<span>Type: ${bookType }</span>
								</div>

								<div class="input-field col s4">
									<span>Meaning: ${bookMeaning }</span>
								</div>


								<div class="input-field col s4">
									<span>Meaning: ${bookLanguage }</span>
								</div>
							</div>
				<div class="section" style="margin-bottom:0">
					<h5>Info</h5>
					<p>${book.description}</p>

				</div>
			</div>

		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>