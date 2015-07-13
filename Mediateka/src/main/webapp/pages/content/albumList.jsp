
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<head>

</head>


<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 0em;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>


<div id="load"></div>
<label id="index" hidden="true">${index}</label>

<c:forEach var="album" items="${albums}">
	<div id="recordId${album.key.id}" class="col s4" style="margin-bottom: 3.0em;">
		<div class="col s12 m8 offset-m2 l6 offset-l2 my-back-card my-small-card">
			<div onclick="loadAlbumPhoto(${album.key.id})"
				class="my-album-card card-panel grey lighten-5 z-depth-1"
				style="padding: 0">
				<a title="${album.key.name }" href="photos?albumId=${album.key.id }" 
					>
					<div class="row valign-wrapper" style="margin: 0;">
						<div class="col s12 center" style="height: 12em;padding-left:0; padding-right:0;" >
							<img class="responsive-image"
								src="${album.value.path}" alt=""
								style="margin: 0 0 0 0; height: 100%; width:100%;  border-radius: 5%">
						</div>
						<div class="club-badge"
							style="z-index: 10; margin-top: 3em; margin-left: -1.3em">${album.value.count }</div>
					</div>
				</a>
			</div>
		</div>
	</div>

	

</c:forEach>


