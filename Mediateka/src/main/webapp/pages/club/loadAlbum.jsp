<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/viewImage.js"></script>
<title>Insert title here</title>
</head>
<body>

	<form id="myForm" action="loadAlbum" method="post"
		enctype="multipart/form-data">

		<input type="text" id = "name" name="name" placeholder="album name">
		Files: <input type="file" id="image" name="image" multiple
			onchange="readURL(this)" accept="image/*"><br /> 
			<label id="number"
			hidden="true">1</label>

		<div id="selectedFiles"></div>		

		<input type="submit" value="upload">
	</form>
</body>
<script>
	
</script>
</html>