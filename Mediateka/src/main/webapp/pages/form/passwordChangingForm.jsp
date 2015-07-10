<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/forms" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message bundle="${msg }" key="password_changing_form.title"/> </title>

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
				<form class="form-horizontal" action="changePassword" method="post">
					<fieldset>

						<!-- Form Name -->
						<legend><fmt:message bundle="${msg }" key="password_changing_form.form_name"/></legend>

						<!-- Text input-->
						<input type="hidden" name="token" id="token" value="${token}" />

						<!-- Text input-->
						<div class="control-group">
							<p><fmt:message bundle="${msg }" key="password_changing_form.new_password"/></p>
							<div class="controls">
								<input id="password" name="password" type="text"
									class="input-xlarge" required="">

							</div>
						</div>

						<!-- Text input-->
						<div class="control-group">
							<p><fmt:message bundle="${msg }" key="password_changing_form.confirm_password"/></p>
							<div class="controls">
								<input id="password" name="confirmPassword" type="text"
									class="input-xlarge" required="">
							</div>
						</div>



						<!-- Button -->
						<div class="control-group">
							<label class="control-label" for="button"></label>
							<div class="controls">
								<button id="button" name="button" class="btn btn-primary"><fmt:message bundle="${msg }" key="password_changing_form.send"/></button>
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