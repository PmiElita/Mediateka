<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.State"%>
<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/book_update" var="msg" />



<head>
<jsp:include page="../general/head.jsp" />
<script type="text/javascript" src="js/search_book.js"></script>
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

				<div class="row">
					<button class="btn waves-effect titler col s3 " id="updateBook"
						onclick="document.getElementById('update_book').submit();">
						<fmt:message bundle="${msg }" key="update_book" />
					</button>
					<button class="btn waves-effect titler col s3 offset-s2"
						id="blockBook" onclick="blockBook(${book.id})">
						<c:choose>
							<c:when test="${book.state eq State.ACTIVE }">
								<fmt:message bundle="${msg }" key="block_book" />
							</c:when>
							<c:otherwise>
								<fmt:message bundle="${msg }" key="unblock_book" />
							</c:otherwise>
						</c:choose>
					</button>
					<button class="btn waves-effect titler col s3 " id="deleteBook"
						onclick="deleteBook(${book.id})">
						<fmt:message bundle="${msg }" key="delete_book" />
					</button>
				</div>
				<form id="update_book" action="UpdateBook" method="post"
					enctype="multipart/form-data" onsubmit ="return checkBookValidity();">

					<input name="id" value="${book.id }" hidden>

					<h6 style="color: blue">${message}</h6>

					<div class="row" style="margin-top: 0em">
						<div class="col s12">
							<div class="row" style="margin-bottom: 0em">
								<div class="input-field col s6">
									<p>
										<fmt:message bundle="${msg }" key="book_name" />
									</p>
									<input id="name" name="name" type="text" class="validate"
										pattern=".{1,45}" required value="${book.getName()}">
								</div>

								<div class="input-field col s6">
									<p>
										<fmt:message bundle="${msg }" key="book_author" />
									</p>
									<input id="author" name="author" type="text" class="validate"
										pattern=".{1,45}" required value="${book.getAuthor()}">
								</div>
							</div>
							<div class="row">
								<p>
									<fmt:message bundle="${msg }" key="book_description" />
								</p>
								<textarea class="materialize-textarea info" name="description"> <c:out
										value=" ${book.description }"></c:out></textarea>
							</div>
							<div class="row">

								<div class="input-field col s4">
									<select name="type" id="type" class="browser-default"
										style="margin-top: 0.75em" required>
										<option disabled><fmt:message bundle="${msg }"
												key="book_type" /></option>
										<c:forEach var="types" items="${book_type}">
											<c:choose>
												<c:when test="${types.getId()==book.getTypeId()}">
													<option value="${types.getId()}" selected>
														<fmt:message bundle='${msg }'
															key='book_type.${types.getName()}' />
													</option>
												</c:when>
												<c:otherwise>
													<option value="${types.getId()}">
														<fmt:message bundle="${msg }"
															key="book_type.${types.getName()}" />
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>

								<div class="input-field col s4">
									<select name="meaning" id="meaning" class="browser-default"
										style="margin-top: 0.75em" required>
										<option disabled><fmt:message bundle="${msg }"
												key="book_meaning" /></option>
										<c:forEach var="meanings" items="${book_meaning}">
											<c:choose>
												<c:when test="${meanings.getId()==book.getMeaningId()}">
													<option value="${meanings.getId()}" selected>
														<fmt:message bundle="${msg }"
															key="book_meaning.${meanings.getName()}" />
													</option>
												</c:when>
												<c:otherwise>
													<option value="${meanings.getId()}">
														<fmt:message bundle="${msg }"
															key="book_meaning.${meanings.getName()}" />
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>


								<div class="input-field col s4">
									<select name="language" id="language" class="browser-default"
										style="margin-top: 0.75em" required>
										<option disabled><fmt:message bundle="${msg }"
												key="book_language" /></option>
										<c:forEach var="languages" items="${book_language}">
											<c:choose>
												<c:when test="${languages.getId()==book.getLanguageId()}">
													<option value="${languages.getId()}" selected>
														<fmt:message bundle="${msg }"
															key="book_language.${languages.getName()}" />
													</option>
												</c:when>
												<c:otherwise>
													<option value="${languages.getId()}">
														<fmt:message bundle="${msg }"
															key="book_language.${languages.getName()}" />
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="row">
							<div class="col s3">
								<div class="file-field input-field">
									<input class="file-path validate" type="hidden"/>
									<div class="btn">
										<span>Book titler</span> <input type="file" name="image"
											onchange="readURL(this);" />
									</div>
								</div>
							</div>
							<div class="col s6">
								<img style="height:200px" id="photo" src="${imagePath }">
							</div>

							<div class="col s3">
									<p>
											<fmt:message bundle="${msg}" key="library_book_id" />
										</p>
										<input id="libraryBookId" name="libraryBookId" type="text" class="validate"
											pattern=".{1,100}" required onchange="checkLibraryBookId(this)" value="${book.libraryBookId }">
											<input id="oldLibraryBookId"  hidden value="${book.libraryBookId }">
							</div>
						</div>
							
						</div>
					</div>
				</form>
			</div>
		</div>
<script type="text/javascript">
function setInvalid(element){
	Materialize.toast('<fmt:message bundle="${msg}" key="book_id_is_in_use" />', 2000);
	element.setAttribute('class', 'validate invalid');
}
</script>
	</div>
</div>
<jsp:include page="../general/footer.jsp" />