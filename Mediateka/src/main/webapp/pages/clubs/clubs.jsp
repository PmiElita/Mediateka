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
<jsp:include page="create_club.jsp" />

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
					<li class="tab col s3"><a href="#my_clubs">My clubs</a></li>
					<li class="tab col s3"><a href="#all_clubs">All clubs</a></li>
					<li class="tab col s3"><a href="" data-target="modal8" class="modal-trigger waves-effect">Create club</a></li>
				</ul>
			</div>
			<div id="my_clubs"><jsp:include page="my_clubs.jsp" /></div>
			<div id="all_clubs"><jsp:include page="all_clubs.jsp" /></div>
		</div>

	</div>

	<jsp:include page="../general/footer.jsp" />
	<jsp:include page="../general/script.jsp" />
</body>
</html>