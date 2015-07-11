<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/index" var="msg" scope="session" />
<link href="css/map.css" rel="stylesheet" />
<link href="css/oleh_style.css" rel="stylesheet" />
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&language=en"></script>


<div class="row indexmapchat">
	<div id="map-canvas"></div>
	<span class="map-span"><fmt:message bundle="${msg }"
			key="map.span" /> </span>

</div>

<script src="js/index-map.js"></script>


</body>
</html>