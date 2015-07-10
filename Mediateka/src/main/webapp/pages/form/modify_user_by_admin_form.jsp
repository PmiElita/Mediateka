<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin" var="msg" />


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
			<jsp:include page="../admin/admin_side_nav.jsp" />

				<div class="container white">
					<div class="section white center">
					
					<h3>Change user info</h3>
					<div id="user_modification_form">

						<form method="post" onsubmit="return false;">

							<input id="change_user_form_userId" name="userId" hidden=""
								value="${userId }">


									<div class="row">
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.formId" />
											</p>
											<input id="change_user_form_formId" name="formId" type="text"
												class="validate" value="${user.formId }"
												pattern="${consts.getOnlyDigits() }">
										</div>
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.first_name" />
											</p>
											<input id="change_user_form_firstName" name="firstName"
												type="text" class="validate"
												pattern="${consts.getOnlyChars() }"
												value="<c:out value="${user.firstName }" />"
												required="required">
										</div>
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.last_name" />
											</p>
											<input id="change_user_form_lastName" name="lastName"
												type="text" class="validate"
												value="<c:out value="${user.lastName }" />"
												pattern="${consts.getOnlyChars() }" required="required">
										</div>
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.middle_name" />
											</p>
											<input id="change_user_form_middleName" name="middleName"
												type="text" value="<c:out value="${user.middleName }" />"
												class="validate" pattern="${consts.getOnlyChars() }"
												required="required">
										</div>
									</div>

									<div class="row">
										<div class="input-field col s3" >
											<p>
												<fmt:message bundle="${msg}" key="registration.nationality" />
											</p>
											<input id="change_user_form_nationality" name="nationality"
												type="text" value="<c:out value="${user.nationality }" />"
												class="validate" pattern="${consts.getOnlyChars() }"
												required="required">
										</div>
										<div class="input-field col s3" style="margin-top:0.75em">
											<p>
												<fmt:message bundle="${msg}" key="registration.profession" />
											</p>
											<select id="change_user_form_profession" name="profession"
												class="browser-default" style="margin-top: 1em"
												required="required">

												<c:forEach items="${professions}" var="profession">

													<c:if test="${profession.getId() == user.professionId }">
														<option value="${profession.getId()}" selected>
															<fmt:message bundle="${msg}"
																key="registration.profession.${profession.getName()}" />
														</option>
													</c:if>


													<c:if test="${ ! (profession.id == user.professionId)}">
														<option value="${profession.getId()}">
															<fmt:message bundle="${msg}"
																key="registration.profession.${profession.getName()}" />
														</option>
													</c:if>



												</c:forEach>

											</select>
										</div>
										<div class="input-field col s3" style="margin-top:0.75em">
											<p>
												<fmt:message bundle="${msg}" key="registration.education" />
											</p>
											<select id="change_user_form_education" name="education"
												class="browser-default" style="margin-top: 1em"
												required="required">

												<c:choose>
													<c:when test="${user.education == 'PRIMARY'}">
														<option value="PRIMARY" selected><fmt:message
																bundle="${msg}" key="registration.education.primary" /></option>
													</c:when>
													<c:otherwise>
														<option value="PRIMARY" selected><fmt:message
																bundle="${msg}" key="registration.education.primary" /></option>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${user.education == 'LOWER_SECONDARY'}">
														<option value="LOWER_SECONDARY" selected><fmt:message
																bundle="${msg}"
																key="registration.education.lower_secondary" /></option>
													</c:when>
													<c:otherwise>
														<option value="LOWER_SECONDARY"><fmt:message
																bundle="${msg}"
																key="registration.education.lower_secondary" /></option>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${user.education == 'UPPER_SECONDARY'}">
														<option value="UPPER_SECONDARY" selected><fmt:message
																bundle="${msg}"
																key="registration.education.upper_secondary" /></option>
													</c:when>
													<c:otherwise>
														<option value="UPPER_SECONDARY"><fmt:message
																bundle="${msg}"
																key="registration.education.upper_secondary" /></option>
													</c:otherwise>
												</c:choose>




												<c:choose>
													<c:when test="${user.education == 'BACHELOR'}">
														<option value="BACHELOR" selected><fmt:message
																bundle="${msg}" key="registration.education.bachelor" /></option>
													</c:when>
													<c:otherwise>
														<option value="BACHELOR"><fmt:message
																bundle="${msg}" key="registration.education.bachelor" /></option>
													</c:otherwise>
												</c:choose>



												<c:choose>
													<c:when test="${user.education == 'MASTER'}">
														<option value="MASTER" selected><fmt:message
																bundle="${msg}" key="registration.education.master" /></option>
													</c:when>
													<c:otherwise>
														<option value="MASTER"><fmt:message
																bundle="${msg}" key="registration.education.master" /></option>
													</c:otherwise>
												</c:choose>



												<c:choose>
													<c:when test="${user.education == 'DOCTORAL'}">
														<option value="DOCTORAL" selected><fmt:message
																bundle="${msg}" key="registration.education.doctoral" /></option>
													</c:when>
													<c:otherwise>
														<option value="DOCTORAL"><fmt:message
																bundle="${msg}" key="registration.education.doctoral" /></option>
													</c:otherwise>
												</c:choose>




											</select>
										</div>
										<div class="input-field col s3" style="margin-top: 0em">
											<p>
												<fmt:message bundle="${msg}" key="registration.institution" />
											</p>
											<input id="change_user_form_eduInstitution"
												name="institution" type="text"
												value="<c:out value="${user.eduInstitution }" />"
												class="validate" style="margin-top: 0.75em"
												pattern="${consts.getAnyCharacters() }" required="required">
										</div>
									</div>


									<div class="row">
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.birth_date" />
											</p>
											<input id="change_user_form_birthDate" name="birthDate"
												type="text"
												value="<fmt:formatDate pattern="dd.MM.yyyy" value="${user.birthDate }" />"
												data-field="date" required="required">
											<div id="dtBox"></div>
										</div>
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.email" />
											</p>
											<input id="change_user_form_oldEmail"
												value="<c:out value="${user.email }" />" hidden="">
											<input id="change_user_form_email" name="email" type="text"
												class="validate" value="<c:out value="${user.email }" />"
												pattern="${consts.getEmail() }" required="required"
												onchange="
											$.ajax(
												{
													url:'checkemail', 
													data : { email : this.value}, 
													success : 
														function(data){
															if (data == 'true'){
																form.email.setCustomValidity('');
																form.email.setAttribute('class', 'validate valid');
															} else {
															
																oldEmail = documment.getElementById('oldEmail').value;
																newEmail = documment.getElementById(   'email').value;
																
																if (oldEmail == newEmail){
																	form.email.setCustomValidity('');
																	form.email.setAttribute('class', 'validate valid');
																	return;
																}

															
																form.email.setCustomValidity('<fmt:message bundle="${msg}" key="registration.email_is_in_use" />');
																form.email.setAttribute('class', 'validate invalid');
															}
														}
												} 
											);">
										</div>
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.address" />
											</p>
											<input id="change_user_form_adress" name="adress" type="text"
												class="validate" value="<c:out value="${user.adress }" />"
												pattern="${consts.getAnyCharacters() }" required="required">
										</div>
										<div class="input-field col s3">
											<p>
												<fmt:message bundle="${msg}" key="registration.phone" />
											</p>
											<input id="change_user_form_phone" name="phone" type="text"
												class="validate" value="<c:out value="${user.phone }" />"
												pattern="${consts.getPhoneNumber() }" required="required">
										</div>

										<button class="btn waves-effect titler" type="submit"
											name="action" style="margin-top:1em"
											onclick=" updateUser()">
											<fmt:message bundle="${msg}" key="change_user_info_button" />
										</button>


									</div>

						</form>
					</div>
				</div>

			</div>
		</div>


	</div>

	<jsp:include page="../general/footer.jsp" />



	<script type="text/javascript">
		function updateUser() {
			form = {
				'userId' : document.getElementById("change_user_form_userId").value,
				'formId' : document.getElementById("change_user_form_formId").value,
				'firstName' : document
						.getElementById("change_user_form_firstName").value,
				'lastName' : document
						.getElementById("change_user_form_lastName").value,
				'middleName' : document
						.getElementById("change_user_form_middleName").value,
				'nationality' : document
						.getElementById("change_user_form_nationality").value,
				'profession' : document
						.getElementById("change_user_form_profession").value,
				'education' : document
						.getElementById("change_user_form_education").value,
				'institution' : document
						.getElementById("change_user_form_eduInstitution").value,
				'birthDate' : document
						.getElementById("change_user_form_birthDate").value,
				'email' : document.getElementById("change_user_form_email").value,
				'address' : document.getElementById("change_user_form_adress").value,
				'phone' : document.getElementById("change_user_form_phone").value,
			};

			$
					.ajax({
						url : 'editUser',
						type : 'post',
						data : form,
						success : function(data) {
							Materialize
									.toast(
											"<fmt:message bundle="${msg }" key="user_information_was_successfully_changed" />",
											4000);
							return false;
						},
						error : function(data) {
							Materialize
									.toast(
											"<fmt:message bundle="${msg }" key="failed_to_change_user_information" />",
											4000);
							return false;
						}
					});
			return false;
		}
	</script>

</body>
</html>

