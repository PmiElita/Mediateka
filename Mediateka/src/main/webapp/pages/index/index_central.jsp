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
							<div class="card-content" style="padding: 0">
								<span class="card-title activator grey-text text-darken-4"><div
										class="row" style="margin-top: 1em">
										<div class="col s10">
											<a href="event?eventId=${item.id}"><div align="center"
													style="margin-top: -1em; margin-left: 1em" class="cyss">
													<c:out value="${item.name}" />
												</div></a>
										</div>
										<div class="col s2">
											<i class="mdi-navigation-more-vert left"
												style="margin-top: 2em"></i>
										</div>
									</div> </span>
							</div>
							<div class="card-reveal">
								<span class="card-title grey-text text-darken-4"><div
										style="color: red;">
										<a href="event?eventId=${item.id}"><div align="center">
												<c:out value="${item.name}" />
											</div></a>
									</div> <i class="mdi-navigation-close right"></i></span><br> <br>
								<p>
								<div style="color: green;">Event time:</div>
								<br>
								<c:out value="${dates[status.index]}" />
								<c:if test="${item.type eq EventType.MEETING}">
									<c:out value="${times[status.index]}" />
								</c:if>
								</p>
								<br>
								<p>
								<div style="color: green;">Description:</div>
								<c:out value="${item.description}" />
								</p>
								<br>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="section">
					<jsp:include page="map.jsp" />
					<div class="section center"
						style="padding-left: 1.5em; padding-right: 1.5em; margin-top: 2em; font-size: 1.25em">
						<p>
							<c:if test="${cookie.lang.value eq 'uk-UA'}">
								<c:out value="${textInfoUa}" />
							</c:if>
							<c:if test="${cookie.lang.value eq 'en-US'}">
								<c:out value="${textInfoEn}" />
							</c:if>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>