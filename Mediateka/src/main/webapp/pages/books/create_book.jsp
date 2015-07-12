<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_book" var="msg" />

<%@page import="com.mediateka.model.enums.Role"%>


<html>
<head>
<jsp:include page="../general/head.jsp" />
<script src="js/bookCreation.js"></script>
<script src="js/jquery.autocomplete.js"></script>
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<jsp:include page="../admin/admin_side_nav.jsp" />

			<div id="creation_form">

				<div class="container section white">
					<form id="create_book" onsubmit="return valid();"
						action="CreateBook" method="post" enctype="multipart/form-data">

						<button type="submit" class="btn waves-effect titler" id="submit"
							style="margin-top: 2.5em; margin-left:1.25em">
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
											pattern=".{1,100}" required autocomplete="off">
									</div>

									<div class="input-field col s6">
										<p>
											<fmt:message bundle="${msg}" key="book_author" />
										</p>
										<input id="author" name="author" type="text" class="validate"
											pattern=".{1,100}" required>
									</div>
									<div class = "col s12">
									<p>
											<fmt:message bundle="${msg}" key="description" />
										</p>
									 <textarea class="materialize-textarea info" name = "description"> </textarea>
									</div>
								</div>

								<div class="row">

									<div class="input-field col s4">
										<select name="type" id="type" class="browser-default"
											style="margin-top: 0.75em" required>
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
											style="margin-top: 0.75em" required>
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
											style="margin-top: 0.75em" required>
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

						<div class="row">
							<div class="col s3">
								<div class="file-field input-field">
									<input class="file-path validate" type="hidden"/>
									<div class="btn">
										<span><fmt:message bundle="${msg }" key="book_cover" /></span> <input type="file" name="image"
											onchange="readURL(this);" />
									</div>
								</div>
							</div>
							<div class="col s6">
								<img style="height:200px" id="photo" src="images/book_title.jpg">
							</div>
							
							<div class="col s3">
									<p>
											<fmt:message bundle="${msg}" key="library_book_id" />
										</p>
										<input id="libraryBookId" name="libraryBookId" type="text" class="validate"
											pattern=".{1,100}" required onchange="checkLibraryBookId(this)">
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
function setInvalid(element){
	element.setCustomValidity('<fmt:message bundle="${msg}" key="book_id_is_in_use" />');
	element.setAttribute('class', 'validate invalid');
}
</script>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>