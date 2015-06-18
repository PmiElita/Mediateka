<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

		<input type="text" placeholder="album name">
		Files: <input type="file" id="images" name="images" multiple
			onchange="readURL(this)"><br /> 
			<label id="number"
			hidden="true">1</label>

		<div id="selectedFiles"></div>

		<input type="submit" value="upload">
	</form>
</body>
<script>
	
</script>
</html>