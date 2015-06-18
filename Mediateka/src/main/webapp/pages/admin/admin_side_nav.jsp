<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<jsp:include page="../form/register_user_form.jsp" />
<jsp:include page="admin_modal_users.jsp" />
</head>

<div class="container-side">
	<div id="sidebar" class="z-depth-4">
		<ul>
			<li><a href="index">Main page</a></li>
			<li><a href="events">View events</a></li>
			<li><a href="" data-target="modal4" class="modal-trigger waves-effect">Register new user</a></li>
			<li><a href="" data-target="modal7" class="modal-trigger waves-effect">Users</a></li>
		</ul>
	</div>
	<div class="main-content">
		<div class="swipe-area"></div>
		<a href="#!" data-toggle=".container-side" id="sidebar-toggle"> <span
			class="bar"></span> <span class="bar"></span> <span class="bar"></span>
		</a>
	</div>
</div>
