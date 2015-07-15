<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${cookie.lang.value eq 'uk-UA'}">
	<div class="slider">
		<ul class="slides">
			<li><img src="images/slide_1.jpg">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextUa[0]}" />
					</h5>
				</div></li>
			<li><img src="images/slide_2.jpg">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextUa[1]}" />
					</h5>
				</div></li>
			<li><img src="images/slide_3.jpg">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextUa[2]}" />
					</h5>
				</div></li>
		</ul>
	</div>
</c:if>

<c:if test="${cookie.lang.value eq 'en-US'}">
	<div class="slider">
		<ul class="slides">
			<li><img src="images/slide_1.jpg">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextEn[0]}" />
					</h5>
				</div></li>
			<li><img src="images/slide_2.jpg">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextEn[1]}" />
					</h5>
				</div></li>
			<li><img src="images/slide_3.jpg">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextEn[2]}" />
					</h5>
				</div></li>
		</ul>
	</div>
</c:if>