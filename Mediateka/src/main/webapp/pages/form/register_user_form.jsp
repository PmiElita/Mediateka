<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin" var="msg" />


<div id="modal4" class="modal">
	<div class="modal-content">

		<div id="creation_form">

			<form action="registerNewUser" method="post">

				<div class="container">
					<div style="margin-top: -3.5em">
						<button class="btn waves-effect blue titler" type="submit"
							name="action" style="margin-bottom: 3.5em">
							<fmt:message bundle="${msg}" key="registration.formName" />
							<i class="mdi-content-send right"></i>
						</button>

						<div class="row">
							<div class="col s12">
								<div class="row">
									<div class="input-field col s3">
										<p>
											<fmt:message bundle="${msg}" key="registration.formId" />
										</p>
										<input id="formId" name="formId" type="text" class="validate">
									</div>
									<div class="input-field col s3">
										<p>
											<fmt:message bundle="${msg}" key="registration.first_name" />
										</p>
										<input id="firstName" name="firstName" type="text"
											class="validate">
									</div>
									<div class="input-field col s3">
										<p>
											<fmt:message bundle="${msg}" key="registration.last_name" />
										</p>
										<input id="lastName" name="lastName" type="text"
											class="validate">
									</div>
									<div class="input-field col s3">
										<p>
											<fmt:message bundle="${msg}" key="registration.middle_name" />
										</p>
										<input id="middleName" name="middleName" type="text"
											class="validate">
									</div>
								</div>

								<div class="row">
									<div class="input-field col s3" style="margin-top: 0.5em">
										<p>
											<fmt:message bundle="${msg}" key="registration.nationality" />
										</p>
										<input id="nationality" name="nationality" type="text"
											class="validate">
									</div>
									<div class="input-field col s3">
										<p>
											<fmt:message bundle="${msg}" key="registration.profession" />
										</p>
										<select id="profession" name="profession"
											class="browser-default" style="margin-top: 0.75em">

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
										<select id="education" name="education"
											class="browser-default" style="margin-top: 0.75em">

											<option value="PRIMARY"><fmt:message bundle="${msg}" key="registration.education.primary" /></option>
											<option value="LOWER_SECONDARY"><fmt:message bundle="${msg}" key="registration.education.lower_secondary" /></option>
											<option value="UPPER_SECONDARY"><fmt:message bundle="${msg}" key="registration.education.upper_secondary" /></option>
											<option value="BACHELOR"><fmt:message bundle="${msg}" key="registration.education.bachelor" /></option>
											<option value="MASTER"><fmt:message bundle="${msg}" key="registration.education.master" /></option>
											<option value="DOCTORAL"><fmt:message bundle="${msg}" key="registration.education.doctoral" /></option>

										</select>
									</div>
									<div class="input-field col s3" style="margin-top: 0em">
										<p><fmt:message bundle="${msg}" key="registration.institution" /></p>
										<input id="eduInstitution" name="institution" type="text"
											class="validate" style="margin-top: 0.75em">
									</div>
								</div>


								<div class="row">
									<div class="input-field col s3">
										<p><fmt:message bundle="${msg}" key="registration.birth_date" /></p>
										<input id="birthDate" name="birthDate" type="text"
											data-field="date">
										<div id="dtBox"></div>
									</div>
									<div class="input-field col s3">
										<p><fmt:message bundle="${msg}" key="registration.email" /></p>
										<input id="email" name="email" type="text" class="validate">
									</div>
									<div class="input-field col s3">
										<p><fmt:message bundle="${msg}" key="registration.address" /></p>
										<input id="adress" name="address" type="text" class="validate">
									</div>
									<div class="input-field col s3">
										<p><fmt:message bundle="${msg}" key="registration.phone" /></p>
										<input id="phone" name="phone" type="text" class="validate">
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>