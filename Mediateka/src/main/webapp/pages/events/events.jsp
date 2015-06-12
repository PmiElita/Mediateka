<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="org.apache.log4j.Logger"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>

<jsp:include page="../general/head.jsp" />

</head>


<body>
	<%
		Logger LOG = Logger.getLogger(this.getClass().getName());
	%>
	<%
		LOG.warn("There's a new man in Town!");
	%>


	<jsp:include page="../general/nav.jsp" />
	
	<div class="parallax-container my-parallax">
		<div class="parallax">
			<img src="images/parallax1.jpg">
		</div>

		<div class="row" style="margin-top:-1em">
			<div class="col s12">
				<ul class="tabs">
					<li class="tab col s3"><a href="#my_events">My events</a></li>
					<li class="tab col s3"><a href="#all_events">All events</a></li>
				</ul>
			</div>
			<div id="my_events"><jsp:include page="my_events.jsp" /></div>
			<div id="all_events"><jsp:include page="all_events.jsp" /></div>
		</div>

	</div>

	<jsp:include page="../general/footer.jsp" />
	<jsp:include page="../general/script.jsp" />
</body>
</html>