<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/page404" var="msg" />

<html>
<head>
<link type="text/css" rel="stylesheet" href="css/materialize.css"
	media="screen,projection" />
<jsp:include page="general/head.jsp" />


<script src="http://yui.yahooapis.com/3.17.2/build/yui/yui-min.js"></script>
</head>

<style>
.book-cover-t {
	color: white;
	position: relative;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>

<body>
	${message}
	<div class="main">

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<div class="container">
				<div class="row" style="font-size: 1.5em; margin-top: 0.5em">
					<a href="index"
						style="background: black; color: white; border-radius: 10%;">
						<fmt:message bundle="${msg }" key="back_to_main_page" />
					</a>
				</div>
				<div style="width: 25em; margin: auto">
					<h1 class="book-cover-t">
						<fmt:message bundle="${msg }" key="error404" />
					</h1>
					<img src="images/error.png" />
					<h4 style="margin-left: 0.5em" class="book-cover-t">
						<fmt:message bundle="${msg }" key="page_not_found" />
					</h4>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="general/footer.jsp" />

</body>
</html>
