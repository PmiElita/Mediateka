<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/content" var="msg" />

<form id="recordForm" enctype="multipart/form-data">


	<c:if test="${clubId } != null">
		<input type="hidden" name="clubId" id="clubId" value='${clubId }'>
	</c:if>
	<c:if test="${eventId } != null">
		<input type="hidden" name="eventId" id="eventId" value="${eventId }">
	</c:if>

	<div class="section main-info container z-depth-1" style="width: 100%">
		<div class="row">
			<div class="row">
				<div class="col s10" style="margin-top: 2em">
					<div class="input-field image-cover-t">
						<i class="mdi-editor-mode-edit prefix"></i>
						<textarea class="materialize-textarea" name="text" id="text"></textarea>
						<label for="text" style="font-size: 1.5em" style="color:white"><fmt:message
								bundle="${msg}" key="record_form.message" /></label>
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
				<div class="file-field input-field col s2 offset-s1"
					style="margin-left: 18%">
					<div class="btn">
						<span><i class="small mdi-image-camera-alt"></i></span> <input
							style="font-size: 5px" type="file" id="image" name="image"
							multiple accept="image/*">

					</div>
				</div>


				<div class="file-field input-field col offset-s1">
					<div class="btn">
						<span><i class="small mdi-av-video-collection"></i></span> <input
							style="font-size: 5px" type="file" id="video" name="video"
							multiple accept="video/*">
					</div>

				</div>
				<div class="file-field input-field col s2" style="margin-left: 6.3%">
					<div class="btn">
						<a class="waves-effect waves-light btn modal-trigger"
							href="#videoLink"><span><i
								class="small mdi-content-link"></i> </span></a>
					</div>
				</div>





				<div id="videoLink" class="modal">
					<div class="modal-content">
						<div class="row">
							<div class="col s10" style="margin-top: 2em">
								<div class="input-field image-cover-t">
									<i class="small mdi-editor-insert-link prefix"></i>
									<textarea class="materialize-textarea"
										name="contentFromInternet" id="contentFromInternet"></textarea>
									<label for="text" style="font-size: 1.5em" style="color:black;">
										<fmt:message bundle="${msg }" key="insert_video_link" />
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<a href="#!"
							class=" modal-action modal-close waves-effect waves-green btn-flat">Ok</a>
					</div>
				</div>


				<div class="file-field input-field col s2 offset-s1">
					<div class="btn">
						<span><i class="small mdi-av-queue-music"></i></span> <input
							style="font-size: 5px" type="file" id="audio" name="audio"
							multiple accept="audio/*">
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