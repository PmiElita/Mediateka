<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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

	</div>
</body>

</html>