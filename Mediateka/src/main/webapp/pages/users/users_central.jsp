<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="u" uri="../../WEB-INF/tld/showUsers.tld"%> <!-- change path, remove one ../-->
    
<!DOCTYPE html >
<script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
<div class="main-activity">
		<h4>Search user</h4>
		<div class="row">
			<form  id="searchUsers" class="col s12" onsubmit="return reloadUsers()">
			<p id = "message"></p>
				<div class="input-field">
					<input id="query" name="query" type="text" required autocomplete="off" onkeyup="return reloadUsers()" value="${query}"> <label
						for="search"><i class="mdi-action-search"></i></label> 
				</div>
			</form>
			<button 
			class="col s2 offset-s10 waves-effect waves-green btn-flat" onclick="reloadUsers()">Confirm</button>
		</div>

	<div id="users">
	<p>${message }</p>
	<u:showUsers users="${users}"/>

</div>
</div>