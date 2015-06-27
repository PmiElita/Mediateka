<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.State"%>
<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<head>
<jsp:include page="../general/head.jsp" />
</head>
<div class="main">
	<jsp:include page="../general/nav.jsp" />

	<div class="parallax-container my-parallax">
		<div class="parallax">
			<img src="images/parallax1.jpg">
		</div>
		<jsp:include page="../admin/admin_side_nav.jsp" />

		<div id="creation_form">

			<div class="container section white">
				${message}

				<form id="update_book" action="UpdateBook" method="post"
					enctype="multipart/form-data">

					<button type="submit" class="btn waves-effect titler"
						style="margin-top: 2.5em">Update book</button>

					<h6 style="color: blue">${message}</h6>

					<div class="row" style="margin-top: 0em">
						<div class="col s12">
							<div class="row" style="margin-bottom: 0em">
								<div class="input-field col s6">
									<p>Book name</p>
									<input id="name" name="name" type="text" class="validate"
										pattern=".{1,45}" required value="${book.getName()}">
								</div>

								<div class="input-field col s6">
									<p>Book author</p>
									<input id="author" name="author" type="text" class="validate"
										pattern=".{1,45}" required value="${book.getAuthor()}">
								</div>
							</div>

							<div class="row">

								<div class="input-field col s4">
									<select name="type" id="type" class="browser-default"
										style="margin-top: 0.75em" required>
										<option disabled>Book type..</option>
										<c:forEach var="types" items="${book_type}">
											<c:choose>
												<c:when test="${types.getId()==book.getTypeId()}">
													<option value="${types.getId()}" selected><c:out
															value="${types.getName()}" /></option>
												</c:when>
												<c:otherwise>
													<option value="${types.getId()}"><c:out
															value="${types.getName()}" /></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>

								<div class="input-field col s4">
									<select name="meaning" id="meaning" class="browser-default"
										style="margin-top: 0.75em" required>
										<option disabled>Book meaning...</option>
										<c:forEach var="meanings" items="${book_meaning}">
											<c:choose>
												<c:when test="${meanings.getId()==book.getMeaningId()}">
													<option value="${meanings.getId()}" selected><c:out
															value="${meanings.getName()}" /></option>
												</c:when>
												<c:otherwise>
													<option value="${meanings.getId()}"><c:out
															value="${meanings.getName()}" /></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>


								<div class="input-field col s4">
									<select name="language" id="language" class="browser-default"
										style="margin-top: 0.75em" required>
										<option disabled>Book language...</option>
										<c:forEach var="languages" items="${book_language}">
											<c:choose>
												<c:when test="${languages.getId()==book.getLanguageId()}">
													<option value="${languages.getId()}" selected><c:out
															value="${languages.getName()}" /></option>
												</c:when>
												<c:otherwise>
													<option value="${languages.getId()}"><c:out
															value="${languages.getName()}" /></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>

							<input class="btn" type="file" name="image"
								value="${book.getMediaId()}"
								placeholder="book cover screenshot..." onchange="readURL(this);"
								style="width: 45em"> <img id="photo" src="${imagePath}">

							<div title="Book state" style="margin-top: 1em">
								<div style="display: inline-block; margin-right: 10em">
									<c:choose>
										<c:when test="${book.state eq State.ACTIVE}">
											<input type="radio" name="state" value="ACTIVE" checked>active
   				   </c:when>
										<c:otherwise>
											<input type="radio" name="state" value="ACTIVE">active
   				   </c:otherwise>
									</c:choose>

								</div>
								<div style="display: inline-block; margin-right: 10em">
									<c:choose>
										<c:when test="${book.state eq State.BLOCKED}">
											<input type="radio" name="state" value="BLOCKED" checked>blocked
   				   </c:when>
										<c:otherwise>
											<input type="radio" name="state" value="BLOCKED">blocked
   				   </c:otherwise>
									</c:choose>
								</div>
								<div style="display: inline-block; margin-right: 10em">
									<c:choose>
										<c:when test="${book.state eq State.DELETED}">
											<input type="radio" name="state" value="DELETED" checked>deleted
   				   </c:when>
										<c:otherwise>
											<input type="radio" name="state" value="DELETED">deleted
   				   </c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>
<jsp:include page="../general/footer.jsp" />