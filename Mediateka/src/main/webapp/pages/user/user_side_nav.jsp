<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../form/new_event_form.jsp" />

<div class="container-side">
	<div id="sidebar" class="z-depth-4">
		<ul>
			<li><a href="index">Main page</a></li>
			<li><a href="clubs">Clubs</a></li>
			<li><a href="events">Events</a></li>
			<li><a href="" data-target="modal3"
				class="modal-trigger waves-effect">Create event</a></li>
			<li><a href="activity">Activity</a></li>
		</ul>
	</div>
	<div class="main-content">
		<div class="swipe-area"></div>
		<a href="#!" data-toggle=".container-side" id="sidebar-toggle"> <span
			class="bar"></span> <span class="bar"></span> <span class="bar"></span>
		</a>
	</div>
</div>
