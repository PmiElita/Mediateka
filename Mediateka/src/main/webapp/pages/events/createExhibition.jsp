<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>

<jsp:include page="../general/head.jsp" />
</head>


<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />
		<div id="creation_form">
			<div class="container">

				<h3 style="margin-top: 2em">Create exhibition</h3>

				${message}
				<form name="createExhibition" id="create_exhibition"
					action="CreateExhibition" method="post">

					<div class="row">
						Name:<input type="text" id="name" name="name" required
							pattern=".{1,45}"><br>
					</div>

					<div class="row">
						<p id="wrongDate"></p>

						<div class="col s6">
							<label for="dateFrom" id="labelDateFrom">Date from:</label> <input
								id="dateFrom" name="dateFrom" type="text" data-field="date">
							<div id="dtBox"></div>
						</div>

						<div class="col s6">
							<label for="dateTill" id="labelDateTill">Date till:</label> <input
								id="dateFrom" name="dateFrom" type="text" data-field="date">
						</div>
					</div>

					<div class="row">
						Description:
						<textarea name="description" pattern=".{0,255}"
							class="materialize-textarea"></textarea>
						<input type="submit" id="submit" value="Create exhibition"
							class="btn">
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

</body>
</html>