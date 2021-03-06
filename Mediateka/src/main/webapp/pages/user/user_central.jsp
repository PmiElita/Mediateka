<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="../../WEB-INF/tld/showActivity.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/activity" var="msg1" />
<fmt:setBundle basename="translations/user_modification_form" var="msg" />

<script type="text/javascript" src="js/activity.js"></script>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
<jsp:include page="crop/crop.jsp" />

<div class="parallax-container my-parallax">
	<div class="parallax">
		<img src="images/parallax1.jpg">
	</div>

	<jsp:include page="user_side_nav.jsp" />

	<div class="container white section" style="width: 80%">
		<div class="row section" style="margin-top: 3em">
			<div class="main-activity col s5" style="min-height: 20em">
				<div class="row select-activityr">
					<h5>
						<fmt:message bundle="${msg }" key="form" />
					</h5>
				</div>
				<div class="row" id="formRecordsRow">
					<div class="col s12 " id="formRecords">
						<u:showUsers formRecords="${formRecords }"
							locale="${cookie.lang.value}" />
					</div>
				</div>
			</div>

			<div class="user-info col s7">
				<div class="row">
					<div>
						<a title="Change avatar" href="" data-target="modal21"
							class="modal-trigger waves-effect col s4"><img
							src="${imagePath }" id="avatar" style="border-radius: 50%;"
							class="col s12" /></a>
					</div>
					<div class="col s8">
						<h5 class="left">${firstName} ${middleName} ${lastName}</h5>
					</div>
					<div class="row" style="font-size: 1.15em;">
						<div class="col s3 center" style="margin-top: 1em">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="birth_date" />
							</p>
							<p>${birthDate}</p>
						</div>
						<div class="col s4 center" style="margin-top: 1em">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="nationality" />
							</p>
							<p>${nationality}</p>
						</div>
					</div>
					<div class="row" style="font-size: 1.1em; margin-top: 3em">
						<div class="col s4">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="profession" />
							</p>
							<p>
								<fmt:message bundle="${msg }" key="profession.${profession}" />
							</p>
						</div>
						<div class="col s4">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="education" />
							</p>
							<p>
								<c:choose>
									<c:when test="${education == 'PRIMARY'}">
										<fmt:message bundle="${msg}" key="education.primary" />
									</c:when>
									<c:when test="${education == 'BACHELOR'}">
										<fmt:message bundle="${msg}" key="education.bachelor" />
									</c:when>
									<c:when test="${education == 'MASTER'}">
										<fmt:message bundle="${msg}" key="education.master" />
									</c:when>
									<c:when test="${education == 'DOCTORAL'}">
										<fmt:message bundle="${msg}" key="education.doctoral" />
									</c:when>
								</c:choose>

							</p>
						</div>
						<div class="col s4">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="institution" />
							</p>
							<p>${eduInstitution}</p>
						</div>
					</div>
					<div class="row" style="font-size: 1.15em; margin-top: 3em">
						<div class="col s8">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="address" />
							</p>
							<p>${address}</p>
						</div>
						<div class="col s4">
							<p style="color: grey; font-size: 0.9em">
								<fmt:message bundle="${msg }" key="phone" />
							</p>
							<p>${phone}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>