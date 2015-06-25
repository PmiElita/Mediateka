<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="js/jquery.autocomplete.js"></script>
<script src="js/myautoc.js"></script>
<div id="modal14" class="modal">
	<div class="modal-content">
		<h4>Search book</h4>
		<div class="row">
			<form class="col s12" id="searchBooks"  action="books" method="post">
			<p id="message"></p>
				<div class="input-field">
					<input id="bookQuery" name="search" type="text" autocomplete="off" required> <label
						for="search"><i class="mdi-action-search" onkeyup="clearMessage();"></i></label>
				</div>
			</form>
		</div>
	</div>

	<div class="modal-footer">
		<button
			class=" modal-action waves-effect waves-green btn-flat" onclick="submitBookForm();">Confirm</button>
	</div>
</div>