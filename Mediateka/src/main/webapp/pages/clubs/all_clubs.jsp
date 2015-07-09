<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />

<div class="row" style="margin-top: -1em; width:125%">
	<br>
</div>
<div class="events_poster">
		<c:choose>
			<c:when test="${!(allClubs==null)}">
						<c:forEach var="item" items="${allClubs}" varStatus="status">
							<div class="row my-picture-row">
								<div class="col s12">
									<div
										class="waves-effect waves-block waves-light my-picture-wrap" style="border:3px solid #008080;background:grey">
										<a href="club?clubId=${item.id}" style="min-width: 100%">
											<div align="center">
												<h2 class="image-cover-t">
													<c:out value="${item.name}" />
												</h2>
										
												<img class="my-picture-club center"
													src="${allClubsAvas[status.index]}" align="middle" style="margin-top:-10.2em;">
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
										<h2> <fmt:message bundle="${msg}" key="there_are_no_such_clubs"/> </h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
</div>