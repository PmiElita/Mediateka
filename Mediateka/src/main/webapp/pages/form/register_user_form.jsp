<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="consts" class="com.mediateka.util.RegExps"
	scope="session" />

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin" var="msg" />


<div id="modal4" class="modal">
	<div class="modal-content">

		<div id="creation_form">

			<form action="registerNewUser" method="post">

				<div class="container" style="width: 95%">
					<div style="margin-top: -3.5em">
						<button class="btn waves-effect blue titler" type="submit"
							name="action" style="margin-bottom: 3.5em">
							<fmt:message bundle="${msg}" key="registration.formName" />
							<i class="mdi-content-send right"></i>
						</button>


						<div class="row">
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.formId" />
								</p>
								<input id="formId" name="formId" type="text" class="validate"
									pattern="${consts.getOnlyDigits() }" required="required">
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.first_name" />
								</p>
								<input id="firstName" name="firstName" type="text"
									class="validate" pattern="${consts.getOnlyChars() }"
									required="required">
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.last_name" />
								</p>
								<input id="lastName" name="lastName" type="text"
									class="validate" pattern="${consts.getOnlyChars() }"
									required="required">
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.middle_name" />
								</p>
								<input id="middleName" name="middleName" type="text"
									class="validate" pattern="${consts.getOnlyChars() }"
									required="required">
							</div>
						</div>

						<div class="row">
							<div class="input-field col s3" style="margin-top: 0.5em">
								<p>
									<fmt:message bundle="${msg}" key="registration.nationality" />
								</p>
								<input id="nationality" name="nationality" type="text"
									class="validate" pattern="${consts.getOnlyChars() }"
									required="required">
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.profession" />
								</p>
								<select id="profession" name="profession"
									class="browser-default" style="margin-top: 0.75em"
									required="required">

									<c:forEach items="${professions}" var="profession">
										<option value="${profession.getId()}">
											<fmt:message bundle="${msg}"
												key="registration.profession.${profession.getName()}" />
										</option>
									</c:forEach>

								</select>
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.education" />
								</p>
								<select id="education" name="education" class="browser-default"
									style="margin-top: 0.75em" required="required">

									<option value="PRIMARY"><fmt:message bundle="${msg}"
											key="registration.education.primary" /></option>
									<option value="LOWER_SECONDARY"><fmt:message
											bundle="${msg}" key="registration.education.lower_secondary" /></option>
									<option value="UPPER_SECONDARY"><fmt:message
											bundle="${msg}" key="registration.education.upper_secondary" /></option>
									<option value="BACHELOR"><fmt:message bundle="${msg}"
											key="registration.education.bachelor" /></option>
									<option value="MASTER"><fmt:message bundle="${msg}"
											key="registration.education.master" /></option>
									<option value="DOCTORAL"><fmt:message bundle="${msg}"
											key="registration.education.doctoral" /></option>

								</select>
							</div>
							<div class="input-field col s3" style="margin-top: 0em">
								<p>
									<fmt:message bundle="${msg}" key="registration.institution" />
								</p>
								<input id="eduInstitution" name="institution" type="text"
									class="validate" style="margin-top: 0.75em"
									pattern="${consts.getAnyCharacters() }" required="required">
							</div>
						</div>


						<div class="row">
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.birth_date" />
								</p>
								<input id="birthDate" name="birthDate" type="text"
									data-field="date" required="required">
								<div id="dtBox"></div>
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.email" />
								</p>
								<input id="email" name="email" type="text" class="validate"
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
								<input id="adress" name="address" type="text" class="validate"
									pattern="${consts.getAnyCharacters() }" required="required">
							</div>
							<div class="input-field col s3">
								<p>
									<fmt:message bundle="${msg}" key="registration.phone" />
								</p>
								<input id="phone" name="phone" type="text" class="validate"
									pattern="${consts.getPhoneNumber() }" required="required">
							</div>

						</div>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>