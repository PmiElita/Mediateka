<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Google+ Authorization</title>
<script src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
<form id="form" action="googleLogin" method="post">
<input id="token" name="token" type="text" value="" hidden>
</form>
<script type="text/javascript">


	$( document ).ready(function() {
		var type = window.location.hash.substr(1);
		type=type.split("&")[0].split("=")[1];
		document.getElementById("token").value=type;
		document.getElementById("form").submit();
	});
	

</script>
</body>
</html>