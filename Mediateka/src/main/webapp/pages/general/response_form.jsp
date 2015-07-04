<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/response_form" var="msg" />


<!DOCTYPE html>
<html>
<head>
<title>Response form</title>
<jsp:include page="../general/head.jsp" />

</head>
<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="TTWForm-container">


			<div id="form-title" class="form-title field">
				<h2>
					<fmt:message bundle="${msg}" key="formTitle" />
				</h2>
			</div>


			<form onsubmit="return sendResponse()">


				<div id="field1-container" class="field f_100">
					<label for="field1"> <fmt:message bundle="${msg}" key="nameField" /> </label> <input type="text" name="name"
						id="field1" required="required">
				</div>


				<div id="field3-container" class="field f_100">
					<label for="field3"> <fmt:message bundle="${msg}" key="emailField" /> </label> <input type="text" name="email"
						id="field3" required="required">
				</div>


				<div id="field4-container" class="field f_100">
					<label for="field4"> <fmt:message bundle="${msg}" key="bodyField" /> </label>
					<textarea rows="5" cols="20" name="response" id="field4"
						required="required"></textarea>
				</div>


				<div id="form-submit" class="field f_100 clearfix submit">
					<input type="submit" value="<fmt:message bundle="${msg}" key="button" />">
				</div>
			</form>
		</div>

	</div>



	<script type="text/javascript">
		function sendResponse() {

			Materialize.toast("sending response", 4000);

			request = {
				'name' : document.getElementsByName("name")[0].value,
				'email' : document.getElementsByName("email")[0].value,
				'response' : document.getElementsByName("response")[0].value,
			};

			Materialize.toast("request: " + JSON.stringify(request), 10000);

			$.ajax({
				url : 'sendResponse',
				type : 'post',
				data : request,
				success : function(data) {
					Materialize.toast("thank you for your response", 4000);
				}
			});

			return false;
		}
	</script>
</body>
</html>