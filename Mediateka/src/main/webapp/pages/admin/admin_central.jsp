<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="org.apache.log4j.Logger"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<div class="user-info">
	<div class="section white">
		<div class="container">
		<h1>User info</h1>
			<div class="row">
				<div class="col 8">
					<h6>First name:</h6>
					<h6>Second name:</h6>
					<h6>E-mail:</h6>
					<h6>Gender:</h6>
				</div>
			</div>
		</div>
	</div>
</div>