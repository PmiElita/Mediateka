<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="modal20" class="modal">
	<div class="modal-content">

		<div class="container">

			<h3 class="center">Create club</h3>
			${message}
			<form id="createClub" action="createClub" method="post"></form>
			<div>
				<div class="row">
					Club name:<input type="text" id="name" name="name" required
						pattern=".{1,45}" form="createClub"><br>
				</div>

				<div class="row">
					Description:
					<textarea name="description" id="description" pattern=".{0,255}"
						class="materialize-textarea" form="createClub"></textarea>
					<input type="submit" id="subbut" name="subbut" value="Create club"
						class="btn" form="createClub">
				</div>
			</div>
		</div>
	</div>
</div>