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



<script src="js/record.js"></script>
<style>
audio {
	width: 100%;
}
</style>



<div id="load"></div>
<label id="index" hidden="true">${index}</label>
<div id="uploaded">
	<c:forEach var="record" items="${records}">
		<div class="grey lighten-5 z-depth-1" style="margin-top: 5%;"
			id="restore${record.id }" hidden="true">
			<div class="valign-wrappert">
				<div class="col s12">
					<div class="card-title card-panel grey lighten-5 z-depth-1 row"
						style="padding: 0px;">
						<div class="valign-wrapper">
							<div class="col s1">
								<img
									src="http://cs620219.vk.me/v620219213/17bc9/dNN2ANduYDg.jpg"
									alt="" class="circle" style="height: 45px;">
							</div>
							<div align="left" class="col s3">
								<c:out value="${creatorName[record.id]}"></c:out>
							</div>
							<a onclick="restoreRecord(${record.id }); Materialize.toast('Restored', 4000)"
								class="waves-effect waves-light btn">Restore</a>
							<div class="col s7" align="right">
								<fmt:formatDate value="${record.creationDate}"
									pattern="HH:mm dd.MM.yyyy" />
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div id="recordId${record.id}" class="grey lighten-5 z-depth-1"
			style="margin-top: 5%;">
			<div class="valign-wrappert">
				<div class="col s12">
					<div class="card-title card-panel grey lighten-5 z-depth-1 row"
						style="padding: 0px;">
						<div class="valign-wrapper">
							<div class="col s1">
								<img
									src="http://cs620219.vk.me/v620219213/17bc9/dNN2ANduYDg.jpg"
									alt="" class="circle" style="height: 45px;">
							</div>
							<div align="left" class="col s3">
								<c:out value="${creatorName[record.id]}"></c:out>
							</div>
							<div class="col s7" align="right">
								<fmt:formatDate value="${record.creationDate}"
									pattern="HH:mm dd.MM.yyyy" />
							</div>
							<c:if test="${userId eq record.creatorId}">
								<div class="col s1 ">
									<span onclick="deleteRecord(${record.id}); Materialize.toast('Deleted', 4000);"
										onmouseover="this.style.color = 'red';"
										onmouseleave="this.style.color = 'black';"
										class="waves-effect waves-circle waves-red">X</span>
								</div>
							</c:if>
						</div>
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
							data-thumb='<c:out value="${image.path}"></c:out>'> <img
							src='<c:out value="${image.path}"></c:out>' data-fit="scaledown">
						</a>
					</c:forEach>
				</div>
				<div class="fotorama " data-width="700" data-maxwidth="100%"
					data-ratio="16/9" align="center" data-video="true"
					data-click="false" data-swipe="false">
					<c:forEach var="video" items="${videoMap.get(record.id) }">

						<video width="700" poster="${posterMap.get(video.id).path }"
							onclick="this.play();" controls="controls"
							title='<c:out value="${video.name}"></c:out>'>
							<source src='<c:out value="${video.path}"></c:out>'
								type="video/mp4">
						</video>
					</c:forEach>
				</div>
				<div align="left">
					<c:forEach var="audio" items="${audioMap.get(record.id) }">
						<div class="card-title  grey lighten-1 row"
							style="border-radius: 5px">
							<div class="col s12" style="padding-left: 4%">
								<c:out value="${audio.name}"></c:out>
							</div>
							<audio title="<c:out value="${audio.name}"></c:out>" controls>
								<source src='<c:out value="${audio.path}"></c:out>'>
							</audio>

						</div>

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
		<div class="row">
			 <ul class="collapsible comments" data-collapsible="accordion">
		 <li>
		 <div class="collapsible-header"><i
	id="collapse-icon" class="mdi-communication-comment"></i>Comments</div>
		 <div class="collapsible-body  " >

			<div class=" comment-area" id="commentArea">
				<c:forEach var="chatMessage" items="${chatMessages }">
					<div class="col s12 comment-item">
						<div class="card-panel grey lighten-5 z-depth-1"
							style="padding: 5px;">
							<div class="row valign-wrapper"
								style="line-height: 20px; margin-bottom: 5px;">
								<div class="col s1">

									<img src="${chatMessage.userCard.path }" alt=""
										class="circle responsive-img">
									<!-- notice the "circle" class -->

								</div>


								<div class="col s10">
									<span class="comment_user_name"> <c:out
											value="${chatMessage.userCard.firstName }"></c:out> : </span> 
											<span class="comment-text"> <c:out
											value="${chatMessage.chatMessage.text}"></c:out>
									</span>
								</div>
							</div>

						</div>
					</div>

				</c:forEach>
			</div>
			<div class="panel input-area ">
				<textarea id="commentInput" class="materialize-textarea"
					placeholder="<fmt:message bundle="${msg}" key="message" />"></textarea>

				<button class=" col s4 offset-s9 btn" onclick="addComment();"><fmt:message bundle="${msg}" key="send" /></button>

			</div>

		
		 </div>
		 </li>
		 </ul>
		</div>
	</c:forEach>
</div>