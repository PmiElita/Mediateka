<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />
<jsp:include page="../../js/record.js.jsp" />
<script src="js/record.js"></script>

<div id="addAlbum" class="modal">
	<div class="modal-content">

		<div id="progress" hidden="true">
			<div class="progress">
				<div id="determinate" class="determinate"></div>
			</div>
		</div>
		<form id="loadAlbumForm" action="loadAlbum" method="post"
			enctype="multipart/form-data">

			<c:if test="${clubId ne null}">
				<input type="hidden" name="clubId" id="clubId" value='${clubId }'>
			</c:if>
			<c:if test="${eventId ne null}">
				<input type="hidden" name="eventId" id="eventId" value="${eventId }">
			</c:if>

			<div class="row">
				<fmt:message bundle="${msg}" key="load_album.album_name" />
				<input type="text" id="name" name="name" required pattern=".{1,45}"
					form="createClub"><br>
			</div>
			<fmt:message bundle="${msg }" key="load_album.files" />
			<div class="row">
				<div class="col s3">
					<div class="row">
						<div class="file-field input-field">
							<input class="file-path validate" type="hidden" />
							<div class="btn">
								<span><fmt:message bundle="${msg}"
										key="load_album.choose_files" /> </span> <input type="file"
									id="image" multiple name="image" onchange="readURL(this);"
									accept="image/*" />
							</div>
							<label id="number" hidden="true">1</label>
						</div>
					</div>
					<div class="row" style="margin-top: 5em">
						<button class="btn" type="submit">
							<fmt:message bundle="${msg}" key="load_album.upload" />
						</button>
					</div>
				</div>
				<div class="col s9">
					<div id="selectedFiles" class="row"></div>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		$("#loadAlbumForm").on("submit", handleAlbum);

	});

	function handleAlbum(e) {
		alert(1);

		e.preventDefault();
		var data = new FormData();
		data.append('index', $('#index').text());
		data.append('name', document.getElementById('name').value);
		if (document.getElementById('clubId') != null) {
			data.append('clubId', document.getElementById('clubId').value);
		}
		if (document.getElementById('eventId') != null) {

			data.append('eventId', document.getElementById('eventId').value);
		}
		for (var i = 0, len = storedImages.length; i < len; i++) {
			data.append('image', storedImages[i]);
		}
		if ((document.getElementById('name').value == "")
				&& (storedImages.length == 0)) {
			return;
		}

		var xhr = new XMLHttpRequest();
		xhr.upload
				.addEventListener(
						"progress",
						function(evt) {
							if (evt.lengthComputable) {
								var percentComplete = evt.loaded / evt.total;
								percentComplete = parseInt(percentComplete * 100);
								document.getElementById("progress").hidden = false;
								document.getElementById("determinate").style.width = percentComplete
										+ "%";
								if (percentComplete === 100) {
									document.getElementById("progress").hidden = true;
								}

							}
						}, false);
		xhr.open('POST', 'loadAlbum', true);

		xhr.onload = function(e, data) {
			if (this.status == 200) {
				document.getElementById("loadAlbumForm").reset();
				document.getElementById("name").value = "";
				document.getElementById("selectedFiles").innerHTML = "";
				storedImages = [];
				alert(JSON.stringify(e.currentTarget));
				var responseJSON = JSON.parse(e.currentTarget.responseText);

				loadIndex = document.getElementById('index').textContent;
				var loadEl = document.getElementById("load");
				loadIndex++;
				$("#index").text(loadIndex);
				loadEl.id = loadEl.id + loadIndex;
				$('#' + loadEl.id).load(
						"viewNewAlbum?albumId="
								+ responseJSON["contentGroup"].id + "&index="
								+ document.getElementById("index").textContent);

				$('#addAlbum').closeModal();

				alert(' items uploaded.');
			}
		}

		xhr.send(data);

	}
</script>
