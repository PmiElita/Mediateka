<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	z-index: 1000;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>


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
<style type="text/css">
.vjs-default-skin .vjs-control-bar {
	font-size: 75%
}
</style>


<script src="js/record.js"></script>
<div class="collapsible-header" style="font-size: 2em">+</div>
<div class="collapsible-body">
	<form id="recordForm" enctype="multipart/form-data">


		<c:if test="${clubId } != null">
			<input type="hidden" name="clubId" id="clubId" value='${clubId }'>
		</c:if>
		<c:if test="${eventId } != null">
			<input type="hidden" name="eventId" id="eventId" value="${eventId }">
		</c:if>

		<div class="section white main-info container z-depth-1"
			style="width: 100%">
			<div class="row">
				<div class="row">
					<div class="col s10" style="margin-top: 2em">
						<div class="input-field image-cover-t">
							<i class="mdi-editor-mode-edit prefix"></i>
							<textarea class="materialize-textarea" name="text" id="text"></textarea>
							<label for="text" style="font-size: 1.5em" style="color:white">Message</label>
						</div>
					</div>
				</div>

				<div class="col offset-s10" style="margin-top: -5em">
					<div style="margin-left: 0.5em">
						<button class="btn waves-effect" type="submit" name="action">
							<i class="small mdi-content-send"></i>
						</button>
					</div>
				</div>

				<div id="progress"></div>
				<div class="row" style="margin-left: -8em">
					<div class="file-field input-field col s2 offset-s3">
						<div class="btn">
							<span><i class="small mdi-image-camera-alt"></i></span> <input
								type="file" id="image" name="image" multiple accept="image/*">

						</div>
					</div>
					<div class="file-field input-field col s2 offset-s1">
						<div class="btn">
							<span><i class="small mdi-av-video-collection"></i></span> <input
								type="file" id="video" name="video" onloadstart="alert(5);"
								multiple accept="video/*">
						</div>

					</div>
					<div class="file-field input-field col s2 offset-s1">
						<div class="btn">
							<span><i class="small mdi-av-queue-music"></i></span> <input
								type="file" id="audio" name="audio" multiple accept="audio/*">
						</div>
					</div>
				</div>
				<div class="row" style="margin-top: 5em">
					<div id="selectedImages"></div>
				</div>

				<div class="row">
					<div id="selectedVideos"></div>
				</div>

				<div class="row">
					<div id="selectedAudios"></div>
				</div>
			</div>
		</div>
		<label id="number" hidden="true">1</label>

	</form>
</div>

<!-- <form id="clubForm" action="record" method="post"></form> -->
<div id="recordList" style="padding-top: 4.0em">
	<div id="uploaded">
		<c:forEach var="record" items="${records}">
			<div class="card-panel grey lighten-5 z-depth-1">
				<div class="row  valign-wrappert">
					<div class="card-title card-panel grey lighten-5 z-depth-1 row">
						<div align="left" class="col">
							<c:out value="${userName }"></c:out>
						</div>
						<div class="col s2 offset-s8">

							<fmt:formatDate value="${record.creationDate}"
								pattern="dd.MM.yyyy HH:mm" />
						</div>
					</div>
					<div align="left">
						<c:out value="${record.text}" />
					</div>
					<div class="fotorama" data-nav="thumbs" data-width="700"
						data-maxwidth="100%" data-ratio="16/9" data-allowfullscreen="true"
						data-nav="thumbs" data-keyboard="true" align="center" data-swipe="true">
						<c:forEach var="image" items="${imageMap.get(record.id) }">

							<a href='<c:out value="${image.path}"></c:out>'><img
								src='<c:out value="${image.path}"></c:out>' data-fit="scaledown"></a>
						</c:forEach>
					</div>
					<div class="fotorama " data-width="700" data-maxwidth="100%"
						data-ratio="16/9" align="center" data-video="true" data-click="false">
						<c:forEach var="video" items="${videoMap.get(record.id) }">
							<video controls>
								<source src='<c:out value="${video.path}"></c:out>'>
							</video>
							<video id="MY_VIDEO_1" class="video-js vjs-default-skin" controls
								preload="auto">
								<source src='<c:out value="${video.path}"></c:out>' type='video/mp4'>
														
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
</div>
