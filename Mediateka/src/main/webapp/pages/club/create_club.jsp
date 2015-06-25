<<<<<<< .mine
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
=======
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
>>>>>>> .r289
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="main">
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

<<<<<<< .mine
	${message}	
	<form id="createClub" action="createClub" method="post"></form>
	<div>
		<div class="input-field col s5">
			<input name="name" id="name" type="text" form="createClub" />
			Club name
		</div>
		<div class="input-field col s5">
			<i class="mdi-action-account-circle prefix"></i>
			<textarea name="description" id="description " cols="40"
				rows="5" form="createClub"></textarea>
			<label class="active" for="description">Description</label>
		</div>	
		<input type="submit" id="subbut" name="subbut" value="save"
			form="createClub">
=======
			<input type="submit" value="save"
				onclick="javaScript: performAjaxSubmit ()">
>>>>>>> .r289

<<<<<<< .mine
	</div>
</body>

=======
		</form>
		<div class="main">
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
>>>>>>> .r289
</html>