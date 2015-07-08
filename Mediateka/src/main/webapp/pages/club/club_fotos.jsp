<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />

<html>

<head>
<jsp:include page="../general/head.jsp" />
<jsp:include page="add_video.jsp" />
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

			<div class="section">
				<div class="container">
					<h3 class="image-cover-t">
						<fmt:message bundle="${msg}" key="club_photos.club_name" />
					</h3>
					<h4 class="image-cover-t"><fmt:message bundle="${msg}" key="club_photos.videos" /></h4>
					<div class="row" style="margin-left: -7em">
						<div class="col s3">
							<a title="Add video" href="" data-target="modal9"
								class="modal-trigger">
								<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
									<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
										<div style="margin-top: 2em">
											<div class="row valign-wrapper">
												<div class="col s9">
													<img src="images/club/add_video.png" alt=""
														class="responsive-img" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>