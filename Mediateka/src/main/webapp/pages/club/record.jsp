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
	<form id="myForm" action="record" method="post"
		enctype="multipart/form-data">

		<textarea name="text" id="text" placeholder="text"></textarea>
		<div>
			<input type="file" id="image" name="image" multiple
				onchange="readURL(this)" accept="image/*"> 
			<input
				type="file" id="video" name="video" onchange="readURL(this)"
				accept="video/*">
			<input
				type="file" id="audio" name="audio" onchange="readURL(this)"
				accept="audio/*">
				<audio id="sound1" preload="auto">
      <source id="sound1source" src="media/club/club1/audios/rnMA76nynMwF6p5v.mp3">      
      </audio>	
				 <label id="number"
				hidden="true">1</label>

		</div>
		<div id="selectedFiles"></div>

		<input type="submit" value="upload">
	</form>
</body>
</html>