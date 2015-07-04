<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/user_modification_form" var="msg" />

<jsp:include page="response_form.jsp"/>

<footer class="page-footer">
	<div class="footer-copyright">
		<div class="container row" style="padding: 0; margin: 0">
			<div class="col s6 offset-s3">&copy; 2015 Copyright TEAM</div>
			<a href="" data-target="modal25" class="modal-trigger waves-effect">send response</a>
		</div>

	</div>
</footer>