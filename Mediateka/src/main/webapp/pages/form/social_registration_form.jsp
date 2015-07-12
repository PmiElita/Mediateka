<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.Role"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/user_modification_form" var="msg" />


<!DOCTYPE html>
<html>
<head>
<jsp:include page="../general/head.jsp" />

</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />


		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
		
			<div class="container section white">
				<label style="color: #2196F3;">${message }</label>

				<h3>
					<fmt:message bundle="${msg}" key="user_form" />
				</h3>
				<form id="saveUser" action="socialRegistration" method="post" onchange="checkEmail();">

					<button class="btn waves-effect titler" type="submit" name="action"
						style="margin-bottom: 0.5em; margin-top: 2em">
						<fmt:message bundle="${msg}" key="register" />
					</button>
                     <input type="text" name="socialId" value="${user.socialId }" hidden>
					<div class="row">
						<div class="input-field col s4">
							<p>
								<fmt:message bundle="${msg}" key="first_name" />
							</p>
							<input id="firstName" name="firstName" type="text"
								class="validate" value="${user.firstName }" required>
						</div>
						<div class="input-field col s4">
							<p>
								<fmt:message bundle="${msg}" key="last_name" />
							</p>
							<input id="lastName" name="lastName" type="text" class="validate"
								required value="${user.lastName}">
						</div>
						<div class="input-field col s4">
							<p>
								<fmt:message bundle="${msg}" key="middle_name" />
							</p>
							<input id="middleName" name="middleName" type="text" required
								class="validate" value="">
						</div>

					</div>

					<div class="row">
						<div class="input-field col s3" style="margin-top: 0.5em">
							<p>
								<fmt:message bundle="${msg}" key="nationality" />
							</p>
							<input id="nationality" name="nationality" type="text" required
								class="validate" value="">
						</div>

						<div class="input-field col s3">
							<p>
								<fmt:message bundle="${msg}" key="profession" />
							</p>
							<select id="profession" name="profession" class="browser-default"
								required style="margin-top: 0.75em">
								<option value="">
										<fmt:message bundle="${msg}"
											key="profession.select" />
								<c:forEach items="${professions}" var="profession">
									<option value="${profession.getId()}">
										<fmt:message bundle="${msg}"
											key="profession.${profession.getName()}" />
									</option>
								</c:forEach>
							</select>
						</div>

						<div class="input-field col s3">
							<p>
								<fmt:message bundle="${msg}" key="education" />
							</p>
							<select id="education" name="education" class="browser-default"
								required style="margin-top: 0.75em">
								<option value="">
									<fmt:message bundle="${msg}" key="education.select" />
								</option>
								<option value="PRIMARY">
									<fmt:message bundle="${msg}" key="education.primary" />
								</option>
								<option value="BACHELOR">
									<fmt:message bundle="${msg}" key="education.bachelor" />
								</option>
								<option value="MASTER">
									<fmt:message bundle="${msg}" key="education.master" />
								</option>
								<option value="DOCTORAL">
									<fmt:message bundle="${msg}" key="education.doctoral" />
								</option>

							</select>
						</div>


						<div class="input-field col s3" style="margin-top: 0em">
							<p>
								<fmt:message bundle="${msg}" key="institution" />
							</p>
							<input id="eduInstitution" name="institution" type="text"
								class="validate" style="margin-top: 0.75em"
								value="">
						</div>
					</div>

					<div class="row">
						<div class="input-field col s4">
							<p>
								<fmt:message bundle="${msg}" key="birth_date" />
							</p>
							<input id="birthDate" value="" name="birthDate"
								type="text" required data-field="date" required="required">
							<div id="dtBox"></div>
						</div>

						<div class="input-field col s4">
							<p>
								<fmt:message bundle="${msg}" key="address" />
							</p>
							<input id="adress" name="address" type="text" class="validate"
								value="">
						</div>
						<div class="input-field col s4">
							<p>
								<fmt:message bundle="${msg}" key="phone" />
							</p>
							<input id="phone" name="phone" type="text" class="validate"
								value="">
						</div>
						<div class="input-field col s4">
							<p>
								Email
							</p>
							<input id="email" name="email" type="text" class="validate"
								value="${user.email }" onchange="checkEmail();">
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function checkEmail(){
	var isEmailFree = false;
	var email = document.getElementById("email");
	$.ajax(
			{
				url:'checkemail', 
				data : { email : email.value}, 
				success : 
					function(data){
					
						if (data == 'true'){
							email.setCustomValidity('');
							email.setAttribute('class', 'validate valid');
							isEmailFree = true;
							
						} else {
							email.setCustomValidity('<fmt:message bundle="${msg}" key="registration.email_is_in_use" />');
							email.setAttribute('class', 'validate invalid');
					         isEmailFree= false;
						}
					}
			} 
		);
	return isEmailFree;
}
</script>
</html>