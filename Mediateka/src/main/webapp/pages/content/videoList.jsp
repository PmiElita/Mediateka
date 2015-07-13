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
	<div   style="margin-left:2em">
		<c:forEach var="record" items="${records }">
			<c:forEach var="video" items="${videoMap.get(record.id) }">
				<li class="col s6" style="font-size:1.5em"><c:out value="${video.name}"></c:out><video style="margin-left:-1.5em; border-radius:5%" width="100%"
						poster="${posterMap.get(video.id).path }" onclick="this.play();"
						controls="controls" title='<c:out value="${video.name}"></c:out>'>
						<source src='<c:out value="${video.path}"></c:out>'
							type="video/mp4">
					</video></li>
			</c:forEach>
		</c:forEach>
	</div>
</div>
</div>
</div>