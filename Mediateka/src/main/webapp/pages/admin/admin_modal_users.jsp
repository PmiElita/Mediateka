<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin" var="msg" />




<script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<div id="modal7" class="modal">
	<div class="modal-content">
		<h4>
			<fmt:message bundle="${msg}" key="searchUser" />
		</h4>
		<div class="row">
			<form action="users" method="post" id="searchUsers" class="col s12"
				onsubmit="return submitForm()">
				<p id="message"></p>
				<div class="input-field">
					<input id="query" name="query" type="text" required
						autocomplete="off" onkeyup="clearMessage()"> <label
						for="search"><i class="mdi-action-search"></i></label>
				</div>
			</form>
		</div>
	</div>

	<div class="modal-footer">
		<button class=" modal-action  waves-effect waves-green btn-flat"
			onclick="submitForm()">
			<fmt:message bundle="${msg}" key="confirm" />
		</button>
	</div>
</div>