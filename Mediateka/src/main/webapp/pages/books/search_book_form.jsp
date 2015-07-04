<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/search_book" var="msg" />

<script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
<div class="row main-activity section white container"
	style="margin-top: 1em">
	<div class="modal-content div-margin">
		<h4>
			<fmt:message bundle="${msg}" key="search_book" />
		</h4>
		<div class="row">
			<form class="col s12" id="searchBook" action="searchBooks"
				method="post" onsubmit="return submitBookForm()">
				<p id="message"></p>
				<div class="input-field">
					<input id="bookQuery" name="search" type="text" autocomplete="off">
					<label for="search"><i class="mdi-action-search"></i></label>
				</div>
			</form>
		</div>
	</div>

	<div class="row">
		<button class=" waves-effect waves-white btn"
			style="float: right;" onclick="submitBookForm();">
			<fmt:message bundle="${msg}" key="confirm" />
		</button>
	</div>
	<div class="row div-margin">
		<ul class="collapsible" data-collapsible="accordion">
			<li>
				<div class="collapsible-header" onclick="changeIsOpen()">
					<i id="collapse-icon" class="mdi-hardware-keyboard-arrow-down "></i>
					<fmt:message bundle="${msg}" key="more_options" />
				</div>
				<div class="collapsible-body">
					<div class="row" style="margin-bottom: 5px;">
						<label class="col s3 user_label select_margin"><fmt:message
								bundle="${msg}" key="book_type" /></label> <label
							class="col s3 user_label select_margin"><fmt:message
								bundle="${msg}" key="book_meaning" /></label> <label
							class="col s3 user_label select_margin"><fmt:message
								bundle="${msg}" key="book_language" /></label>
					</div>
					<div class="row">
						<select id="type" class="browser-default col s3 select_margin">
							<option value="" selected><fmt:message bundle="${msg}"
									key="any_book_type" /></option>
							<c:forEach var="type" items="${bookTypes }">
								<option value="${type.id }"><fmt:message
										bundle="${msg}" key="book_type.${type.name }" /></option>
							</c:forEach>
						</select> <select id="meaning" class="browser-default col s3 select_margin">
							<option value="" selected><fmt:message bundle="${msg}"
									key="any_book_meaning" /></option>
							<c:forEach var="meaning" items="${bookMeanings }">
								<option value="${meaning.id }"><fmt:message
										bundle="${msg}" key="book_meaning.${meaning.name }" /></option>
							</c:forEach>
						</select> <select id="language" class="browser-default col s3 ">
							<option value="" selected><fmt:message bundle="${msg}"
									key="any_book_language" /></option>
							<c:forEach var="language" items="${bookLanguages }">
								<option value="${language.id }"><fmt:message
										bundle="${msg}" key="book_language.${language.name }" /></option>
							</c:forEach>
						</select>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>