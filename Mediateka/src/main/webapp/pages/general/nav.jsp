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

		<ul class="right">

			<li><a title="<fmt:message bundle="${msg}" key="events" />" href="events" class="waves-effect"> <i
					style="width: 1em"><img style="width: 120%; height: 60%; margin-top:0.35em; margin-left:-0.25em"
						src="images/admin/events.png" /></i>
			</a></li>

			<li><a title="<fmt:message bundle="${msg}" key="clubs" />" href="clubs" class="waves-effect"> <i
					style="width: 1em"><img style="width: 120%; margin-left:-0.15em"
						src="images/admin/clubs.png" /></i>
			</a></li>

			<li><a title="<fmt:message bundle="${msg}" key="books" />" href="searchBook" class="waves-effect"> <i
					style="width: 1em"><img style="width: 120%; height:60%; margin-top:0.35em; margin-left:-0.15em"
						src="images/admin/books.png" /></i>
			</a></li>

			<li><a style="font-size: 1.5em" id="top-user-name">${userName}</a></li>
			<c:if test="${userRole ne null}">
				<li class="profile-tooltipped"><a
					title="<fmt:message bundle="${msg}" key="edit_profile" />"
					href="modifyUser" class="waves-effect"> <i
						class="large mdi-action-face-unlock"></i>

				</a><jsp:include page="../general/profile_tooltip.jsp" /></li>

				<li><a title="<fmt:message bundle="${msg}" key="cabinet" />"
					href="cabinet" class="waves-effect"> <i
						class="large mdi-maps-local-library"></i>
				</a></li>

				<li><a title="<fmt:message bundle="${msg}" key="logout" />"
					href="logout" class="waves-effect"><i
						class="large mdi-navigation-cancel"></i></a></li>
			</c:if>


			<c:if test="${userRole eq null}">
				<li><a title="<fmt:message bundle="${msg}" key="register" />"
					href="" data-target="modal2" class="modal-trigger waves-effect">
						<i class="large mdi-action-assignment-ind"></i>
				</a></li>

				<li><a title="<fmt:message bundle="${msg}" key="login" />"
					href="" data-target="modal1" class="modal-trigger waves-effect">
						<i class="large mdi-action-input"></i>
				</a></li>
			</c:if>

			<li style="height: 4.5em"><c:if
					test="${cookie.lang.value eq 'en-US'}">
					<a href="chooseLanguage?lang=uk"><div style="margin-top: 0.7em"></div>
						<img title="<fmt:message bundle="${msg}" key="change_language" />"
						src="images/UAFlag.png" alt="Ukrainian" title="" /></a>
				</c:if> <c:if test="${cookie.lang.value eq 'uk-UA'}">
					<a href="chooseLanguage?lang=en"><div style="margin-top: 0.7em"></div>
						<img title="<fmt:message bundle="${msg}" key="change_language" />"
						src="images/GBFlag.png" alt="English" /></a>
				</c:if></li>
		</ul>
	</div>

</nav>