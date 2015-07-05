<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="modal26" class="modal">
	<div class="modal-content">
		<div class="container white">






			<div class="row">
				<form class="col s12" onsubmit="return false;">

					<input hidden id="responseIdField" type="text" class="validate">

					<div class="row">
						<div class="input-field col s6">
							<input disabled placeholder="-1" id="responseNameField"
								type="text" class="validate"> <label
								for="responseNameField">Name</label>
						</div>
						<div class="input-field col s6">
							<input disabled placeholder="santa@example.com"
								id="responseEmailField" type="text" class="validate"> <label
								for="responseEmailField">Email</label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col s12">
							<textarea disabled id="requestBody" class="materialize-textarea"></textarea>
							<label for="requestBody">Request</label>
						</div>
					</div>

					<div class="row">
						<div class="input-field col s12">
							<textarea id="responseBody" class="materialize-textarea"></textarea>
							<label for="responseBody">Your response here</label>
						</div>
					</div>


					<button class=" waves-effect waves-white btn" style="float: right;"
						onclick="sendResponseToReport();">
						<fmt:message bundle="${msg}" key="confirm" />
					</button>
				</form>
			</div>





		</div>
	</div>
</div>
