<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/event_page" var="msg" />

<div class="row" style="margin-top: -1em">
	<div class="col s12">
		<ul class="tabs">
			<li class="tab col s4" style="margin-left: 5em;"><a
				href="#my_active_events"><fmt:message bundle="${msg}" key="my_events.active" /></a></li>
			<li class="tab col s4"><a
				href="#my_blocked_events"><fmt:message bundle="${msg}" key="my_events.blocked" /></a></li>
			<li class="tab col s4" style="margin-left: -5em"><a
				href="#my_requested_events"><fmt:message bundle="${msg}" key="my_events.requested" /></a></li>
		</ul>
	</div>
</div>
<div class="events_poster">
	<div id="my_active_events">
		<c:choose>
			<c:when test="${!myActiveEvents.isEmpty()}">
				<c:forEach var="item" items="${myActiveEvents}" varStatus="status">
					<div class="row my-picture-row">
						<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap" style="border:3px solid #008080;background:grey">
								<a href="event?eventId=${item.id}">
									<div align="center">
										<h2 class="image-cover-t">
											<c:out value="${item.name}" />
										</h2>
										<img class="my-picture-club"
											src="${myActiveEventsAvas[status.index]}" align="middle" style="margin-top:-10.2em;">
									</div>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="section white">
					<div class="container">
						<div class="row my-picture-row">
							<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap" style="border:3px solid #008080;background:grey">
									<div align="center">
										<h2><fmt:message bundle="${msg}" key="there_are_no_such_events" /></h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="my_blocked_events">
		<c:choose>
			<c:when test="${!myBlockedEvents.isEmpty()}">
				<c:forEach var="item" items="${myBlockedEvents}" varStatus="status">
					<div class="row my-picture-row">
						<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap" style="border:3px solid #008080;background:grey">
								<a href="event?eventId=${item.id}">
									<div align="center">
										<h2 class="image-cover-t">
											<c:out value="${item.name}" />
										</h2>
										<img class="my-picture-club"
											src="${myBlockedEventsAvas[status.index]}" align="middle" style="margin-top:-10.2em;">
									</div>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="section white">
					<div class="container">
						<div class="row my-picture-row">
							<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap" style="border:3px solid #008080;background:grey">
									<div align="center">
										<h2><fmt:message bundle="${msg}" key="there_are_no_such_events" /></h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div id="my_requested_events">
		<c:choose>
			<c:when test="${!myRequestedEvents.isEmpty()}">
				<c:forEach var="item" items="${myRequestedEvents}" varStatus="status">
					<div class="row my-picture-row">
						<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap" style="border:3px solid #008080;background:grey">
								<a href="event?eventId=${item.id}">
									<div align="center">
										<h2 class="image-cover-t">
											<c:out value="${item.name}" />
										</h2>
										<img class="my-picture-club"
											src="${myRequestedEventsAvas[status.index]}" align="middle" style="margin-top:-10.2em;">
									</div>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="section white">
					<div class="container">
						<div class="row my-picture-row">
							<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap">
									<div align="center">
										<h2><fmt:message bundle="${msg}" key="there_are_no_such_events" /></h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>