<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
<script src="js/search_book.js" type="text/javascript">
	
</script>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/search_book" var="msg" />

<div id="main" class=" row main-books">

	<c:forEach var="item" items="${bookItems }">
		<a href="bookPage">
			<div class="col s4 books">
				<div class="row" style="margin-bottom: 5px;">
					<label class="user_label ">${item.value}</label>
				</div>
				<img alt="Book name" src="${item.key}" width="100%">
			</div>
		</a>
	</c:forEach>
</div>
<div id="scroll">
	<c:if test="${haveMoreResults }">
		<div class="row main-books">
			<button class="  waves-effect waves-green btn-flat book-scroll"
				onclick="doScroll()">
				<fmt:message bundle="${msg}" key="show_more_results" />
			</button>
			<p id="index" hidden>${index }</p>
		</div>
	</c:if>
</div>
