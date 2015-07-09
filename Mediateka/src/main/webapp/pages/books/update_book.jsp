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
				
                <div class = "row">
                	<button  class="btn waves-effect titler col s3 " id = "updateBook"
						 onclick ="document.getElementById('update_book').submit();">Update book</button>
						<button  class="btn waves-effect titler col s3 offset-s2" id="blockBook"
						 onclick ="blockBook(${book.id})">
						 <c:choose>
						 <c:when test="${book.state eq State.ACTIVE }">
						    Block 
						 </c:when>
						 <c:otherwise>
						 	Unblock
						 </c:otherwise>
						 </c:choose>
						 book</button>
						 <button  class="btn waves-effect titler col s3 " id="deleteBook"
						 onclick ="deleteBook(${book.id})">Delete book</button>
                </div>
				<form id="update_book" action="UpdateBook" method="post"
					enctype="multipart/form-data">
                    
				    <input name="id" value="${book.id }" hidden>

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
                             <p>Description</p>
                             <textarea class="materialize-textarea info" name = "description"> <c:out value=" ${book.description }"></c:out></textarea>
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
								style="width: 45em"> <img id="photo" src="${imagePath}" style="width:45%;">

							
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>
<jsp:include page="../general/footer.jsp" />