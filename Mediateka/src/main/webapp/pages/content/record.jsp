<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	z-index: 1000;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>



<style type="text/css">
.vjs-default-skin .vjs-control-bar {
	font-size: 75%
}
</style>


<script src="js/record.js"></script>
<div class="collapsible-header" style="font-size: 2em">+</div>
<div class="collapsible-body">
	<div id="record_form"><jsp:include page="record_form.jsp" /></div>
</div>

<!-- <form id="clubForm" action="record" method="post"></form> -->
<div id="record_central">
	<div id="recordList" style="margin-top: 0em"><jsp:include
			page="record_central.jsp"></jsp:include></div>
</div>
