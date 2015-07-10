<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			<jsp:include page="activity_central.jsp" />
		</div>
	</div>
	
	<jsp:include page="../general/footer.jsp" />
</body>
</html>