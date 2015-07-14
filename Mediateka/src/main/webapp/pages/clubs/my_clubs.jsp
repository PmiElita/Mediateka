<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />


<div class="row" style="margin-top: -1em">
	<div class="col s12">
		<ul class="tabs">
			<li class="tab col s4" style="margin-left: 5em;"><a
				href="#my_active_clubs"><fmt:message bundle="${msg}"
						key="my_clubs.active" /></a></li>
			<li class="tab col s4"><a href="#my_blocked_clubs"><fmt:message
						bundle="${msg}" key="my_clubs.blocked" /></a></li>
			<li class="tab col s4" style="margin-left: -5em"><a
				href="#my_requested_clubs"><fmt:message bundle="${msg}"
						key="my_clubs.requested" /></a></li>
		</ul>
	</div>
</div>
<div class="events_poster">
	<div id="my_active_clubs">
		<c:choose>
			<c:when test="${!myActiveClubs.isEmpty()}">
				<c:forEach var="item" items="${myActiveClubs}" varStatus="status">
					<div class="row my-picture-row">
						<div class="col s12">
							<div class="waves-effect waves-block waves-light my-picture-wrap"
								style="border: 3px solid #008080; background: grey">
								<a href="club?clubId=${item.id}" style="min-width: 100%">
									<div align="center">
										<h2 class="image-cover-t">
											<div class="row">
												<c:out value="${item.name} " />
												<c:out value=" (${myActiveClubsMemberNumber[status.index]})" />
											</div>
										</h2>
										<img class="my-picture-club center"
											src="${myActiveClubsAvas[status.index]}" align="middle"
											style="margin-top: -10.2em;">
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
										<h2>
											<fmt:message bundle="${msg}" key="there_are_no_such_clubs" />
										</h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="my_blocked_clubs">
		<c:choose>
			<c:when test="${!myBlockedClubs.isEmpty()}">
				<div class="section white">
					<div class="container">
						<c:forEach var="item" items="${myBlockedClubs}" varStatus="status">
							<div class="row my-picture-row">
								<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap"
										style="border: 3px solid #008080; background: grey">
										<a href="club?clubId=${item.id}" style="min-width: 100%">
											<div align="center">
												<h2 class="image-cover-t">
													<c:out value="${item.name}" />
												</h2>
												<img class="my-picture-club center"
													src="${myBlockedClubsAvas[status.index]}" align="middle"
													style="margin-top: -10.2em;">
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
							<div class="col s12">
								<div
									class="waves-effect waves-block waves-light my-picture-wrap">
									<div align="center">
										<h2>
											<fmt:message bundle="${msg}" key="there_are_no_such_clubs" />
										</h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="my_requested_clubs">
		<c:choose>
			<c:when test="${!myRequestedClubs.isEmpty()}">
				<c:forEach var="item" items="${myRequestedClubs}" varStatus="status">
					<div class="row my-picture-row">
						<div class="col s12">
							<div class="waves-effect waves-block waves-light my-picture-wrap"
								style="border: 3px solid #008080; background: grey">
								<a href="club?clubId=${item.id}">
									<div align="center">
										<h2 class="image-cover-t">
											<c:out value="${item.name}" />
										</h2>
										<img class="my-picture-club"
											src="${myRequestedClubsAvas[status.index]}" align="middle"
											style="margin-top: -10.2em;">
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
										<h2>
											<fmt:message bundle="${msg}" key="there_are_no_such_clubs" />
										</h2>
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
