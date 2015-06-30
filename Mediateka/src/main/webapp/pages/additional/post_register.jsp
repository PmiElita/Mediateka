<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

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
				class=" section white coll s12">
				<div class="row offset-s4">
					<h1 class="col8 ">Thanks!</h1>
				</div>
				<div class="row">
					<p style="font-size: 1.5em" class="col8">Check out your e-mail
						to confirm your registration.</p>
				</div>

				<div class="row">
					<a style="font-size: 1.5em" class="col8" href="index">Back to main page.</a>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />

</body>
</html>