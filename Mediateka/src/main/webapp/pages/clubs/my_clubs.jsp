<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="parallax-container my-parallax">
			<div class="parallax">
			</div>
			<div class="row" style="margin-top: -1em">
				<div class="col s12">
					<ul class="tabs">
						<li class="tab col s2" style="margin-left: 5em;"><a href="#my_active_clubs">Active</a></li>
						<li class="tab col s2" style="margin-left: -5em"><a href="#my_blocked_clubs">Blocked</a></li>
					</ul>
				</div>
				</div>
<div class="events_poster">
<div class="main-info">
<div id="my_active_clubs">
<div class="section white">
	<div class="container">
	<c:forEach var="item" items="${myActiveClubs}"  varStatus="status">
		<div class="row my-picture-row">
			<div class="col s8 offset-s2">
				<div class="waves-effect waves-block waves-light my-picture-wrap">
					<a href="club?id=${item.id}">
						<div align="center">
							<h3 class="image-cover-t"><c:out value="${item.name}"/>
							</h3><img class="my-picture-club" src="${myActiveClubsAvas[status.index]}" align="middle">
						</div>
					</a>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	</div>
	</div><br>
	<div id="my_blocked_clubs">
	<div class="section white">
	<div class="container">
	<c:forEach var="item" items="${myBlockedClubs}"  varStatus="status">
		<div class="row my-picture-row">
			<div class="col s8 offset-s2">
				<div class="waves-effect waves-block waves-light my-picture-wrap">
					<a href="club?id=${item.id}">
						<div align="center">
							<h3 class="image-cover-t"><c:out value="${item.name}"/>
							</h3><img class="my-picture-club" src="${myBlockedClubsAvas[status.index]}" align="middle">
						</div>
					</a>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</div>