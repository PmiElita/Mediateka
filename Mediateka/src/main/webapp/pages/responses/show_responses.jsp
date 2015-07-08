<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.mediateka.model.enums.State"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/responses_page" var="msg" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="../general/head.jsp" />

</head>
<body>

	<jsp:include page="report_response_submition_form_modal.jsp" />

	<div class="main">
		<jsp:include page="../general/nav.jsp" />
		<jsp:include page="../admin/admin_side_nav.jsp" />


		<div class="parallax-container my-parallax" style="max-height: 100%;">
			<div class="parallax" style="max-height: 100%">
				<img src="images/parallax1.jpg">
			</div>

			<div class="container section white" style="width: 60%">

				<h3 class="center">
					<fmt:message bundle="${msg}" key="responses" />
				</h3>

				<div id="responses_div">
					<c:forEach var="response" items="${responses }">
						<div class='row' id='responseNo${response.id }'>
							<div class='col s12'>
								<div class='card blue-grey darken-1'>
									<div class='card-content white-text'>
										<c:choose>
											<c:when test="${response.state eq State.ACTIVE}">
												<span class='card-title'><fmt:message bundle="${msg}"
														key="newResponse" /></span>
											</c:when>
										</c:choose>
										<div class="row">
											<div class="col s12">
												<p style="color: black">
													<fmt:message bundle="${msg}" key="text" />
												</p>

											</div>
										</div>
										<div class="row">
											<div class="col s12">
												<p id="responseTextNo${response.id }">${response.text }</p>
											</div>
										</div>
										<div class="row"></div>
										<div class="row">
											<div class="col s6">
												<p>
													<span style="color: black"><fmt:message
															bundle="${msg}" key="name" /></span> ${response.name }
												</p>
											</div>
											<div class="col s6">
												<p>
													<span style="color: black"><fmt:message
															bundle="${msg}" key="email" /></span> ${response.email }
												</p>
											</div>
										</div>
										<div class="row">
											<div class="col s12">
												<p>
													<span style="color: black"><fmt:message
															bundle="${msg}" key="date" /></span> 

													<fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${response.date }" />
												</p>
											</div>
										</div>
									</div>
									<div class='card-action' style="padding-top: 0">
										<a href='#' onclick='deleteResponse(${response.id})'><fmt:message
												bundle="${msg}" key="delete" /></a> <a href=""
											class="btn modal-trigger waves-effect" data-target="modal26"
											onclick="markResponse(${response.id}, '${response.name }', '${response.email }')"><fmt:message
												bundle="${msg}" key="answer" /></a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<c:forEach begin="${first_page }" end="${last_page }"
					varStatus="loop">

					<c:choose>
						<c:when test="${loop.index == current_page }">${loop.index }</c:when>
						<c:when test="${loop.index != current_page }">
							<a
								href="showResponsesPage?offset=${ loop.index * limit }&limit=${limit}">
								${loop.index } </a>
						</c:when>
					</c:choose>


				</c:forEach>

			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />





	<script type="text/javascript">

function deleteResponse(responseId){
	$.ajax(
			{
				url:'deleteResponse', 
				data : { 'responseId' : responseId}, 
				success : 
					function(data){
						location.reload();
					}
			} 
		);

};


function markResponse(responseId, name, email){
	

	var text = document.getElementById("responseTextNo" + responseId).innerHTML;
	
	
	document.getElementById("responseIdField").value = responseId;
	document.getElementById("responseNameField").value = name;
	document.getElementById("responseEmailField").value = email;
	document.getElementById("requestBody").value = text;
	document.getElementById("responseBody").value = "";
}


function sendResponseToReport( ){

	responseId = document.getElementById("responseIdField").value;
	responseBody = document.getElementById("responseBody").value;

	document.getElementById("sendAnswerButton").disabled = true;
	$.ajax(
			{
				url:'sendResponseToReport', 
				data : { 
					'reportId' : responseId,
					'text' : responseBody
					}, 
				success : 
					function(data){
						Materialize.toast("<fmt:message bundle="${msg}" key="done" />", 4000);
						document.getElementById("sendAnswerButton").disabled = false;
				},
				error: 
					function (error) {
						Materialize.toast("<fmt:message bundle="${msg}" key="cant_send_email" />", 4000);
						document.getElementById("sendAnswerButton").disabled = false;
	              	}
			} 
		);
	
}

</script>
</body>
</html>