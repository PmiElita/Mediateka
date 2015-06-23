<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/side_nav" var="msg" scope="session" />



<head>
<jsp:include page="../form/register_user_form.jsp" />
<jsp:include page="admin_modal_users.jsp" />
</head>

<div class="container-side">
	<div id="sidebar" >
		<ul>
			<li><a href="" data-target="modal4"
				class="modal-trigger waves-effect"><fmt:message bundle="${msg}"
						key="admin.register_new_user" /></a></li>
			<li><a href="cabinet"><fmt:message bundle="${msg}"
						key="admin.cabinet" /></a></li>
			<li><a href="index"><fmt:message bundle="${msg}"
						key="admin.main_page" /></a></li>
		</ul>
	</div>
	<div class="main-content">
		<div class="swipe-area"></div>
		<a href="#!" data-toggle=".container-side" id="sidebar-toggle"> <span
			class="bar"></span> <span class="bar"></span> <span class="bar"></span>
		</a>
	</div>
</div>
