<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form id="editClub" action="editClub" name="editClub" method="post"
		enctype="multipart/form-data"></form>
	<div>
		<div class="input-field col s5">
			<input name="club_name" id="club_name" type="text" value="${club.name}" form="editClub" />
			Club name
		</div>
		<div class="input-field col s5">
			<i class="mdi-action-account-circle prefix"></i>
			<textarea name="club_description" id="club_description " cols="40"
				rows="5" form="editClub">${club.description}</textarea>			
		</div>
		<img id ="photo" src = "${clubAva}">
		<div class="input-field col s5">
			<input type="file" name="dataFile" id=dataFile form="editClub" onchange="readURL(this);">
		</div>		
		<input type="submit" id="subbut" name="subbut" value="save"
			form="editClub">

	</div>

</body>
<script>
function readURL(input) {	
	  if (input.files && input.files[0]) {		  
	    var reader = new FileReader();
	    reader.onload = function (e) {
	      $('#photo')
	        .attr('src', e.target.result)
	        .width(150)
	        .height(200);
	    };
	    reader.readAsDataURL(input.files[0]);
	  }
	}
 </script>
</html>