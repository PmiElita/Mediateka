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

<div class="section center" style="padding-top: 0">

	<form>
		<div class="row my-picture-row">
			<div class="col s12" style="background-color: #212121;">
				<div class="waves-effect waves-block waves-light my-picture-wrap">

					<a title="Change picture" href="" data-target="modal15"
						class="modal-trigger waves-effect" style="min-width: 100%">
						<h3 class="image-cover-t">${club.name}</h3> <img
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
						<c:if test="${isSigned == false}">
							<div class="col s6">
								<a class="btn" href="memberSignClub?clubId=${club.id}"
									style="width: 15em">Sign to club</a>
							</div>


						</c:if>
						<c:if test="${isSigned == true}">
							<c:choose>
								<c:when test="${!(creator==null)}">
									<c:if test="${club.state eq State.ACTIVE}">
										<div class="col s3">
											<a class="btn" href="creatorBlockClub?clubId=${club.id}"
												style="width: 15em">Block club</a>
										</div>
										<div class="col s3">
											<a class="btn" href="creatorDeleteClub?clubId=${club.id}"
												style="width: 15em">Delete club</a>
										</div>
									</c:if>
									<c:if test="${club.state eq State.BLOCKED}">
										<div class="col s3">
											<a class="btn" href="creatorUnblockClub?clubId=${club.id}"
												style="width: 15em">Unblock club</a>
										</div>
										<div class="col s3">
											<a class="btn" href="creatorDeleteClub?clubId=${club.id}"
												style="width: 15em">Delete club</a>
										</div>
									</c:if>
								</c:when>
								<c:otherwise>
									<div class="col s6">
										<a class="btn" href="memberSignClub?clubId=${club.id}"
											style="width: 15em">Sign out</a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>



						<div class="col s6">
							<a class="btn" href="ClubUsers?clubId=${club.id}"
								style="width: 15em">Look participants</a>
						</div>
					</c:if>
					<c:if test="${badGuy!=null}">
						<h5>
							<text align="center"> <c:out
								value="Your membership in this event is blocked or deleted!" /></text>
						</h5>
					</c:if>
				</c:when>
				<c:otherwise>
					<text align="center">
					<h4>
						<c:out value="Log in, if you want to see more!" />
					</h4>
					</text>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="row" style="margin-left: -5em">
			<div class="col s3">
				<a href="" data-target="modal7" class="modal-trigger">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-cardmy-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 class="center" style="color: black">Music</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/club/music.png" alt=""
											class="circle responsive-img" />
									</div>
									<div class="club-badge" style="margin-left: 0.4em">4</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>


			<div class="col s3">
				<a href="club_videos">
					<div
						class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 class="center" style="color: black">Video</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/club/video.jpg" alt=""
											class="circle responsive-img">
									</div>
									<div class="club-badge" style="margin-left: 0.4em">3</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>

			<div class="col s3">
				<a href="clubAlbums?clubId=${clubId}">



					<div
						class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 class="center" style="color: black">Photo</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/club/photo.png" alt=""
											class="circle responsive-img">
									</div>
									<div class="club-badge" style="margin-left: 0.4em">1</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>

			<div class="col s3">
				<a href="events?clubId=${club.id}">
					<div
						class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 class="center" style="color: black">Events</h3>
								<div class="row valign-wrapper">
									<div class="col s8">
										<img style="margin-top: 0em" src="images/club/events.png"
											alt="" class="circle responsive-img">
									</div>
									<div class="my-badge" style="margin-left: 0.75em">2</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<p>${club_description}</p>
	</div>
</div>