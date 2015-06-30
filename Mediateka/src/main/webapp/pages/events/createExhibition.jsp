<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_event" var="msg" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>
<script src="js/eventCreation.js"></script>
<jsp:include page="../general/head.jsp" />
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<jsp:include page="../user/user_side_nav.jsp" />
			<div id="creation_form">
				<div class="container section white">

					<h3>
						<fmt:message bundle="${msg}" key="exhibition.create_exhibition" />
					</h3>

					${message}
					<form name="createExhibition" id="create_exhibition"
						action="CreateExhibition" method="post">

						<div class="row">
							<fmt:message bundle="${msg}" key="exhibition.name" />
							<input type="text" id="name" name="name" required
								pattern=".{1,45}"><br>
						</div>

						<div class="row">
							<p id="wrongDate"></p>

							<div class="col s6">
								<label for="dateFrom" id="labelDateFrom"><fmt:message
										bundle="${msg}" key="exhibition.date_from" /></label> <input
									id="dateFrom" name="dateFrom" type="text" data-field="date"
									required onchange="dateChangeExhibition()">
								<div id="dtBox"></div>
							</div>

							<div class="col s6">
								<label for="dateTill" id="labelDateTill"><fmt:message
										bundle="${msg}" key="exhibition.date_till" />:</label> <input
									id="dateTill" name="dateTill" type="text" data-field="date"
									required onchange="dateChangeExhibition()">
							</div>
						</div>

						<div class="row">
							<fmt:message bundle="${msg}" key="exhibition.description" />
							<textarea name="description" pattern=".{0,255}"
								class="materialize-textarea"></textarea>
							<input type="submit" id="submit"
								value="<fmt:message bundle="${msg}" key="exhibition.create_exhibition_button" />"
								class="btn">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>