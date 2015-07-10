<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_event" var="msg" />
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../general/head.jsp" />
<script type="text/javascript" src="js/form_record.js"></script>
</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />
		<jsp:include page="../admin/admin_side_nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>

			<div class="container section white">
				<h4>Create form record:</h4>

				${message}
				<form id="create_form_record" action="CreateFormRecord"
					method="post" onsubmit="return checkFormRecordValidity();">

					<div class="row">
						<div class="input-field col s6">
							<p>Form ID</p>
							<input id="formId" name="formId" required="required" type="text" value ="${formId }">
						</div>

						<div class="input-field col s6">
							<p>Time till</p>
							<input data-field="time" type="text" id="timeTill"
								name="timeTill" required="required">
							<div id="dtBox1"></div>
						</div>
					</div>

					<div class="row">
						<h5>Goal:</h5>

						<fieldset>
							<div class="row">
								<div class = " input-field col s4 right">
									<p>Book ID</p>
									<input id="bookId" name="book" type="text">
								</div>
								<div class="col s2 right">
									<input type="radio" name="goal" value="book" id="bookRadio" checked
										style="margin-top: 3em">Book
									
								   
								</div>
							
								<div class="input-field col s6 left">
									<p>Book name</p>
									<input id="bookName" name="bookName" type="text" autocomplete="off">
								</div>
							</div>

							<div class="row">

								<div class="col s6 right">
									<input type="radio" name="goal" value="event" id ="eventRadio">Event
								</div>

								<div class="col s6 left">
									<select id="event" class="browser-default" name="event">
										<option disabled value="">Event name</option>
										<c:forEach var="item" items="${events}">
											<option value="${item.getId()}">
												<c:out value="${item.getName()}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="col s6 right">
									<input type="radio" name="goal" value="other">Other
								</div>

								<div class="col s6 left">
									<fieldset class="col s12">
										<input type="radio" name="other" value="WI_FI" checked>Wi-fi
										<input type="radio" name="other" value="INTERNET">Internet<br>
									</fieldset>
								</div>
							</div>
						</fieldset>
					</div>


					<div class="row">
						Comment:
						<textarea name="comment" class="materialize-textarea info">
					</textarea>
						<input type="submit" value="Create form record" class="btn">
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function setInvalid(element){
	element.setCustomValidity('No book with such book ID');
	element.setAttribute('class', 'validate invalid');
}
	</script>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>