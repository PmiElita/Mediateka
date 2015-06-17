<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form id="myForm" action="loadAlbum" method="post" enctype="multipart/form-data">

        Files: <input type="file" id="files" name="files" multiple><br/>

        <div id="selectedFiles"></div>       

        <input type="submit" value="upload">
	</form>

</html>