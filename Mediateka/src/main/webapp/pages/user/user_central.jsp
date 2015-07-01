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
<jsp:include page="crop/crop.jsp"/>

<div class="parallax-container my-parallax">
	<div class="parallax">
		<img src="images/parallax1.jpg">
	</div>

	<jsp:include page="user_side_nav.jsp" />

	<div class="container white section">

		<div class="row section" style="margin-top: 2em">
			<div class="main-activity col s3" style="height: 20em">
				<div class="row select-activity">

					<select class="browser-default col s12 " id="period"
						onchange="reloadActivity(this)">
						<option value="week" selected>
							<fmt:message bundle="${msg1}" key="week" />
						</option>
						<option value="month">
							<fmt:message bundle="${msg1}" key="month" />
						</option>
						<option value="allTime">
							<fmt:message bundle="${msg1}" key="allTime" />
						</option>
					</select>
				</div>
				<div class="row" id="formRecordsRow">
					<div class="col s12 " id="formRecords">
						<u:showUsers formRecords="${formRecords }"
							locale="${cookie.lang.value}" />
					</div>
				</div>
			</div>

			<div class="user-info col s9">
				<div class="row">
					<div>
						<a  title="Change avatar" href="" data-target="modal21"
							class="modal-trigger waves-effect col s4"><img
							src="images/user.png" id="avatar" style="border-radius:50%;" class="col s12"/></a>
					</div>
					<div class="col s8">
						<h5>${firstName}${middleName}${lastName}</h5>
					</div>
					<div class="row" style="font-size: 1.25em">
						<div class="col s6">Birth ${birthDate}</div>
						<div class="col s6">${nationality}</div>
					</div>
					<div class="row" style="font-size: 1.25em">
						<div class="col s4">${profession}</div>
						<div class="col s4">${education}</div>
						<div class="col s4">${eduInstitution}</div>
					</div>
					<div class="row" style="font-size: 1.25em">
						<div class="col s6">${address}</div>
						<div class="col s6">${phone}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>