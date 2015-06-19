<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.mediateka.model.enums.State"%>

<div id="modal12" class="modal">
	<div class="modal-content">
		<div id="creation_form">

			<div class="container">
				${message}

				<form id="update_book" action="UpdateBook" method="post"
					enctype="multipart/form-data">

					<button type="submit" class="btn waves-effect blue titler"
						style="margin-top: -7em">Update book</button>

					<div class="row" style="margin-top: 0em">
						<div class="col s12">
							<div class="row" style="margin-bottom: 0em">
								<div class="input-field col s6">
									<p>Book name</p>
									<input id="name" name="name" type="text" class="validate"
										pattern=".{1,45}" value="${book.getName()}">
								</div>

								<div class="input-field col s6">
									<p>Book author</p>
									<input id="author" name="author" type="text" class="validate"
										pattern=".{1,45}" value="${book.getAuthor()}">
								</div>
							</div>

							<div class="row">

								<div class="input-field col s4">
									<select size="6" name="type" id="type" class="browser-default"
										style="margin-top: 0.75em">
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
									<select size="6" name="meaning" id="meaning"
										class="browser-default" style="margin-top: 0.75em">
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
									<select size="6" name="language" id="language"
										class="browser-default" style="margin-top: 0.75em">
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

							<div title="Book state" style="margin-top:1em" >
								<div style="display:inline-block; margin-right:10em">
									<c:choose>
										<c:when test="${book.getState()==State.ACTIVE}">
											<input type="radio" name="state" value="ACTIVE" checked>active
   				   </c:when>
										<c:otherwise>
											<input type="radio" name="state" value="ACTIVE">active
   				   </c:otherwise>
									</c:choose>

								</div>
								<div style="display:inline-block; margin-right:10em">
									<c:choose>
										<c:when test="${book.getState()==State.BLOCKED}">
											<input type="radio" name="state" value="BLOCKED" checked>blocked
   				   </c:when>
										<c:otherwise>
											<input type="radio" name="state" value="BLOCKED">blocked
   				   </c:otherwise>
									</c:choose>
								</div>
								<div style="display:inline-block">
									<c:choose>
										<c:when test="${book.getState()==State.DELETED}">
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