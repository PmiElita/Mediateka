<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="../../WEB-INF/tld/showUsers.tld"%>
<!-- change path, remove one ../-->



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/users" var="msg" />




<!DOCTYPE html >
<script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
	
	<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	z-index: 1000;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>
	
	
<div class="main-activity container white section">
	<h4>
		<fmt:message bundle="${msg}" key="searchUser" />
	</h4>
	<div class="row section white">
		<form id="searchUsers" class="col s12" onsubmit="return reloadUsers();">
			<p id="message"></p>
			<div class="input-field">
				<input id="query" name="query" type="text" required
					autocomplete="off" onkeyup="return reloadUsers();" value="${query}">
				<label for="search"><i class="mdi-action-search"></i></label>
			</div>
		</form>
		<button class="col s2 offset-s10 waves-effect waves-green btn-flat"
			onclick="reloadUsers();">
			<fmt:message bundle="${msg}" key="confirm" />
		</button>
	</div>

	<div id="users" class="white">
		<p>${message }</p>
		<jsp:include page="user_list.jsp"/>
		

	</div>
</div>