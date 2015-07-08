<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/book_statistics" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../general/head.jsp" />
</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>

			<div class="container section white" style="width: 25%">
				<form>
					<h4>
						<fmt:message bundle="${msg}" key="year_picker" />
					</h4>
					<input type="number" name="year" min="2010" max="${currentYear }"
						value="${year }">

					<h4>
						<fmt:message bundle="${msg}" key="month_picker" />
					</h4>
					<select name="month" class="browser-default">
						<c:forEach begin="1" end="12" var="i">
							<c:if test="${i == month + 1 }">
								<option value="${i }" selected>
									<fmt:message bundle="${msg}" key="monthNo${i}" />
								</option>
							</c:if>

							<c:if test="${!(i == month + 1) }">
								<option value="${i }">
									<fmt:message bundle="${msg}" key="monthNo${i}" />
								</option>
							</c:if>

						</c:forEach>
					</select> <input type="submit" class="btn"
						value="<fmt:message bundle="${msg}" key="update_button" />">
				</form>
			</div>


			<div class="container section white" style="width: 97.5%">
				<table class="centered striped hoverable responsive-table">
					<caption style="font-size: 1.5em">
						<fmt:message bundle="${msg}" key="start_of_the_month_table" />
					</caption>
					<thead>

						<tr>
							<c:forEach var="group" items="${yearStatistics}">
								<td class="center" colspan="${group.value.size()}"><fmt:message
										bundle="${msg}" key="${group.key}" /></td>
							</c:forEach>
						</tr>

						<tr>
							<c:forEach var="group" items="${yearStatistics}">
								<c:forEach var="name" items="${group.value}">
									<td style="font-size: 0.75em" class="center"><fmt:message
											bundle="${msg}" key="${name.key}" /></td>
								</c:forEach>
							</c:forEach>
						</tr>

					</thead>

					<tbody>

						<tr>
							<c:forEach var="group" items="${yearStatistics}">
								<c:forEach var="name" items="${group.value}">
									<td>${name.value}</td>
								</c:forEach>
							</c:forEach>
						</tr>

					</tbody>
				</table>
			</div>










			<div class="container section white" style="width: 97.5%">
				<table class="centered striped  hoverable responsive-table">
					<caption style="font-size: 1.5em">
						<fmt:message bundle="${msg}" key="each_day_of_the_month_table" />
					</caption>

					<thead>

						<tr>
							<td><fmt:message bundle="${msg}" key="day" /></td>
							<c:forEach var="group" items="${yearStatistics}">
								<td class="center" colspan="${group.value.size()}"><fmt:message
										bundle="${msg}" key="${group.key}" /></td>
							</c:forEach>
						</tr>

						<tr>
							<td></td>
							<c:forEach var="group" items="${yearStatistics}">
								<c:forEach var="name" items="${group.value}">
									<td style="font-size: 0.75em" class="center"><fmt:message
											bundle="${msg}" key="${name.key}" /></td>
								</c:forEach>
							</c:forEach>
						</tr>

					</thead>

					<tbody>

						<c:forEach begin="1" end="${monthStatistics.size()}" var="day">

							<tr>
								<td>${day }</td>
								<c:forEach var="group" items="${monthStatistics[day-1]}">
									<c:forEach var="name" items="${group.value}">
										<td>${name.value}</td>
									</c:forEach>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>