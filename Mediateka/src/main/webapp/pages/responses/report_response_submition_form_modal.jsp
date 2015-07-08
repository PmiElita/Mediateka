<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/responses_page" var="msg" />


<div id="modal26" class="modal">
	<div class="modal-content white">
		<div class="container">
			
			<h3 class="center"><fmt:message bundle="${msg}"
									key="answer_title" /></h3>
			
			<div class="row">
				<form class="col s12" onsubmit="return false;">

					<input hidden="true" id="responseIdField" type="text" class="validate">

					<div class="row">
						<div class="input-field col s6">
							<input disabled placeholder="-1" id="responseNameField"
								type="text" class="validate"> <label
								for="responseNameField"><fmt:message bundle="${msg}"
									key="name" /></label>
						</div>
						<div class="input-field col s6">
							<input disabled placeholder="santa@example.com"
								id="responseEmailField" type="text" class="validate"> <label
								for="responseEmailField"><fmt:message
										bundle="${msg}" key="email" /></label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col s12">
							<textarea disabled id="requestBody" class="materialize-textarea"></textarea>
							<label for="requestBody"><fmt:message
										bundle="${msg}" key="request" /></label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col s12">
							<textarea id="responseBody" class="materialize-textarea"></textarea>
							<label for="responseBody"><fmt:message
										bundle="${msg}" key="your_response_here" /></label>
						</div>
					</div>


					<button id = "sendAnswerButton" class=" waves-effect waves-white btn" style="float: right;"
						onclick="sendResponseToReport();">
						<fmt:message bundle="${msg}" key="send" />
					</button>
				</form>
			</div>
		</div>
	</div>
</div>
