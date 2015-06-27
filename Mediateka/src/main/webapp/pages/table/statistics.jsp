<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book statistics</title>
<jsp:include page="../general/head.jsp" />
</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<div class="container section white">
				<table>
					<thead>

						<!-- <tr>
						<c:forEach var="group" items="${statistics}">
							<td colspan="${group.value.size()}">${group.key}</td>
						</c:forEach>
					</tr>  -->

						<tr>
							<td>Name</td>
							<td>Number</td>
						</tr>


					</thead>

					<tbody>

						<c:forEach var="group" items="${statistics}">
							<c:forEach var="name" items="${group.value}">
								<tr>
									<td>${name.key}</td>
									<td>${name.value}</td>
								</tr>
							</c:forEach>
						</c:forEach>

						<!-- 
					<tr>
						<c:forEach var="group" items="${statistics}">
							<c:forEach var="name" items="${group.value}">
								<td>${name.key}</td>
							</c:forEach>
						</c:forEach>
					</tr>


					<tr>
						<c:forEach var="group" items="${statistics}">
							<c:forEach var="name" items="${group.value}">
								<td>${name.value}</td>
							</c:forEach>
						</c:forEach>
					</tr>
					 -->

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>