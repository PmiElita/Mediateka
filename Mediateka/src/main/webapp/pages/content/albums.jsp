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
<jsp:include page="loadAlbum.jsp" />
</c:if>
<!-- fotorama.css & fotorama.js. -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
<!-- 3 KB -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
<!-- 16 KB -->
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
					<h3 class="image-cover-t center" style="margin-top: 0.5em;">${clubName}</h3>
					<div class="row">
						<h4 class="image-cover-t center">
							<fmt:message bundle="${msg}" key="albums" />
						</h4>
						
					</div>
					<c:if test="${memberType eq ClubEventMemberType.CREATOR }">
					<div class="row" style="margin-top: -5em;">
						<a title='<fmt:message bundle="${msg }" key="albums.add_album"/>' href="" data-target="addAlbum"
							class="modal-trigger"> <span style="margin-left:5em"><i
								class="medium mdi-av-queue"></i></span>
						</a>
					</div>
					</c:if>
				</div>

				<div class="section">
					<div class="row" style="margin-left: -2em">
						<jsp:include page="albumList.jsp" />
					</div>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>