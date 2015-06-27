<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:useBean id="consts" class="com.mediateka.util.RegExps"
	scope="session" />

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/reset_password_form" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../general/head.jsp" />
</head>
<body>

	<div class=main>
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<div class="container">
				<c:if test="${notification ne null }">
					<fmt:message bundle="${msg}" key="${notification}" />
				</c:if>

			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>