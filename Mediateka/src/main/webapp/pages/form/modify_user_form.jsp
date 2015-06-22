<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/user_modification_form" var="msg"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../general/head.jsp" />
</head>
<body>
	<jsp:include page="../general/nav.jsp" />




	<form action="modifyUser" method="post">

		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="first_name" />
			</p>
			<input id="firstName" name="firstName" type="text" class="validate"
				value="${firstName }">
		</div>
		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="last_name" />
			</p>
			<input id="lastName" name="lastName" type="text" class="validate"
				value="${lastName }">
		</div>
		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="middle_name" />
			</p>
			<input id="middleName" name="middleName" type="text" class="validate"
				value="${middleName }">
		</div>

		<div class="input-field col s3" style="margin-top: 0.5em">
			<p>
				<fmt:message bundle="${msg}" key="nationality" />
			</p>
			<input id="nationality" name="nationality" type="text"
				class="validate" value="${nationality }">
		</div>
		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="profession" />
			</p>
			<select id="profession" name="profession" class="browser-default"
				style="margin-top: 0.75em">
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
				style="margin-top: 0.75em">
				<option value="PRIMARY">
					<fmt:message bundle="${msg}" key="education.primary" />
				</option>
				<option value="LOWER_SECONDARY">
					<fmt:message bundle="${msg}" key="education.lower_secondary" />
				</option>
				<option value="UPPER_SECONDARY">
					<fmt:message bundle="${msg}" key="education.upper_secondary" />
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
				value="${eduInstitution }">
		</div>

		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="birth_date" />
			</p>
			<input id="birthDate" name="birthDate" type="date" class="datepicker"
				value="${birthDate }">
		</div>

		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="address" />
			</p>
			<input id="adress" name="address" type="text" class="validate"
				value="${address }">
		</div>
		<div class="input-field col s3">
			<p>
				<fmt:message bundle="${msg}" key="phone" />
			</p>
			<input id="phone" name="phone" type="text" class="validate"
				value="${phone }">
		</div>


		<button class="btn waves-effect blue titler" type="submit"
			name="action" style="margin-bottom: 3.5em">
			<fmt:message bundle="${msg}" key="button" /><i class="mdi-content-send right"></i>
		</button>

	</form>





</body>
</html>