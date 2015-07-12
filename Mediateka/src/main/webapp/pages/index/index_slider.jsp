<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="slider">
	<ul class="slides">
		<li><img src="images/slide_1.jpg">
			<div class="caption center-align">
				<h3>Wellcome!</h3>
				<h5 class="light grey-text text-lighten-3">Greetings from
					MediaTec.</h5>
			</div></li>
		<li><img src="images/slide_2.jpg">
			<div class="caption left-align">
				<h3>Media</h3>
				<h5 class="light grey-text text-lighten-3">You can find
					here....</h5>
			</div></li>
		<li><img src="images/slide_3.jpg">
			<div class="caption right-align">
				<h3>Great amount</h3>
				<h5 class="light grey-text text-lighten-3">of many interesting
					things.</h5>
			</div></li>
	</ul>
</div>

<!-- SEE THIS -->

<div class="slider">
	<ul class="slides">
		<c:if test="${cookie.lang.value eq 'uk-UA'}">
			<li><img src="${imagePath[0]}">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTetxUa[0]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[1]}">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTetxUa[1]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[2]}">
				<div class="${randomic}">
					<h3>Медіатека</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTetxUa[2]}" />
					</h5>
				</div></li>
		</c:if>
		<c:if test="${cookie.lang.value eq 'en-US'}">
			<li><img src="${imagePath[0]}">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTetxEn[1]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[1]}">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTetxEn[2]}" />
					</h5>
				</div></li>
			<li><img src="${imagePath[3]}">
				<div class="${randomic}">
					<h3>MediaTec</h3>
					<h5 class="light grey-text text-lighten-3">
						<c:out value="${imageTetxEn[3]}" />
					</h5>
				</div></li>
		</c:if>
	</ul>
</div>