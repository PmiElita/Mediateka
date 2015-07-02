<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mediateka.model.enums.EventType"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />



<div class="parallax-container my-parallax" style="max-height: 100%;">
	<div class="parallax" style="max-height: 100%">
		<img src="images/parallax1.jpg">
	</div>

	<div class="container white" style="padding: 0; margin-top: 0">

		<jsp:include page="index_slider.jsp" />

		<div class="main_poster" style="margin-top: 2em">

			<div class="row">
				<c:forEach var="item" items="${events}" varStatus="status">
					<div class="col s4">
						<div class="card medium" style="height: 20em;">
							<div class="card-image waves-effect waves-block waves-light"
								style="height: 10em">
								<img class="activator" src="images/events/event2.jpg">
							</div>
							<div class="card-content">
								<span class="card-title activator grey-text text-darken-4"><div
										style="color: red;">
										<a href="event?eventId=${item.id}"><div align="center">
												<c:out value="${item.name}" />
											</div></a>
									</div> <i class="mdi-navigation-more-vert right"></i> </span>
							</div>
							<div class="card-reveal">
								<span class="card-title grey-text text-darken-4"><div
										style="color: red;">
										<a href="event?eventId=${item.id}"><div align="center">
												<c:out value="${item.name}" />
											</div></a>
									</div> <i class="mdi-navigation-close right"></i></span> <br>
								<p>
								<div style="color: green;">Event time:</div>
								<c:out value="${dates[status.index]}" />
								</p>
								<p>
								<div style="color: green;">Description:</div>
								<c:out value="${item.description}" />
								</p>
								<br>
							</div>
						</div>
					</div>
				</c:forEach>

				<div class="section">
					<div class="main-info">
						<p>Перша львівська медіатека – це проект громадського центру
							нового типу, діє при Центральній бібліотеці для дорослих ім. Лесі
							Українки. Медіатека – інтерактивний громадський центр, який
							пропонує можливість участі у заходах та використання
							різноманітних ресурсів, включаючи Інтернет, CD та DVD-носії,
							електронні книги, інтерактивні програми вивчення іноземних мов і
							комп’ютерних технологій, програми дитячого розвитку, комп’ютерні
							курси, музичну студію, кінозал, дискусійні клуби, тренінги і
							гуртки для усіх членів родини.</p>
						<p>Приміщення доступне для людей з особливими потребами. На
							додачу до послуг традиційної бібліотеки громада міста може
							насолоджуватись сучасним комфортним дизайном центру та значно
							ширшим спектром послуг.</p>
						<p>Медіатека складається вона з трьох функціональних приміщень
							та студії звукозапису. У першому, найбільшому – комп’ютери, друге
							приміщення – кімната для юнацтва, третє приміщення – кінозал та
							конференц-зал.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>