<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />
<jsp:include page="../../js/record.js.jsp"/>
<script src="js/record.js"></script>

<div id="addPhoto" class="modal">
	<div class="modal-content">


		<form id="loadPhotoForm" action="loadPhotos" method="post"
			enctype="multipart/form-data">
			<c:if test="${clubId ne null}">
				<input type="hidden" name="clubId" id="clubId" value='${clubId }'>
			</c:if>
			<c:if test="${eventId ne null}">
				<input type="hidden" name="eventId" id="eventId" value="${eventId }">
			</c:if>

				<input name="albumId" id ="albumId" value="${albumId }" hidden>

			<fmt:message bundle="${msg }" key="load_album.files"/>
			<div class="row">
				<div class="col s3">
					<div class="row">
						<div class="file-field input-field">
							<input class="file-path validate" type="hidden" />
							<div class="btn">
								<span><fmt:message bundle="${msg}"
										key="load_album.choose_files" />
									</span> <input
									type="file" id="image" multiple name="image"
									onchange="readURL(this);" accept="image/*" />
							</div>
							<label id="number" hidden="true">1</label>
						</div>
					</div>
					<div class="row" style="margin-top: 5em">
						<button class="btn" type="submit"><fmt:message bundle="${msg}" key="load_album.upload" /></button>
					</div>
				</div>
				<div class="col s9">
					<div id="selectedFiles"></div>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		$("#loadPhotoForm").on("submit", handlePhotos);

	});

	function handlePhotos(e) {
		alert(1);

		e.preventDefault();
		var data = new FormData();
		data.append('index', $('#index').text());
		data.append('albumId', document.getElementById('albumId').value);
		if (document.getElementById('clubId') != null) {
			data.append('clubId', document.getElementById('clubId').value);
		}
		if (document.getElementById('eventId') != null) {
			data.appent('eventId', document.getElementById('clubId').innerHTML
					.toString());
		}
		for (var i = 0, len = storedImages.length; i < len; i++) {
			data.append('image', storedImages[i]);
		}
		if (storedImages.length == 0) {
			return;
		}

		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'loadPhotos', true);

		xhr.onload = function(e, data) {
			if (this.status == 200) {
				document.getElementById("loadPhotoForm").reset();
				document.getElementById("selectedFiles").innerHTML = "";
				storedImages = [];
				alert(JSON.stringify(e.currentTarget));
				var responseJSON = JSON.parse(e.currentTarget.responseText);

				
				$('#photoList').load(
						"viewNewPhoto?albumId="
								+ responseJSON["albumId"]);

				$('#addPhoto').closeModal();

				Materialize.toast(' items uploaded.', 3000);
			}
		}

		xhr.send(data);

	}
</script>
