<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<div id="modal7" class="modal">
	<div class="modal-content">
		<h4>Search user</h4>
		<div class="row">
			<form action="users" method="post"  id="searchUsers" class="col s12" onsubmit="return submitForm()">
			<p id = "message"></p>
				<div class="input-field">
					<input id="query" name="query" type="text" required autocomplete="off" onkeyup="clearMessage()"> <label
						for="search"><i class="mdi-action-search"></i></label> 
				</div>
			</form>
		</div>
	</div>

	<div class="modal-footer">
		<button 
			class=" modal-action  waves-effect waves-green btn-flat" onclick="submitForm()">Confirm</button>
	</div>
</div>