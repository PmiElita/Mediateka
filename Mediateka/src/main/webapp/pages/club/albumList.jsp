
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />



<!-- fotorama.css & fotorama.js. -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
<!-- 3 KB -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
<!-- 16 KB -->
</head>


<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>


<div id="load"></div>
<label id="index" hidden="true">${index}</label>

<c:forEach var="album" items="${records}">
	<div id="recordId${album.id}" class="col s3">
		<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
			<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
				<div style="">
					<a href="clubAlbums?clubId=${clubId}">
						<h3 style="color: black">${album.name }</h3>
						<div class="row valign-wrapper">
							<div class="col s9">
								<img src="${imageMap.get(album.id)[0].path}" alt=""
									class=" responsive-img">
							</div>
							<div class="club-badge" style="margin-left: 0.4em">${fn:length(imageMap.get(album.id)) }</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</c:forEach>