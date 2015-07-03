<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin_events" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Events</title>
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
							<li class="tab col s3"><a href="#requested_events"
								style="margin-left: 5em"><fmt:message bundle="${msg}"
										key="requested_events" /></a></li>
							<li class="tab col s3"><a href="#all_events"><fmt:message
										bundle="${msg}" key="all_events" /></a></li>
						</ul>
					</div>
					<jsp:include page="../admin/admin_side_nav.jsp" />
				</div>

				<div id="requested_events">
					<c:choose>
						<c:when test="${!(requestedEvents==null)}">
							<ul class="collapsible" data-collapsible="accordion">
								<c:forEach items="${requestedEvents}" var="current"
									varStatus="status">
									<li id="eventNo${current.id}">
										<div class="collapsible-header">
											<a class="button" href="event?eventId=${current.id}"> <c:out
													value="${current.name}" /></a>
										</div>
										<div class="collapsible-body">
											<p>
												<c:out value="${current.description}" />
											</p>

											<p>
												<fmt:message bundle='${msg}' key='time' />
												:
												<c:out value="${datesRequested[status.index]}" />
											</p>

											<p>
												<fmt:message bundle='${msg}' key='type' />
												:
												<fmt:message bundle='${msg}' key='${current.type}' />
											</p>

											<p>
												<button class="btn waves-effect green titler" type="submit"
													name="action" style="margin-bottom: 3.5em"
													onclick="activate(${current.id});">
													<fmt:message bundle="${msg}" key="activate_button" />
													<i class="mdi-content-send right"></i>
												</button>

												<button class="btn waves-effect red titler" type="submit"
													name="action" style="margin-bottom: 3.5em"
													onclick="dismiss(${current.id} );">
													<fmt:message bundle="${msg}" key="dismiss_button" />
													<i class="mdi-content-send right"></i>
												</button>
											</p>
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<h2 align="center"><fmt:message bundle="${msg}" key="no_events" /></h2>
						</c:otherwise>
					</c:choose>
				</div>



				<div id="all_events">
					<c:choose>
						<c:when test="${!(allEvents==null)}">
							<ul class="collapsible" data-collapsible="accordion">
								<c:forEach items="${allEvents}" var="current" varStatus="status">
									<li id="eventNo${current.id}">
										<div class="collapsible-header">
											<a class="button" href="event?eventId=${current.id}"> <c:out
													value="${current.name}" /></a>
										</div>
										<div class="collapsible-body">
											<p>
												<c:out value="${current.description}" />
											</p>

											<p>
												<fmt:message bundle='${msg}' key='time' />
												:
												<c:out value="${datesAll[status.index]}" />
											</p>

											<p>
												<fmt:message bundle='${msg}' key='type' />
												:
												<fmt:message bundle='${msg}' key='${current.type}' />
											</p>

											<p>
												<button class="btn waves-effect red titler" type="submit"
													name="action" style="margin-bottom: 3.5em"
													onclick="dismiss(${current.id} );">
													<fmt:message bundle="${msg}" key="delete_button" />
													<i class="mdi-content-send right"></i>
												</button>
											</p>
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<h2 align="center"><fmt:message bundle="${msg}" key="no_events" /></h2>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	function activate(clubId){

		$.ajax({
			url : 'activateEvent',
			type: 'get',
			data : {
				id : clubId
			},
			success : function(data) {
				document.getElementById("eventNo" + clubId).innerHTML = "";					
				}
			}
		);
	}

	
	function dismiss(clubId){

		$.ajax({
			url : 'deleteEvent',
			type : 'get', 
			data : {
				id : clubId
			},
			success : function(data) {
				document.getElementById("eventNo" + clubId).innerHTML = "";					
				}
			}
		);
	}

	
		
		
	</script>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>