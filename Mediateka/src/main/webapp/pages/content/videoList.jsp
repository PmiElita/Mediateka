<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />
<div class="row">
	<div id="listVideos">
		<div id="load"></div>
		<label id="index" hidden="true">${index}</label>
		<div id="uploaded">
			<div style="margin-left: 2em">
				<c:forEach var="record" items="${records }">
					<c:forEach var="video" items="${videoMap.get(record.id) }">
						<div id="restore${video.id}" hidden="true">
							<div title="${video.name }" class="col s6"
								style="margin-bottom: 3.0em;">
								<a
									onclick="restoreMedia(${video.id }); Materialize.toast('<fmt:message bundle="${msg}" key="record_central.restored" />', 4000)"
									class="waves-effect waves-light btn"><fmt:message
										bundle="${msg}" key="record_central.restore" /></a>

							</div>
						</div>
						<li id="mediaId${video.id}" class="col s6"
							style="font-size: 1.5em"><c:out value="${video.name}"></c:out>
							<c:if test="${userId eq record.creatorId}">

								<span
									onclick="deleteMedia(${video.id}); Materialize.toast('<fmt:message bundle="${msg}" key="record_central.deleted" />', 4000);"
									onmouseover="this.style.color = 'red';"
									onmouseleave="this.style.color = 'black';"
									class="waves-effect waves-circle waves-red">X</span>

							</c:if> <c:if test="${userId ne record.creatorId}">
								<pre>
									<span> </span>
								</pre>
							</c:if> <video style="margin-left: -1.5em; border-radius: 5%"
								width="100%" poster="${posterMap.get(video.id).path }"
								onclick="this.play();" controls="controls"
								title='<c:out value="${video.name}"></c:out>'>
								<source src='<c:out value="${video.path}"></c:out>'
									type="video/*">
							</video></li>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
	</div>
</div>