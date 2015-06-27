<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change password</title>

<jsp:include page="../general/head.jsp" />
</head>
<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />


		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<div class="container" style="margin-top: 5em">
				<form class="form-horizontal" action="setNewPasswordByToken" method="post">
					<fieldset>

						<!-- Form Name -->
						<legend>Form Name</legend>

						<!-- Text input-->
						<input type="hidden" name="token" id="token" value="${token}" />

						<!-- Text input-->
						<div class="control-group">
							<p>New password</p>
							<div class="controls">
								<input id="password" name="password" type="text"
									class="input-xlarge" required="">

							</div>
						</div>

						<!-- Text input-->
						<div class="control-group">
							<p>Confirm password</p>
							<div class="controls">
								<input id="password" name="confirmPassword" type="text"
									class="input-xlarge" required="">
							</div>
						</div>



						<!-- Button -->
						<div class="control-group">
							<label class="control-label" for="button"></label>
							<div class="controls">
								<button id="button" name="button" class="btn btn-primary">Send</button>
							</div>
						</div>

					</fieldset>
				</form>
			</div>
		</div>
	</div>
	
	<jsp:include page="../general/footer.jsp" />
</body>
</html>