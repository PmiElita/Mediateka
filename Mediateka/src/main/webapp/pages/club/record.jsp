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

<form id="recordForm" enctype="multipart/form-data">

	<input type="hidden" name="clubId" id="clubId" value="1">
	<div class="main-info container z-depth-1" style="width: 100%">
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
<!-- <form id="clubForm" action="record" method="post"></form> -->
<div style="padding-top: 4.0em">
	<c:forEach var="record" items="${records}">
		<div class="main-info container z-depth-1">
			<div class="row">
				<fmt:formatDate value="${record.creationDate}"
					pattern="dd.MM.yyyy HH:mm" />
				<c:out value="${record.text}" />
				<c:forEach var="media" items="${mediaMap.get(record.id)}">
			${record.id}		
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
				<a><span><i onclick="like('1');"
						class="tiny mdi-action-thumb-up waves-effect waves-green "></i></span></a> <span
					onclick="document.getElementById('recordLike').innerHTML = 0"
					id="recordLike${record.id}">${record.like }</span> <a><span><i
						onclick="like('-1');"
						class="tiny mdi-action-thumb-down  waves-effect waves-red"></i></span></a> <span
					id="recordDislike${record.id}"><c:out
						value="${record.dislike }"></c:out></span>
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

	function like(value, id) {
		alert(value);
		$
				.ajax({
					type : 'get',
					url : 'likes',
					dataType : 'JSON',
					data : {
						"likeState" : value,
						"recordId" : id
					},
					complete : function(data) {

						// 				alert(JSON.stringify(data));				
						// 				alert(data.responseJSON);
						// 				alert(data.responseJSON.id);
						var id = data.responseJSON.id;
						document.getElementById("recordLike" + id).innerHTML = data.responseJSON.like;
						document.getElementById("recordDislike" + id).innerHTML = data.responseJSON.dislike;
					}
				});

	}
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
		data.append('text', document.getElementById('text').value);
		for (var i = 0, len = storedImages.length; i < len; i++) {
			data.append('image', storedImages[i]);
		}
		for (var i = 0, len = storedVideos.length; i < len; i++) {
			data.append('video', storedVideos[i]);
		}
		for (var i = 0, len = storedAudios.length; i < len; i++) {
			data.append('audio', storedAudios[i]);
		}
		if ((document.getElementById('text').value == "")
				&& (storedImages.length == 0) && (storedAudios.length == 0)
				&& (storedVideos.length == 0)) {
			return;
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