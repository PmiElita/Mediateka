<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />
<fmt:setBundle basename="translations/club_chat" var="msg1" />
<!-- fotorama.css & fotorama.js. -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css"
	rel="stylesheet">
<!-- 3 KB -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
<!-- 16 KB -->



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
								<img src="${creatoAva[record.id] }" alt="" class="circle"
									style="height: 45px;">
							</div>
							<div align="left" class="col s3">
								<c:out value="${creatorName[record.id]}"></c:out>
							</div>
							<a
								onclick="restoreRecord(${record.id }); Materialize.toast('<fmt:message bundle="${msg}" key="record_central.restored" />', 4000)"
								class="waves-effect waves-light btn"><fmt:message
									bundle="${msg}" key="record_central.restore" /></a>
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
								<img src="${creatorAva[record.id] }" alt="" class="circle"
									style="height: 45px;">
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
									<span
										onclick="deleteRecord(${record.id}); Materialize.toast('<fmt:message bundle="${msg}" key="record_central.deleted" />', 4000);"
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
					align="center" data-video="true" data-click="false"
					data-swipe="false">
					<c:forEach var="video" items="${videoMap.get(record.id) }">
						<a href='<c:out value="${video.path}"></c:out>' data-video="true">
							<c:if test="${posterMap.get(video.id).path ne null}">
								<img
									src='<c:out value="${posterMap.get(video.id).path }"></c:out>'>
							</c:if>
						</a>						
						
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
		 <div class="collapsible-header" ><i
	id="collapse-icon" class="mdi-communication-comment"></i><fmt:message bundle="${msg}" key="record_central.comments" /></div>
		 <div class="collapsible-body  " id="colbody${record.id }" >

			<div class=" comment-area" id="commentArea${record.id }" >
			<c:if test="${fn:length(comments.get(record.id)) eq 0 }">
			<span id="noComments${record.id }">No comments</span>
			</c:if>
				<c:forEach var="comment" items="${comments.get(record.id) }">
					<div id = "recordId${comment.comment.id}" class="col s12 comment-item"> 
					
					
						<div class="card-panel grey lighten-5 z-depth-1"
							style="padding: 5px;">
							<div class="row valign-wrapper"
								style="line-height: 20px; margin-bottom: 5px;">
								<div class="col s1">

									<img onload="setCommentHeight(${record.id});" src="${comment.userCard.path }" alt=""
										class="circle responsive-img">
									<!-- notice the "circle" class -->

											</div>


								<div class="col s10">
									<span class="comment_user_name"> <c:out
											value="${comment.userCard.firstName }"></c:out> : </span> 
											<span class="comment-text"> <c:out
											value="${comment.comment.text}"></c:out>
									</span>
								</div>
									<c:if test="${userId eq comment.comment.creatorId}">
								<div class="col s1 ">
									<span onclick="deleteRecord(${comment.comment.id}); Materialize.toast('Deleted', 4000);"
										onmouseover="this.style.color = 'red';"
										onmouseleave="this.style.color = 'black';"
										class="waves-effect waves-circle waves-red">X</span>
								</div>
							</c:if>
								</div>
								
							
								
							
 <div class="row message-date"><span class="comment-date">${comment.comment.formatedCreationDate}</span> <div align="right">
				<a><span><i onclick="like('1',${comment.comment.id});"
						class="tiny mdi-action-thumb-up waves-effect waves-green "></i></span></a> <span
					id="recordLike${comment.comment.id}">${comment.comment.like }</span> <a><span><i
						onclick="like('-1',${comment.comment.id});"
						class="tiny mdi-action-thumb-down  waves-effect waves-red"></i></span></a> <span
					id="recordDislike${comment.comment.id}"><c:out
						value="${comment.comment.dislike }"></c:out></span>
			</div></div> 
			</div>
			</div>
							</c:forEach>
						
						

				
			</div>
			
			<div class="panel input-area ">
				<textarea id="commentInput${record.id}" class="materialize-textarea"
					placeholder="<fmt:message bundle="${msg1}" key="message" />"></textarea>

				<button class=" col s4 offset-s8 btn" onclick="addComment(${record.id});"><fmt:message bundle="${msg1}" key="send" /></button>


					</div>
					</div>
				</li>
			</ul>
	
		</div>
	</c:forEach>
</div>