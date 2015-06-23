<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/side_nav" var="msg" scope="session" />



<jsp:include page="../form/new_event_form.jsp" />
<jsp:include page="../events/create_event.jsp" />

<div class="container-side">
	<div id="sidebar" class="z-depth-4">
		<ul>
			<li><a href="clubs"><fmt:message bundle="${msg}"
						key="user.clubs" /></a></li>
			<li><a href="events"><fmt:message bundle="${msg}"
						key="user.events" /></a></li>
			<li><a href="" data-target="modal18"
					class="modal-trigger waves-effect"><fmt:message
						bundle="${msg}" key="user.create_event" /></a></li>
			<li><a href="activity"><fmt:message bundle="${msg}"
						key="user.activity" /></a></li>
			<li><a href="cabinet"><fmt:message bundle="${msg}"
						key="user.cabinet" /></a></li>
			<li><a href="index"><fmt:message bundle="${msg}"
						key="user.main_page" /></a></li>
		</ul>
	</div>
	<div class="main-content">
		<div class="swipe-area"></div>
		<a href="#!" data-toggle=".container-side" id="sidebar-toggle"> <span
			class="bar"></span> <span class="bar"></span> <span class="bar"></span>
		</a>
	</div>
</div>
