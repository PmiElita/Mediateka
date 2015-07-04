<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row" style="margin-top: -1em">
	<div class="col s12">
		<ul class="tabs">
			<li class="tab col s2" style="margin-left: 5em;"><a
				href="#my_active_events">Active</a></li>
			<li class="tab col s2" style="margin-left: -5em"><a
				href="#my_blocked_events">Blocked</a></li>
		</ul>
	</div>
</div>
<div class="events_poster">
	<div class="main-info">
		<div id="my_active_events">
			<c:choose>
				<c:when test="${!myActiveEvents.isEmpty()}">
					<div class="section white">
						<div class="container">
							<c:forEach var="item" items="${myActiveEvents}"
								varStatus="status">
								<div class="row my-picture-row">
									<div class="col s8 offset-s2">
										<div
											class="waves-effect waves-block waves-light my-picture-wrap">
											<a href="event?eventId=${item.id}">
												<div align="center">
													<h3 class="image-cover-t">
														<c:out value="${item.name}" />
													</h3>
													<img class="my-picture-club"
														src="${myActiveEventsAvas[status.index]}" align="middle">
												</div>
											</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="section white">
						<div class="container">
							<div class="row my-picture-row">
								<div class="col s8 offset-s2">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap">
										<div align="center">
											<h2>There are no such events!</h2>
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
					<div class="section white">
						<div class="container">
							<c:forEach var="item" items="${myBlockedEvents}"
								varStatus="status">
								<div class="row my-picture-row">
									<div class="col s8 offset-s2">
										<div
											class="waves-effect waves-block waves-light my-picture-wrap">
											<a href="event?eventId=${item.id}">
												<div align="center">
													<h3 class="image-cover-t">
														<c:out value="${item.name}" />
													</h3>
													<img class="my-picture-club"
														src="${myBlockedEventsAvas[status.index]}" align="middle">
												</div>
											</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="section white">
						<div class="container">
							<div class="row my-picture-row">
								<div class="col s8 offset-s2">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap">
										<div align="center">
											<h2>There are no such events!</h2>
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
</div>