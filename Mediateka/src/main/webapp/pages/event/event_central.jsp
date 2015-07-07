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

<head>
<jsp:include page="crop/crop.jsp" />

<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	z-index: 1000;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>
</head>



<form>
	<div class="row center">
		<div class="col s12" style="background-color: #212121;">
			<div
				class="waves-effect waves-block waves-light my-picture-wrap col s12">

				<a title="Change picture" href="" data-target="modal16"
					class="modal-trigger waves-effect" style="min-width: 100%">
					<h3 class="image-cover-t">${event.name}</h3> <img
					class="my-picture-club" src="${imagePath}" id="ava">
				</a>
			</div>
		</div>
	</div>
</form>

<div class="container white"
	style="height: 24em; margin-bottom: 0; margin-top: -1em">
	<div style="height: 3em"></div>
	<div class="sign-event row center">
	
	<c:choose>
	<c:when test="${userRole eq Role.USER}">
	<c:if test="${badGuy==null}">
		<c:if test="${isSigned eq 'false'}">
			<div class="col s6">
				<a class="btn" href="memberSignEvent?eventId=${event.id}" style="width: 15em">Sign to event</a>
			</div>
		</c:if>

		<c:if test="${isSigned eq 'true'}">
		<c:choose>
		<c:when test="${!(creator==null)}">
		<c:if test="${club.state eq State.ACTIVE}">
			<div class="col s3">
				<a class="btn" href="creatorBlockEvent?eventId=${event.id}" style="width: 15em">Block event</a>
			</div>
			<div class="col s3">
				<a class="btn" href="creatorUnblockEvent?eventId=${event.id}" style="width: 15em">Delete event</a>
			</div>
		</c:if>
		<c:if test="${club.state eq State.BLOCKED}">
			<div class="col s3">
				<a class="btn" href="creatorUnblockEvent?eventId=${event.id}" style="width: 15em">Unblock event</a>
			</div>
			<div class="col s3">
				<a class="btn" href="creatorDeleteEvent?eventId=${event.id}" style="width: 15em">Delete event</a>
			</div>
		</c:if>
		</c:when>
		<c:otherwise>
			<div class="col s6">
				<a class="btn" href="memberSignEvent?eventId=${event.id}" style="width: 15em">Sign out</a>
			</div>
		</c:otherwise>
		</c:choose>
		</c:if>

		<div class="col s6">
			<a class="btn" href="EventUsers?eventId=${event.id }" style="width: 15em">Look participants</a>
		</div>
	</c:if>
	<c:if test="${badGuy!=null}">
	<h5><text align="center"><c:out value="Your membership in this event is blocked or deleted!"/></text></h5>
	</c:if>
	</c:when>
	<c:otherwise><text align="center">
	<h4><c:out value="Log in, if you want to see more!"/></h4></text>
	</c:otherwise>
	</c:choose>
	
	</div>
	<div class="row" style="margin-left: -3.5em">
		<div class="col s4">
			<a href="" data-target="modal17" class="modal-trigger">
				<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
					<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
						<div style="margin-top: -2em">
							<h3 class="center" style="color: black">Music</h3>
							<div class="row valign-wrapper">
								<div class="col s9">
									<img src="images/club/music.png" alt=""
										class="circle responsive-img" />
								</div>
								<div class="club-badge" style="margin-left: 0.4em"><c:out value="${music}"/></div>
							</div>
						</div>
					</div>
				</div>
			</a>
		</div>

		<div class="col s4">
			<a href="event_videos">
				<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
					<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
						<div style="margin-top: -2em">
							<h3 class="center" style="color: black">Video</h3>
							<div class="row valign-wrapper">
								<div class="col s9">
									<img src="images/club/video.jpg" alt=""
										class="circle responsive-img">
								</div>
								<div class="club-badge" style="margin-left: 0.4em"><c:out value="${videos}"/></div>
							</div>
						</div>
					</div>
				</div>
			</a>
		</div>

		<div class="col s4">
			<a href="event_photo">
				<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
					<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
						<div style="margin-top: -2em">
							<h3 class="center" style="color: black">Photo</h3>
							<div class="row valign-wrapper">
								<div class="col s9">
									<img src="images/club/photo.png" alt=""
										class="circle responsive-img">
								</div>
								<div class="club-badge" style="margin-left: 0.4em"><c:out value="${albums}"/></div>
							</div>
						</div>
					</div>
				</div>
			</a>
		</div>
	</div>
</div>
