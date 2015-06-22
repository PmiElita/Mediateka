<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/nav_jsp" var="msg" scope="session" />


<nav>
	<div class="nav-wrapper">

		<a href="index" class="brand-logo"><img alt="Logo"
			src="images/logo.png" style="width: inherit" /></a>

		<ul class="right hide-on-med-and-down">

			<li><a style="font-size: 1.5em" id="top-user-name">${userName}</a></li>
			<c:if test="${userRole ne null}">
				<li class="profile-tooltipped"><a title="<fmt:message bundle="${msg}" key="edit_profile" />" href="modifyUser"
					class="waves-effect"> <i class="large mdi-action-face-unlock"></i>

				</a><jsp:include page="../general/profile_tooltip.jsp" /></li>

				<li><a title="<fmt:message bundle="${msg}" key="cabinet" />" href="cabinet" class="waves-effect">
						<i class="large mdi-maps-local-library"></i>
				</a></li>

				<li><a title="<fmt:message bundle="${msg}" key="logout" />" href="logout" class="waves-effect"><i
						class="large mdi-navigation-cancel"></i></a></li>
			</c:if>


			<c:if test="${userRole eq null}">
				<li><a title="<fmt:message bundle="${msg}" key="register" />" href="" data-target="modal2"
					class="modal-trigger waves-effect"> <i
						class="large mdi-action-assignment-ind"></i></a></li>

				<li><a title="<fmt:message bundle="${msg}" key="login" />" href="" data-target="modal1"
					class="modal-trigger waves-effect"> <i
						class="large mdi-action-input"></i></a></li>
			</c:if>



			<li><a
				title="<fmt:message bundle="${msg}" key="change_language" />"
				class="dropdown-button" href="#!" data-activates="dropdown1"><fmt:message
						bundle="${msg}" key="language" /><i
					class="mdi-navigation-arrow-drop-down right"></i></a></li>
		</ul>
	</div>

</nav>

<!-- Dropdown Structure -->
<ul id="dropdown1" class="dropdown-content">
	<li><a href="chooseLanguage?lang=uk"><img
			src="images/UAFlag.png" alt="Ukraine" />UKR</a></li>
	<li class="divider"></li>
	<li><a href="chooseLanguage?lang=en"><img
			src="images/USFlag.png" alt="USA" />USA</a></li>
</ul>