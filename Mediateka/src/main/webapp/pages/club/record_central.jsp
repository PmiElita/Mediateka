<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- fotorama.css & fotorama.js. -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
<!-- 3 KB -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
<!-- 16 KB -->

<link href="http://vjs.zencdn.net/4.12/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/4.12/video.js"></script>

<div id="load"></div>
<label id="index" hidden="true">${index}</label>
<div id="uploaded">
	<c:forEach var="record" items="${records}">
		<div class="card-panel grey lighten-5 z-depth-1">
			<div class="row  valign-wrappert">
				<div class="card-title card-panel grey lighten-5 z-depth-1 row">
					<div align="left" class="col">
						<c:out value="${creatorName[\"${ record.id}\"] }"></c:out>
					</div>
					<div class="col s2 offset-s8">

						<fmt:formatDate value="${record.creationDate}"
							pattern="dd.MM.yyyy HH:mm" />
					</div>
				</div>
				<div align="left">
					<c:out value="${record.text}" />
				</div>
				<div class="fotorama" id='gallery${record.id }' data-nav="thumbs"
					data-width="700" data-maxwidth="100%" data-ratio="16/9"
					data-allowfullscreen="true" data-nav="thumbs" data-keyboard="true"
					align="center" data-swipe="true">
					<c:forEach var="image" items="${imageMap.get(record.id) }">

						<a href='<c:out value="${image.path}"></c:out>'
							data-thumb='<c:out value="${image.path}"></c:out>'>
							<img
							src='<c:out value="${image.path}"></c:out>' data-fit="scaledown">
							</a>
					</c:forEach>
				</div>
				<div class="fotorama " data-width="700" data-maxwidth="100%"
					data-ratio="16/9" align="center" data-video="true"
					data-click="false">
					<c:forEach var="video" items="${videoMap.get(record.id) }">
						<video controls>
							<source src='<c:out value="${video.path}"></c:out>'>
						</video>
						<video id="MY_VIDEO_1" class="video-js vjs-default-skin" controls
							preload="auto">
							<source src='<c:out value="${video.path}"></c:out>'>

						</video>
					</c:forEach>
				</div>
				<div align="left">
					<c:forEach var="audio" items="${audioMap.get(record.id) }">
						<audio controls>
							<source src='<c:out value="${audio.path}"></c:out>'>
						</audio>
					</c:forEach>
				</div>
			</div>
			<div align="right">
				<a><span><i onclick="like('1',${record.id});"
						class="tiny mdi-action-thumb-up waves-effect waves-green "></i></span></a> <span
					id="recordLike${record.id}">${record.like }</span> <a><span><i
						onclick="like('-1',${record.id});"
						class="tiny mdi-action-thumb-down  waves-effect waves-red"></i></span></a> <span
					id="recordDislike${record.id}"><c:out
						value="${record.dislike }"></c:out></span>
			</div>
		</div>
	</c:forEach>
</div>