<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/book_page" var="msg" />
<%@page import="com.mediateka.model.enums.Role"%>
<%@page import="com.mediateka.model.enums.State"%>


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
	width: 100%;
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

			<div class="container white center"
				style="min-height: 33em; margin-bottom: 0">
				<div class="row">
					<c:if test="${userRole eq Role.ADMIN}">
						<div class="col s4">
							<a href="UpdateBook?bookId=${book.id }"><button class="btn">
									<fmt:message bundle="${msg }" key="edit" />
								</button></a>
						</div>
					</c:if>
					<div class="col s12 ">
						<h5 class="book-cover-t center">"${book.name}" ${book.author }</h5>
					</div>



				</div>
				<div class="row book-row">
					<img alt="Book name" src="${avaPath }" width="45%">
				</div>
				<div class="row book-row">
					<span><fmt:message bundle="${msg }" key="state"/>: </span>
					<c:choose>
						<c:when test="${book.state eq State.ACTIVE }">
							<span class="stateValue"><fmt:message bundle="${msg }"
									key="state.free" /></span>
						</c:when>
						<c:otherwise>
							<span class="stateValue"><fmt:message bundle="${msg }"
									key="state.not_free" /></span>
						</c:otherwise>
					</c:choose>

				</div>
				<div class="row">

					<div class="input-field col s4">
						<span><fmt:message bundle="${msg }" key="book_type" />: <fmt:message
								bundle="${msg }" key="book_type.${bookType }" /> </span>
					</div>

					<div class="input-field col s4">
						<span><fmt:message bundle="${msg }" key="book_meaning" />: <fmt:message
								bundle="${msg }" key="book_meaning.${bookMeaning }" /> </span>
					</div>


					<div class="input-field col s4">
						<span><fmt:message bundle="${msg }" key="book_language" />: <fmt:message
								bundle="${msg }" key="book_language.${bookLanguage }" /> </span>
					</div>
				</div>
				<div class="section" style="margin-bottom: 0">
					<h5><fmt:message bundle="${msg }" key="description"/></h5>
					<p>${book.description}</p>

				</div>
			</div>

		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>