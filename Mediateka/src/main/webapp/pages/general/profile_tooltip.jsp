<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	

<span> <img src="images/user.png" style="width: 5em; margin-top:0.5em" />
	<div style="margin-top: -1.5em">
		<strong>
			<p> <c:out value="${sessionScope.userCard.getFirstName()}"/>
			    <c:out value="${sessionScope.userCard.getLastName()}"/> </p>

			<p> <c:out value="${sessionScope.userCard.getEmail()}"/> </p>

		</strong>
	</div>
</span>