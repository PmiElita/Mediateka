<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="createClub" method="post">
		<div class="input-field col s5">
			<i class="mdi-action-account-circle prefix"></i> <input
				name="club_name" id="club_name" class="validate" type="text">
			<label class="active" for="club_name">Club name</label>
		</div>
		<div class="input-field col s5">
			<i class="mdi-action-account-circle prefix"></i>
			<textarea name="club_description" cols="40" rows="5"></textarea>
			<label class="active" for="club_description">Description</label>
		</div>
		<div class="input-field col s5">
			<input type='file' name="avaClub" id="avaClub"
				onchange="readURL(this);" /> <img id="ava" src="#"
				alt="aclub avatar" />
		</div>

		<input type="submit" value="save"
			onclick="javaScript: performAjaxSubmit ()">

	</form>

</body>
<script>
	function performAjaxSubmit() {
		var sampleFile = document.getElementById("file").files[0];
		var formdata = new FormData();
		formdata.append("sampleFile", sampleFile);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "http://127.0.0.1:8080/Mediateca/Upload", true);
		xhr.send(formdata);
		xhr.onload = function(e) {
			if (this.status == 200) {
				alert(this.responseText);
			}
		};
	}
</script>
</html>