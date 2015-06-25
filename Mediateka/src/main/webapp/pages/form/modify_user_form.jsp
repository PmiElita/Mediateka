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
<script src="js/modifyUser.js"></script>
</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />
        
				<c:if test="${userRole eq Role.ADMIN}">
					<jsp:include page="../admin/admin_side_nav.jsp" />
				</c:if>

				<c:if test="${userRole eq Role.USER}">
					<jsp:include page="../user/user_side_nav.jsp" />
				</c:if>
		<div class="container">
		<label style="color:#2196F3;">${message }</label>
			<form id="modifyUser" action="modifyUser" method="post" onsubmit="return submitModify();">

				<button class="btn waves-effect blue titler" type="submit"
					name="action" style="margin-bottom: 0.5em; margin-top:2em">
					<fmt:message bundle="${msg}" key="button" />
					<i class="mdi-content-send right"></i>
				</button>
				
				<div class="row">
					<div class="input-field col s4">
						<p>
							<fmt:message bundle="${msg}" key="first_name" />
						</p>
						<input id="firstName" name="firstName" type="text"
							class="validate" value="${firstName }" required>
					</div>
					<div class="input-field col s4">
						<p>
							<fmt:message bundle="${msg}" key="last_name" />
						</p>
						<input id="lastName" name="lastName" type="text" class="validate" required
							value="${lastName }">
					</div>
					<div class="input-field col s4">
						<p>
							<fmt:message bundle="${msg}" key="middle_name" />
						</p>
						<input id="middleName" name="middleName" type="text" required
							class="validate" value="${middleName }">
					</div>

				</div>

				<div class="row">
					<div class="input-field col s3" style="margin-top: 0.5em">
						<p>
							<fmt:message bundle="${msg}" key="nationality" />
						</p>
						<input id="nationality" name="nationality" type="text" required
							class="validate" value="${nationality }">
					</div>

					<div class="input-field col s3">
						<p>
							<fmt:message bundle="${msg}" key="profession" />
						</p>
						<select id="profession" name="profession" class="browser-default" required
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
						<select id="education" name="education" class="browser-default" required
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
				</div>

				<div class="row">
					<div class="input-field col s4">
						<p>
							<fmt:message bundle="${msg}" key="birth_date" />
						</p>  
						<input id="birthDate" value="${birthDate }" name="birthDate" type="text" required
											data-field="date" required="required">
										<div id="dtBox"></div>
					</div>

					<div class="input-field col s4">
						<p>
							<fmt:message bundle="${msg}" key="address" />
						</p>
						<input id="adress" name="address" type="text" class="validate" 
							value="${address }">
					</div>
					<div class="input-field col s4">
						<p>
							<fmt:message bundle="${msg}" key="phone" />
						</p>
						<input id="phone" name="phone" type="text" class="validate" 
							value="${phone }">
					</div>

				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>