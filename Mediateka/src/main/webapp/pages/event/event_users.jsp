<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.mediateka.model.enums.Role"%>
<%@page import="java.util.List"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/event_users" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message bundle="${msg}" key="title" /></title>
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

				<div class="row" style="margin-top: -1em">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab col s3"><a href="#active_users"
								style="margin-left: 5em"><fmt:message bundle="${msg}"
										key="active_users" /></a></li>
							<c:if test="${!(creator==null)}">
								<li class="tab col s3"><a href="#blocked_users"><fmt:message
											bundle="${msg}" key="blocked_users" /></a></li>
							</c:if>
						</ul>
					</div>
					<c:if test="${userRole eq Role.ADMIN}">
						<jsp:include page="../admin/admin_side_nav.jsp" />
					</c:if>
					<c:if test="${userRole eq Role.USER}">
						<jsp:include page="../user/user_side_nav.jsp" />
					</c:if>
				</div>

				<div id="active_users">
					<c:choose>
						<c:when test="${!(activeUsers == null)}">
							<ul class="collapsible" data-collapsible="accordion">
								<c:forEach items="${activeUsers}" var="current"
									varStatus="status">
									<li id="userNo${current.id}">
										<div class="collapsible-header">
											<a class="button" href="user?userId=${current.id}"> <c:out
													value="${current.firstName} ${current.lastName} ${current.middleName}" /></a>
										</div> <c:if test="${!(creator==null)}">
										<c:if test="${!(userId==current.id)}">
											<div class="collapsible-body">
												<p>

													<button class="btn waves-effect green titler" type="submit"
														name="action" style="margin-bottom: 3.5em"
														onclick="block(${current.id},${eventId});">
														<fmt:message bundle="${msg}" key="block" />
														<i class="mdi-content-send right"></i>
													</button>

													<button class="btn waves-effect red titler" type="submit"
														name="action" style="margin-bottom: 3.5em"
														onclick="deleted(${current.id},${eventId});">
														<fmt:message bundle="${msg}" key="delete" />
														<i class="mdi-content-send right"></i>
													</button>

												</p>
											</div></c:if>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<h2 align="center">
								<fmt:message bundle="${msg}" key="no_users" />
							</h2>
						</c:otherwise>
					</c:choose>
				</div>


				<c:if test="${!(creator == null) }">

					<div id="blocked_users">
						<c:choose>
							<c:when test="${!(blockedUsers == null)}">
								<ul class="collapsible" data-collapsible="accordion">
									<c:forEach items="${blockedUsers}" var="current"
										varStatus="status">
										<li id="userNo${current.id}">
											<div class="collapsible-header">
												<a class="button" href="user?userId=${current.id}"> <c:out
														value="${current.firstName} ${current.lastName} ${current.middleName}" /></a>
											</div> <c:if test="${!(creator==null)}">
												<div class="collapsible-body">
													<p>

														<button class="btn waves-effect red titler" type="submit"
															name="action" style="margin-bottom: 3.5em"
															onclick="activate(${current.id},${eventId});">
															<fmt:message bundle="${msg}" key="activate" />
															<i class="mdi-content-send right"></i>
														</button>

													</p>
												</div>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</c:when>
							<c:otherwise>
								<h2 align="center">
									<fmt:message bundle="${msg}" key="no_users" />
								</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>

			</div>
		</div>
	</div>

	<script type="text/javascript">
	function activate(userId,eventId){

		$.ajax({
			url : 'activateEventUser',
			type: 'get',
			data : {
				id : userId,
				eid : eventId
			},
			success : function(data) {
				document.getElementById("userNo" + userId).innerHTML = "";					
				}
			}
		);
	}

	function deleted(userId,eventId){

		$.ajax({
			url : 'deleteEventUser',
			type : 'get', 
			data : {
				id : userId,
				eid : eventId
			},
			success : function(data) {
				document.getElementById("userNo" + userId).innerHTML = "";					
				}
			}
		);
	}
	
	function block(userId,eventId){

		$.ajax({
			url : 'blockEventUser',
			type : 'get', 
			data : {
				id : userId,
				eid : eventId
			},
			success : function(data) {
				document.getElementById("userNo" + userId).innerHTML = "";					
				}
			}
		);
	}

	
		
		
	</script>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>