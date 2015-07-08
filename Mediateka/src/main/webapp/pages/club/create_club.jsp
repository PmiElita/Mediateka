<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />

<div id="modal20" class="modal">
	<div class="modal-content">

		<div class="container">

			<h3 class="center"><fmt:message bundle="${msg}" key="create_club" /></h3>
			${message}
			<form id="createClub" action="createClub" method="post">
			<div>
				<div class="row">
					<fmt:message bundle="${msg}" key="create_club.club_name" /><input type="text" id="name" name="name" required
						pattern=".{1,45}" form="createClub"><br>
				</div>

				<div class="row">
					<fmt:message bundle="${msg}" key="create_club.description" />
					<textarea name="description" id="description" pattern=".{0,255}"
						class="materialize-textarea" form="createClub"></textarea>
					<input type="submit" value="Create club"
						class="btn" form="createClub">
				</div>
			</div>
			</form>
		</div>
	</div>
</div>