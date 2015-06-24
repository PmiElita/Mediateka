<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="recordForm" enctype="multipart/form-data"
	onsubmit="handleForm(e);">

	<input type="hidden" name="clubId" id="clubId" value="1">
	<div class="main-info container z-depth-1">
		<div class="row">
			<div class="row" style="margin-left: 3em">
				<div class="col s10" style="margin-top: 2em">
					<div class="input-field">
						<i class="mdi-editor-mode-edit prefix"></i>
						<textarea id="icon_prefix2" class="materialize-textarea"
							name="text" id="text"></textarea>
						<label for="icon_prefix2">Message</label>
					</div>
				</div>

			</div>

			<div class="col offset-s10" style="margin-top: -5em">
				<div style="margin-left:0.5em">
					<button class="btn waves-effect" type="submit" name="action">
						<i class="small mdi-content-send"></i>
					</button>
				</div>
			</div>

			<div id="progress"></div>
			<div class="row" style="margin-left: 4em">
				<div class="file-field input-field col s1 offset-s8">
					<div class="btn">
						<span><i class="small mdi-image-camera-alt"></i></span> <input
							type="file" id="image" name="image" multiple accept="image/*">

					</div>
				</div>
				<div class="file-field input-field col s1">
					<div class="btn">
						<span><i class="small mdi-av-video-collection"></i></span> <input
							type="file" id="video" name="video" multiple accept="video/*"
							onload="loading();">
					</div>

				</div>
				<div class="file-field input-field col s1">
					<div class="btn">
						<span><i class="small mdi-av-queue-music"></i></span> <input
							type="file" id="audio" name="audio" multiple accept="audio/*">
					</div>
				</div>
				<div class="row">
					<div class="col s8">
						<div id="selectedImages"></div>
					</div>
				</div>

				<div class="row">
					<div class="col s8">
						<div id="selectedVideos"></div>
					</div>
				</div>

				<div class="row">
					<div class="col s8">
						<div id="selectedAudios"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<label id="number" hidden="true">1</label>

</form>
<form id="clubForm" action="record" method="post"></form>
<div style="padding-top: 4.0em">
	<c:forEach var="record" items="${records}">
		<div class="main-info container z-depth-1">
			<div class="row">
				<c:out value="${record.text}" />
				<c:forEach var="media" items="${mediaMap.get(record.id)}">
			${record.id}
			${media.path}
			<c:choose>
						<c:when test="${media.type == 'IMAGE'}">
							<img src='<c:out value="${media.path}"></c:out>' width='200'
								height='150'>
						</c:when>
						<c:when test="${media.type == 'VIDEO'}">
							<video controls>
								<source src='<c:out value="${media.path}"></c:out>'>
							</video>
						</c:when>
						<c:when test="${media.type == 'AUDIO'}">
							<audio controls>
								<source src='<c:out value="${media.path}"></c:out>'>
							</audio>
						</c:when>
					</c:choose>
				</c:forEach>
				<a><span><i
						class="tiny mdi-action-thumb-down  waves-effect waves-red"></i></span></a> <a><span><i
						class="tiny mdi-action-thumb-up waves-effect waves-green "></i></span></a>
			</div>
		</div>


	</c:forEach>
</div>
<script>
	var selDiv = "";
	var storedImages = [];
	var storedVideos = [];
	var storedAudios = [];
	var num;

	$(document).ready(function() {
		$("#image").on("change", handleFileSelect);
		$("#video").on("change", handleFileSelect);
		$("#audio").on("change", handleFileSelect);

		$("#recordForm").on("submit", handleForm);

		$("body").on("click", ".selFile", removeFile);
	});

	function handleFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		filesArr
				.forEach(function(f) {

					if (f.type.match("image.*")) {
						storedImages.push(f);
						selDiv = $("#selectedImages");
						alert(f);

						var reader = new FileReader();
						reader.onload = function(e) {
							var html = "<img src=\"" + e.target.result + "\" data-file='"+f.name+"'height='100' class='selFile' title='Click to remove'><button name='"+f.name+"'>x</button"
									+ f.name + "<br clear=\"left\"/>";
							selDiv.append(html);

						}
						reader.readAsDataURL(f);
					} else if (f.type.match("video.*")) {
						storedVideos.push(f);
						alert(f);
						selDiv = $("#selectedVideos");

						var reader = new FileReader();
						reader.onload = function(e) {

							var html = "<div><video width = '400' class='selFile' title='Click to remove' controls><source src=\"" + e.target.result + "\"></video></div>";
							selDiv.append(html);
						}
						reader.readAsDataURL(f);
					} else if (f.type.match("audio.*")) {
						storedAudios.push(f);
						alert(f);

						selDiv = $("#selectedAudios");

						var reader = new FileReader();
						reader.onload = function(e) {

							var html = "<div><audio class='selFile' title='Click to remove' controls><source src=\"" + e.target.result + "\"></audio></div>";
							selDiv.append(html);

						}
						reader.readAsDataURL(f);
					} else {
						return;
					}
				});
	}

	function handleForm(e) {
		alert(1);
		e.preventDefault();
		var data = new FormData();
		data.append('text', $('textarea').val());
		for (var i = 0, len = storedImages.length; i < len; i++) {
			data.append('image', storedImages[i]);
		}
		for (var i = 0, len = storedVideos.length; i < len; i++) {
			data.append('video', storedVideos[i]);
		}
		for (var i = 0, len = storedAudios.length; i < len; i++) {
			data.append('audio', storedAudios[i]);
		}

		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'loadRecord', true);

		xhr.onload = function(e) {
			if (this.status == 200) {
				console.log(e.currentTarget.responseText);
				alert(' items uploaded.');
			}
		}

		xhr.send(data);
	}

	function removeFile(e) {
		var file = $(this).data("file");
		for (var i = 0; i < storedImages.length; i++) {
			if (storedImages[i].name === file) {
				storedImages.splice(i, 1);
				break;
			}
		}
		for (var i = 0; i < storedVideos.length; i++) {
			if (storedVideos[i].name === file) {
				storedVideos.splice(i, 1);
				break;
			}
		}
		for (var i = 0; i < storedAudios.length; i++) {
			if (storedAudios[i].name === file) {
				storedAudios.splice(i, 1);
				break;
			}
		}
		$(this).parent().remove();
	}
</script>