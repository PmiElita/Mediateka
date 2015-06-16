<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav >
	<div class="nav-wrapper">

		<a href="index" class="brand-logo"><img alt="Logo"
			src="images/logo.png" style="width:inherit"/></a>

		<ul  class="right hide-on-med-and-down">

			<li><a style="margin-right: 10em; font-size: 1.5em">MUMU</a></li>


			<li  class="profile-tooltipped">
			<a title="Edit profile" href="#" class="waves-effect" >
			<i	class="large mdi-action-face-unlock"></i>
			
			</a><jsp:include page="../general/profile_tooltip.jsp"/></li>

			<li><a title="Cabinet" href="cabinet" class="waves-effect">
					<i class="large mdi-maps-local-library"></i>
			</a></li>

			<li><a title="Register" href="" data-target="modal2"
				class="modal-trigger waves-effect"> <i
					class="large mdi-action-assignment-ind"></i></a></li>

			<li><a title="Login" href="" data-target="modal1"
				class="modal-trigger waves-effect"> <i
					class="large mdi-action-input"></i></a></li>

			<li><a title="Change language" class="dropdown-button" href="#!"
				data-activates="dropdown1">Language<i
					class="mdi-navigation-arrow-drop-down right"></i></a></li>
		</ul>
	</div>

</nav>

<!-- Dropdown Structure -->
<ul id="dropdown1" class="dropdown-content">
	<li><a href="#!"><img src="images/UAFlag.png" alt="Ukraine" />UKR</a></li>
	<li class="divider"></li>
	<li><a href="#!"><img src="images/USFlag.png" alt="USA" />USA</a></li>
</ul>