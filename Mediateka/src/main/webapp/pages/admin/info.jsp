<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />


<head>

<jsp:include page="../general/head.jsp" />

</head>


<jsp:include page="../general/nav.jsp" />
<jsp:include page="admin_side_nav.jsp" />

<div class="main-info">
	<div class="section white">
		<div class="container">
			<h3>Edit main page info</h3>
			<textarea id="infoText" name="infoText"
				class="materialize-textarea info">		Перша львівська медіатека – це проект громадського центру нового типу, діє при Центральній бібліотеці для дорослих ім. Лесі	Українки. Медіатека – інтерактивний громадський центр, який
пропонує можливість участі у заходах та використання різноманітних ресурсів, включаючи Інтернет, CD та DVD-носії, електронні книги,
інтерактивні програми вивчення іноземних мов і комп’ютерних технологій, програми дитячого розвитку, комп’ютерні курси, музичну
студію, кінозал, дискусійні клуби, тренінги і гуртки для усіх. Приміщення доступне для людей з особливими потребами. На додачу до послуг традиційної бібліотеки громада міста може насолоджуватись сучасним комфортним дизайном центру та значно
ширшим спектром послуг.
Медіатека складається вона з трьох функціональних приміщень та студії звукозапису. У першому, найбільшому – комп’ютери, друге приміщення – кімната для юнацтва, третє приміщення – кінозал та конференц-зал.
</textarea>
		</div>

		<div class="container">
			<div class="row">
				<h3>Edit slider images</h3>
				<div class="col s6">
					<h6>1</h6>
					<img src="images/slide_1.jpg" style="width: 20em;" />
				</div>
				<div class="col s6">
					<h6>2</h6>
					<img src="images/slide_2.jpg" style="width: 20em;" />
				</div>
				<div class="col s6">
					<h6>3</h6>
					<img src="images/slide_3.jpg" style="width: 20em;" />
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../general/footer.jsp" />