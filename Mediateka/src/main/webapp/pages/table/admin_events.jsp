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
								style="margin-left: 5em">Requested events</a></li>
							<li class="tab col s3"><a href="#all_events">All events</a></li>
						</ul>
					</div>
					<c:if test="${userRole eq Role.ADMIN}">
						<jsp:include page="../admin/admin_side_nav.jsp" />
					</c:if>

					<c:if test="${userRole eq Role.USER}">
						<jsp:include page="../user/user_side_nav.jsp" />
					</c:if>


				</div>
				<div id="requested_events">
					<ul class="collapsible" data-collapsible="accordion">
						<c:forEach items="${requestedEvents}" var="current">
							<li id="eventNo${current.id}">
								<div class="collapsible-header">
									<c:out value="${current.name}" />
								</div>
								<div class="collapsible-body">
									<p>
										<c:out value="${current.description}" />
									</p>

									<p>
										<fmt:message bundle='${msg}' key='from' />
										: ${current.dateFrom}"
									</p>

									<p>
										<fmt:message bundle='${msg}' key='till' />
										: ${current.dateTill}"
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

				</div>



				<div id="all_events">
					<c:forEach items="${allEvents}" var="current">
					${current.name } 
				</c:forEach>
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