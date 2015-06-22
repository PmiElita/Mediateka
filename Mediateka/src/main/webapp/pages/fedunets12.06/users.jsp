<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="u" uri="../../WEB-INF/tld/showUsers.tld"%> <!-- change path, remove one ../-->
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript"
	src="js/jquery-2.1.4.min.js"></script>
    <script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<link type="text/css" rel="stylesheet" href="css/autocomplete.css"
	media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Users</h1>
<div class="modal-content">
		<h4>Search user</h4>
		<div class="row">
			<form  id="searchUsers" class="col s12" onsubmit="return reloadUsers()">
			<p id = "message"></p>
				<div class="input-field">
					<input id="query" name="query" type="text" required autocomplete="off" onkeyup="return reloadUsers()" value="${query}"> <label
						for="search"><i class="mdi-action-search"></i></label> 
				</div>
			</form>
		</div>
	</div>

	<div class="modal-footer">
		<button 
			class=" modal-action  waves-effect waves-green btn-flat" onclick="reloadUsers()">Confirm</button>
	</div>
	<div id="users">
	<p>${message }</p>
	<u:showUsers users="${users}"/>

</div>
<footer>Заєбісь</footer>
</body>
</html>