<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.Role"%>
<%@page import="com.mediateka.model.enums.State"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />


<head>
<jsp:include page="crop/crop.jsp" />
<jsp:include page="../club/club_create_event.jsp" />

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
<jsp:include page="send_email_to_members_form.jsp" />
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
									style="width: 15em"><fmt:message bundle="${msg}"
										key="sign_to_club" /></a>
							</div>
							<div class="col s6">
								<a class="btn" href="ClubUsers?clubId=${club.id}"
									style="width: 15em"><fmt:message bundle="${msg}"
										key="look_participants" /></a>
							</div>


						</c:if>
						<c:if test="${isSigned == true}">
							<c:choose>
								<c:when test="${!(creator==null)}">
									<c:if test="${club.state eq State.ACTIVE}">
										<div class="col s4" id="1">
											<a class="btn" href="creatorBlockClub?clubId=${club.id}"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="block_club" /></a>
										</div>
										<div class="col s4" id="2">
											<a class="btn" onclick="deleteClubAjax(${club.id})"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="delete_club" /></a>
										</div>
										<div class="col s12" id="3" hidden>
											<a class="btn" onclick="restoreClubAjax(${club.id})"
												style="width: 30em; color: red"><fmt:message
													bundle="${msg}" key="restore_club" /></a>
										</div>
										<div class="col s4" id="4">
											<a class="btn" href="editClub?clubId=${club.id}"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="edit_club" /></a>
										</div>
										<br>
										<div class="col s4" id="5">
											<a class="btn" href="ClubUsers?clubId=${club.id}"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="look_participants" /></a>
										</div>
										<div class="col s4" id="6">
											<a href="" data-target="modal18" class="btn modal-trigger"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="create_event" /></a>
										</div>
										<div class="col s4" id="7">
											<a href="" data-target="modal_send_email_to_club_members"
												class="modal-trigger btn"> <fmt:message bundle="${msg}"
													key="send_email_to_club_members" />
											</a>
										</div>
									</c:if>
									<c:if test="${club.state eq State.BLOCKED}">
										<div class="col s4" id="1">
											<a class="btn" href="creatorUnblockClub?clubId=${club.id}"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="unblock_club" /></a>
										</div>
										<div class="col s4" id="2">
											<a class="btn" onclick="deleteClubAjax(${club.id})"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="delete_club" /></a>
										</div>
										<div class="col s12" id="3">
											<a class="btn" onclick="restoreClubAjax(${club.id})"
												style="width: 30em; color: red"><fmt:message
													bundle="${msg}" key="restore_club" /></a>
										</div>
										<div class="col s4" id="4">
											<a class="btn" href="editClub?clubId=${club.id}"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="edit_club" /></a>
										</div>
										<br>
										<div class="col s4" id="5">
											<a class="btn" href="ClubUsers?clubId=${club.id}"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="look_participants" /></a>
										</div>
										<div class="col s4" id="6">
											<a href="" data-target="modal18" class="btn modal-trigger"
												style="width: 20em"><fmt:message bundle="${msg}"
													key="create_event" /></a>
											<div class="col s4" id="7">
												<a href="" data-target="modal_send_email_to_club_members"
													class="modal-trigger btn"> <fmt:message bundle="${msg}"
														key="send_email_to_club_members" />
												</a>
											</div>
										</div>
									</c:if>
								</c:when>
								<c:otherwise>
									<div class="col s6">
										<a class="btn" href="memberSignClub?clubId=${club.id}"
											style="width: 15em"><fmt:message bundle="${msg}"
												key="sign_out" /></a>
									</div>
									<div class="col s6">
										<a class="btn" href="ClubUsers?clubId=${club.id}"
											style="width: 15em"><fmt:message bundle="${msg}"
												key="look_participants" /></a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:if>
					<c:if test="${badGuy!=null}">
						<h5>
							<text align="center"> <fmt:message bundle="${msg}"
								key="your_membership_in_this_event_is_blocked_or_deleted" /> </text>
						</h5>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${!(userRole eq Role.ADMIN)}">
						<text align="center">
						<h4>
							<fmt:message bundle="${msg}" key="login_to_see_more" />
						</h4>
						</text>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="row" style="margin-left: -5em">
			<div class="col s3">
				<a href="clubAudios?clubId=${clubId}" data-target="modal7"
					class="modal-trigger">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-cardmy-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 class="center" style="color: black">
									<fmt:message bundle="${msg}" key="music" />
								</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/club/music.png" alt=""
											class="circle responsive-img" />
									</div>
									<div class="club-badge" style="margin-left: 0.4em">
										<c:out value="${music}" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>


			<div class="col s3">
				<a href="clubVideos?clubId=${clubId}">
					<div
						class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 class="center" style="color: black">
									<fmt:message bundle="${msg}" key="video" />
								</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/club/video.jpg" alt=""
											class="circle responsive-img">
									</div>
									<div class="club-badge" style="margin-left: 0.4em">
										<c:out value="${videos}" />
									</div>
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
								<h3 class="center" style="color: black">
									<fmt:message bundle="${msg}" key="photo" />
								</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/club/photo.png" alt=""
											class="circle responsive-img">
									</div>
									<div class="club-badge" style="margin-left: 0.4em">
										<c:out value="${albums}" />
									</div>
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
								<h3 class="center" style="color: black">
									<fmt:message bundle="${msg}" key="events" />
								</h3>
								<div class="row valign-wrapper">
									<div class="col s8">
										<img style="margin-top: 0em" src="images/club/events.png"
											alt="" class="circle responsive-img">
									</div>
									<div class="my-badge" style="margin-left: 0.75em">
										<c:out value="${events}" />
									</div>
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
<script type="text/javascript">
	function deleteClubAjax(clubId) {
		$.ajax({
			type : 'get',
			url : 'deleteClubAjax',
			dataType : 'JSON',
			data : {
				'clubId' : clubId
			},
			complete : function(data) {
				document.getElementById(1).hidden = true;
				document.getElementById(2).hidden = true;
				document.getElementById(4).hidden = true;
				document.getElementById(5).hidden = true;
				document.getElementById(6).hidden = true;
				document.getElementById(7).hidden = true;
				document.getElementById(3).hidden = false;
			}
		});
	}

	function restoreClubAjax(clubId) {
		$.ajax({
			type : 'get',
			url : 'restoreClubAjax',
			dataType : 'JSON',
			data : {
				'clubId' : clubId
			},
			complete : function(data) {
				document.getElementById(1).hidden = false;
				document.getElementById(2).hidden = false;
				document.getElementById(4).hidden = false;
				document.getElementById(5).hidden = false;
				document.getElementById(6).hidden = false;
				document.getElementById(7).hidden = false;
				document.getElementById(3).hidden = true;
			}
		});
	}
</script>