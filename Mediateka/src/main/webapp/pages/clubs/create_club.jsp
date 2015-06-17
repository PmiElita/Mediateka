<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="modal8" class="modal">
	<div class="modal-content">
		<form id="createClub" action="createClub" method="post">
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

			<button type="submit" id="subbut" name="subbut" form="createClub">Create</button>
		</form>
	</div>
</div>