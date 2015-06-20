<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<head>
<jsp:include page="../general/head.jsp" />
</head>

<jsp:include page="../general/nav.jsp" />
<jsp:include page="../admin/admin_side_nav.jsp" />


<div id="creation_form">

	<div class="container">
		<form id="create_book" action="CreateBook" method="post"
			enctype="multipart/form-data">

			<button type="submit" class="btn waves-effect blue titler"
				style="margin-top: 2.5em">Create book</button>

			<h6 style="color: blue">${message}</h6>

			<div class="row" style="margin-top: 0em">
				<div class="col s12">
					<div class="row" style="margin-bottom: 0em">
						<div class="input-field col s6">
							<p>Book name</p>
							<input id="name" name="name" type="text" class="validate"
								pattern=".{1,45}" required>
						</div>

						<div class="input-field col s6">
							<p>Book author</p>
							<input id="author" name="author" type="text" class="validate"
								pattern=".{1,45}">
						</div>
					</div>

					<div class="row">

						<div class="input-field col s4">
							<select size="6" name="type" id="type" class="browser-default"
								style="margin-top: 0.75em">
								<option disabled>Book type..</option>
								<c:forEach var="types" items="${book_type}">
									<option value="${types.getId()}"><c:out
											value="${types.getName()}" /></option>
								</c:forEach>
							</select>
						</div>

						<div class="input-field col s4">
							<select size="6" name="meaning" id="meaning"
								class="browser-default" style="margin-top: 0.75em">
								<option disabled>Book meaning...</option>
								<c:forEach var="meanings" items="${book_meaning}">
									<option value="${meanings.getId()}"><c:out
											value="${meanings.getName()}" /></option>
								</c:forEach>
							</select>
						</div>


						<div class="input-field col s4">
							<select size="6" name="language" id="language"
								class="browser-default" style="margin-top: 0.75em">
								<option disabled>Book language...</option>
								<c:forEach var="languages" items="${book_language}">
									<option value="${languages.getId()}"><c:out
											value="${languages.getName()}" /></option>
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
<div style="margin-top: 1em"></div>

<jsp:include page="../general/footer.jsp" />