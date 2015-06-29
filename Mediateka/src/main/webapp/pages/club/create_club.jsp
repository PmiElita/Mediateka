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
			
				<h3 class="center">Create club</h3>
				${message}
				<form id="createClub" action="createClub" method="post"></form>
				<div>
					<div class="row">
						Club name:<input type="text" id="name" name="name" required
							pattern=".{1,45}" form="createClub" ><br>
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
	<jsp:include page="../general/footer.jsp" />
</body>

</html>