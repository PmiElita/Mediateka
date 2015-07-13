<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>
<%@page import="com.mediateka.model.enums.ClubEventMemberType"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />

<html>

<head>

<jsp:include page="../general/head.jsp" />
<c:if test="${memberType eq ClubEventMemberType.CREATOR }">
<jsp:include page="loadPhoto.jsp" />
</c:if>

</head>


<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
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

			<div class="container  white" style="width: 75%;">
				<div class="section">
				
					<div class="row">
						<h4 class="image-cover-t center">
							${albumName }
						</h4>
					</div>
					<c:if test="${memberType eq ClubEventMemberType.CREATOR }">
					<div class="row" style="margin-top: -5em;">
						<a title="Add Photo" href="" data-target="addPhoto"
							class="modal-trigger"> <span style="margin-left:5em"><i
								class="medium mdi-av-queue"></i></span>
						</a>
					</div>
					</c:if>
				</div>

				<div class="section">
					<div class="row" style="margin-left: -2em">
					<label id="index" hidden="true">${index}</label>
						<jsp:include page="photos.jsp" />
					</div>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>