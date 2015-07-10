<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/post_register" var="msg" />


<html>

<head>
<jsp:include page="../general/head.jsp" />
</head>

<body>
	<jsp:include page="../general/nav.jsp" />
	<div class="main">

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>

			<div style="margin-top: 10em; margin-right: 0em"
				class=" section white col s12 center">
				<div class="row offset-s4">
					<h1 class="col8 ">
						<fmt:message bundle="${msg }" key="thanks" />
					</h1>
				</div>
				<div class="row">
					<p style="font-size: 1.5em" class="col s12 center">
						<fmt:message bundle="${msg }" key="check_email" />
					</p>
				</div>

				<div class="row">
					<a style="font-size: 1.5em" class="col s12 center" href="index">
						<fmt:message bundle="${msg }" key="back" />
					</a>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />

</body>
</html>