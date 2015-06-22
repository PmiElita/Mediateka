<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src= "js/eventCreation.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/date.js"></script>
<jsp:include page="../general/head.jsp" />
</head>

<jsp:include page="../general/nav.jsp" />
<jsp:include page="../user/user_side_nav.jsp" />


<div id="creation_form">

	<div class="container">
		<form name="createEvent" id="create_event" action="CreateEvent"
			method="post">

			<button type="submit" class="btn waves-effect blue titler"
				style="margin-top: 2.5em">Create event</button>

			<h6 style="color: blue">${message}</h6>

			<div class="row" style="margin-top: 0em">
				<div class="col s12">
					<div class="row" style="margin-bottom: 0em">

						<div class="input-field col s6">
							<p>Event name</p>
							<input id="name" name="name" type="text" class="validate"
								required pattern=".{1,45}">
						</div>


						<div class="input-field col s6" style="margin-top:6em">
							Event type:<input type="radio" name="type" value="MEETING"
								onclick="handleClick(this);">meeting <input
								type="radio" name="type" value="EXHIBITION"
								onclick="handleClick(this);" checked>exhibition<br>
						</div>
					</div>

					<div class="row" style="margin-bottom: 0em">
						<div class="row">
						<p id="wrongDate"></p>
						</div>
						<div class="row">
						<div class="input-field col s6">
							<p id="labelDateFrom">Date from:</p>
							<p></p>
							<input class="datepicker" name="dateFrom" id="dateFrom" required onchange="dateChange()">
						</div>
						
						<div class="input-field col s6">
							<p id="labelTimeFrom" style="visibility:hidden;">Time from:</p>
							<input class="timepicker" name="timeFrom" id="timeFrom" required onchange="dateChange()" style="visibility:hidden;">
						</div>
						</div>
						
						<div class="row">
						<div class="delete-add" id="delete-add">
						<div class="input-field col s6">
							<p id="labelDateTill" style="visibility:visible;">Date till:</p>
							<input class="datepicker" name="dateTill" id="dateTill" onchange="dateChange()" style="visibility:visible;">
						</div>
						</div>
						
						<div class="input-field col s6">
							<p id="labelTimeTill" style="visibility:hidden;">Time till:</p>
							<input class="timepicker" name="timeTill" id="timeTill" required onchange="dateChange()" style="visibility:hidden;">
						</div>
						</div>
					</div>

					<div class="row">
						<div class="input-field col s12">
							<p>Description</p>
							<textarea id="description" name="description"
								class="materialize-textarea" length="100"></textarea>
						</div>
					</div>

				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="../general/footer.jsp" />