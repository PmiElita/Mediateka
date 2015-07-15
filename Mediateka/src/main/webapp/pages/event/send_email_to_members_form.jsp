<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/mail_list" var="msg" />

<div id="modal_send_email_to_event_members" class="modal">

	<h3 class="center">
		<fmt:message bundle="${msg}" key="formName" />
	</h3>

	<form onsubmit="return false;"></form>
	<div>
		<div class="row">
			<div class="input-field col s12">
				<input type="text" id="emailSubject" name="emailSubject" required />
				<label for="emailSubject"> <fmt:message bundle="${msg}"
						key="subject" />
				</label>
			</div>
		</div>


		<div class="row">
			<label for="emailBody"> <fmt:message bundle="${msg}"
					key="body" />
			</label>
			<textarea name="emailBody" id="emailBody"
				class="materialize-textarea"></textarea>

		</div>
		<input type="submit" id="submit" name="submit"
			value="<fmt:message bundle="${msg}"
									key="send" />"
			class="btn" onclick="send()">
	</div>
</div>





<script type="text/javascript">
	function send() {

		var requestBody = {
			'eventId' : document.getElementById('eventId').innerHTML,
			'subject' : document.getElementById('emailSubject').value,
			'message' : document.getElementById('emailBody').value
		}

		console.log(requestBody);
		//			return;
		$
				.ajax({
					url : 'sendEmailToEventMembers',
					type : 'post',
					data : requestBody,
					success : function(data) {
						Materialize.toast(
								"<fmt:message bundle="${msg}" key="done" />",
								4000);
					},
					error : function(error) {
						Materialize
								.toast(
										"<fmt:message bundle="${msg}" key="cant_send_email" />",
										4000);
					}

				});

	}
</script>

