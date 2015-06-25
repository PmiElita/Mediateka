<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="../../WEB-INF/tld/showActivity.tld"%>
<!-- change path, remove one ../-->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/activity" var="msg" />

<!DOCTYPE html >

<script type="text/javascript" src="js/activity.js"></script>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />

<jsp:include page="../user/user_side_nav.jsp" />
<div class="container section white">
	<div class="main-activity">

		<div class="row select-activity">

			<select class="browser-default col s3 " id="period"
				onchange="reloadActivity(this)">
				<option value="week" selected>
					<fmt:message bundle="${msg}" key="week" />
				</option>
				<option value="month">
					<fmt:message bundle="${msg}" key="month" />
				</option>
				<option value="allTime">
					<fmt:message bundle="${msg}" key="allTime" />
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
</div>