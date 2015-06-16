<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="modal8" class="modal">
	<div class="modal-content">
		<form id="createClub" action="createClub" method="post"
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
					form="createClub">
			</div>
			<input type="submit" id="subbut" name="subbut" value="save"
				form="createClub">

		</div>
	</div>
</div>