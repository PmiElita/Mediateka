<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="slider">
	<ul class="slides">
		<c:if test="${cookie.lang.value eq 'uk-UA'}">
			<li><img src="${imagePath[0]}">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextUa[0]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[1]}">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextUa[1]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[2]}">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextUa[2]}" />
					</h5>
				</div></li>
		</c:if>
		<c:if test="${cookie.lang.value eq 'en-US'}">
			<li><img src="${imagePath[0]}">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextEn[0]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[1]}">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextEn[1]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[2]}">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTextEn[2]}" />
					</h5>
				</div></li>
		</c:if>
	</ul>
</div>
${imagePath[2]}