<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />

<html>

<head>







<jsp:include page="../general/head.jsp" />

<jsp:include page="../../js/record.js.jsp" />
<script src="js/record.js"></script>

</head>

<script>
	$(document).ready(function() {
		$("#loadAudioForm").on("submit", handleAudio);

	});

	function handleAudio(e) {
		alert(1);

		e.preventDefault();
		var data = new FormData();
		data.append('index', $('#index').text());
		if (document.getElementById('clubId') != null) {
			data.append('clubId', document.getElementById('clubId').value);
		}
		if (document.getElementById('eventId') != null) {
			data.appent('eventId', document.getElementById('clubId').value);
		}
		for (var i = 0, len = storedAudios.length; i < len; i++) {
			data.append('audio', storedAudios[i]);
		}
		if (storedAudios.length == 0) {
			return;
		}

		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'loadAudio', true);

		xhr.onload = function(e, data) {
			if (this.status == 200) {
				document.getElementById("loadAudioForm").reset();
				document.getElementById("selectedAudios").innerHTML = "";
				storedAudios = [];
				alert(JSON.stringify(e.currentTarget));
				var responseJSON = JSON.parse(e.currentTarget.responseText);
				alert(responseJSON);
				loadIndex = document.getElementById('index').textContent;
				var loadEl = document.getElementById("load");
				loadIndex++;
				$("#index").text(loadIndex);
				loadEl.id = loadEl.id + loadIndex;
				$('#uploaded').remove();
				$('#audiojs_wrapper' + (document.getElementById("index").textContent - 1)).remove();
				$('#' + loadEl.id).load(
						"viewNewAudio?audioId="
								+ responseJSON["contentGroup"].id + "&index="
								+ document.getElementById("index").textContent);
				
				
// 				$('#audioPlayer').remove();
// 				$('.playing').removeClass('playing');
				$('#addAudio').closeModal();
				$('#audiojs_wrapper' + (document.getElementById("index").textContent)).unwrap();				
				Materialize.toast(' items uploaded.', 2000);
			}
		}

		xhr.send(data);

	}
</script>
<script src="audiojs/audio.min.js"></script>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />


		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<c:if test="${userRole eq Role.ADMIN}">
				<jsp:include page="../admin/admin_side_nav.jsp" />
			</c:if>

			<c:if test="${userRole eq Role.USER}">
				<jsp:include page="../user/user_side_nav.jsp" />
			</c:if>

			<div class="section">
				<div class="container">
					<h3 class="image-cover-t">${clubName}</h3>
					<div class="row">
						<h4 class="image-cover-t"><fmt:message bundle="${msg }" key="club_audios.audios" /></h4>
						<div class="col s9">
							<a title="??Add audio??" href="" data-target="addAudio"
								class="modal-trigger"> <span><i
									class="medium mdi-av-queue"></i></span>
							</a>
						</div>
					</div>
				</div>
			</div>

			<div class="section white">
				<div id="wrapper">
					<!-- 					<audio preload></audio> -->
					<ol>
						<jsp:include page="audioList.jsp" />
					</ol>
				</div>
			</div>



		</div>
		<jsp:include page="../general/footer.jsp" />
	</div>
	<div id="addAudio" class="modal">
		<div class="modal-content">


			<form id="loadAudioForm" enctype="multipart/form-data">

				<c:if test="${clubId ne null}">
					<input type="hidden" name="clubId" id="clubId" value='${clubId }'>
				</c:if>
				<c:if test="${eventId ne null}">
					<input type="hidden" name="eventId" id="eventId"
						value="${eventId }">
				</c:if>


				<div class="row">
					<div class="col s3">
						<div class="row">
							<div class="file-field input-field">
								<input class="file-path validate" type="hidden" />
								<div class="btn">
									<span><fmt:message bundle="${msg }" key="club_audios.choose_files"/></span> <input type="file" id="audio"
										multiple name="audio" accept="audio/*" />
								</div>
								<label id="number" hidden="true">1</label>
							</div>
						</div>
						<div class="row" style="margin-top: 5em">
							<button class="btn" type="submit"><fmt:message bundle="${msg }" key="club_audios.upload"/></button>
						</div>
					</div>
					<div class="col s9">
						<div id="selectedAudios"></div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	var audio;
	var a;
</script>


</html>



