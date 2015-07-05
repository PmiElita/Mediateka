<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create form record</title>
<jsp:include page="../general/head.jsp" />
</head>
<body>

	<jsp:include page="../general/nav.jsp" />
	<jsp:include page="../admin/admin_side_nav.jsp" />

	<div class="parallax-container my-parallax">
		<div class="parallax">
			<img src="images/parallax1.jpg">
		</div>

		<div class="container section white">
			<h4>Create form record:</h4>

			${message}
			<form id="create_form_record" action="CreateFormRecord" method="post">

				<div class="row">
					<div class="input-field col s6">
						<p>User ID</p>
						<input id="userId" name="userId" required="required"
							max="99999999999" type="number">
					</div>


					<div class="input-field col s6">
						<p>Time till</p>
						<input data-field="time" type="text" id="timeTill" name="timeTill" required="required">
							<div id="dtBox"></div>
					</div>
				</div>

				<div class="row">
					<h5>Goal:</h5>
					
				<fieldset>	
					<div class="row">
						<div class="col s6 right">
							<input type="radio" name="goal" value="book" checked>Book
						</div>

						<div class="col s6 left">
							<select name="book" class="browser-default">
								<option disabled>Book name</option>
								<c:forEach var="item" items="${books}">
									<option value="${item.getId()}"><c:out
											value="${item.getName()}" /></option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row">

						<div class="col s6 right">
							<input type="radio" name="goal" value="event">Event
						</div>

						<div class="col s6 left">
							<select class="browser-default" name="event">
								<option disabled>Event name</option>
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
					<input type="submit" value="Create form record">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>