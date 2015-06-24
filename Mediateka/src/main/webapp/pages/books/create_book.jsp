<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_book" var="msg" />

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>
<head>
<jsp:include page="../general/head.jsp" />
<script src="js/bookCreation.js"></script>
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />
		<jsp:include page="../admin/admin_side_nav.jsp" />


		<div id="creation_form">

			<div class="container">
				<form id="create_book" action="CreateBook" method="post"
					enctype="multipart/form-data">

					<button type="submit" class="btn waves-effect blue titler"
						id="submit" style="margin-top: 2.5em" disabled>
						<fmt:message bundle="${msg}" key="create_book" />
					</button>

					<h6 style="color: blue">${message}</h6>

					<div class="row" style="margin-top: 0em">
						<div class="col s12">
							<div class="row" style="margin-bottom: 0em">
								<div class="input-field col s6">
									<p>
										<fmt:message bundle="${msg}" key="book_name" />
									</p>
									<input id="name" name="name" type="text" class="validate"
										pattern=".{1,45}" required onchange="valid()">
								</div>

								<div class="input-field col s6">
									<p>
										<fmt:message bundle="${msg}" key="book_author" />
									</p>
									<input id="author" name="author" type="text" class="validate"
										pattern=".{1,45}" required onchange="valid()">
								</div>
							</div>

							<div class="row">

								<div class="input-field col s4">
									<select name="type" id="type" class="browser-default"
										style="margin-top: 0.75em" required onchange="valid()">
										<option selected disabled value="0"><fmt:message
												bundle="${msg}" key="book_type" /></option>
										<c:forEach var="types" items="${book_type}">
											<option value="${types.getId()}">
												<fmt:message bundle="${msg}"
													key="book_type.${types.getName()}" />

											</option>
										</c:forEach>
									</select>
								</div>

								<div class="input-field col s4">
									<select name="meaning" id="meaning" class="browser-default"
										style="margin-top: 0.75em" required onchange="valid()">
										<option selected disabled value="0"><fmt:message
												bundle="${msg}" key="book_meaning" /></option>
										<c:forEach var="meanings" items="${book_meaning}">
											<option value="${meanings.getId()}">
												<fmt:message bundle="${msg}"
													key="book_meaning.${meanings.getName()}" />
											</option>
										</c:forEach>
									</select>
								</div>


								<div class="input-field col s4">
									<select name="language" id="language" class="browser-default"
										style="margin-top: 0.75em" required onchange="valid()">
										<option selected disabled value="0"><fmt:message
												bundle="${msg}" key="book_language" /></option>
										<c:forEach var="languages" items="${book_language}">
											<option value="${languages.getId()}">
												<fmt:message bundle="${msg}"
													key="book_language.${languages.getName()}" />
											</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
					</div>

					<input class="btn" type="file" name="image"
						placeholder="book cover screenshot..." onchange="readURL(this);"
						style="width: 60em"> <img id="photo"
						src="images/default.png">
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>