<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<div class="section white">
	<div class="container">
		<form>
			<div class="row my-picture-row" style="margin-left: -3em">
				<div class="col s8 offset-s2">
					<div class="waves-effect waves-block waves-light my-picture-wrap">
						<h3 class="image-cover-text">${club_name}</h3>
						<img class="my-picture" src="images/club/club_default.png"
							id="ava">
					</div>
				</div>
			</div>
		</form>

		<div class="row" style="margin-left: -7em">
			<div class="col s3">
				<a href="" data-target="modal7" class="modal-trigger">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 style="color: black">Music</h3>
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
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 style="color: black">Video</h3>
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
				<a href="info">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 style="color: black">Photo</h3>
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
				<a href="events">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 style="color: black">Events</h3>
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
		<div class="container">
			<p>${club_description}</p>
		</div>
	</div>
</div>