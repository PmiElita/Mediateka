<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create club</title>

<jsp:include page="../general/head.jsp" />
</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>

			<jsp:include page="../user/user_side_nav.jsp" />


			<div class="container section white">

				<h3 class="center">Send message to club members</h3>

				<form id="sendMessageForm" action="sendMessageForm" method="post"></form>
				<div>
					<input type="text" id="clubId" name="clubId" required
						pattern=".{1,45}" form="createClub" value="${clubId}" hidden><br>


					<div class="row">
						Inhalt:
						<textarea name="message" id="message" pattern=".{0,255}"
							class="materialize-textarea" form="sendMessageForm"></textarea>

						<input type="submit" id="subbut" name="subbut" value="send"
							class="btn" form="createClub" onclick="send()">
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />

	<script type="text/javascript">
		function send() {
			$
					.ajax({
						url : 'sendEmailToClubMembers',
						type: 'post',
						data : {
							clubId : document.getElementById('clubId'),
							message : document.getElementById('message')
						},
						success : function(data) {


						
						}
					})

		}
	</script>


</body>

</html>