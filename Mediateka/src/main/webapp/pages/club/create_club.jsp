<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form id="createClub" action="createClub" method="post"></form>
	<form id="uploadClubAvaFile" action="uploadClubAvaFile" method="post"
		enctype="multipart/form-data"></form>
	<div>
		<div class="input-field col s5">
			<input name="club_name" id="club_name" type="text" form="createClub" />
			Club name
		</div>
		<div class="input-field col s5">
			<i class="mdi-action-account-circle prefix"></i>
			<textarea name="club_description" id="club_description " cols="40"
				rows="5" form="createClub"></textarea>
			<label class="active" for="club_description">Description</label>
		</div>
		<div class="input-field col s5">
			<input type="file" name="dataFile" id="fileChooser"
				form="uploadClubAvaFile"/>
		</div>


		<input type="submit" value="save" form = "createClub" onchange="document.getElementById('uploadClubAvaFile').submit();">

	</div>
</body>

</html>