<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:useBean id="consts" class="com.mediateka.util.RegExps"
	scope="session" />

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/reset_password_form" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../general/head.jsp" />
</head>
<body>

	<div class=main>
		<jsp:include page="../general/nav.jsp" />


		<div class="container">
			<c:if test="${notification ne null }">
				<fmt:message bundle="${msg}" key="${notification}" />
			</c:if>

			<form action="resetPassword" method="post">

				<div class="row">
					<div class="input-field col s6">
						<p>
							<fmt:message bundle="${msg}" key="old_password" />
						</p>
						<input id="password" name="oldPassword" type="password"
							title="<fmt:message bundle="${msg}" key="password_requirements" />"
							class="validate" required>
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col s6">
						<p>
							<fmt:message bundle="${msg}" key="password" />
						</p>
						<input id="password" name="newPassword" type="password"
							title="<fmt:message bundle="${msg}" key="password_requirements" />"
							pattern="${consts.getPassword() }" class="validate" required
							onchange=" this.setCustomValidity(this.validity.patternMismatch ? this.title : ''); if(this.checkValidity()) form.confirmPassword.pattern = this.value;">
					</div>

					<div class="input-field col s6">
						<p>
							<fmt:message bundle="${msg}" key="confirm_password" />
						</p>
						<input id="password" name="confirmNewPassword" type="password"
							title="<fmt:message bundle="${msg}" key="password_requirements" />"
							pattern="${consts.getPassword() }" class="validate" required>
					</div>
				</div>

				<button class="btn waves-effect blue titler" type="submit"
					name="action" style="margin-bottom: 3.5em">
					<fmt:message bundle="${msg}" key="button" />
					<i class="mdi-content-send right"></i>
				</button>

			</form>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp"/>
</body>
</html>