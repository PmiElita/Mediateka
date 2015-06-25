<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="../../WEB-INF/tld/showUsers.tld"%>
<!-- change path, remove one ../-->
<!DOCTYPE html >
<html>
<head>
<jsp:include page="../general/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<jsp:include page="users_central.jsp" />
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>