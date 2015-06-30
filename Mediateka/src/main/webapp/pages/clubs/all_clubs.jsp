<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="events_poster">
<div class="main-info">
<c:if test="${!allClubs.isEmpty()}">
<div class="section white">
	<div class="container">
	<c:forEach var="item" items="${allClubs}"  varStatus="status">
		<div class="row my-picture-row">
			<div class="col s8 offset-s2">
				<div class="waves-effect waves-block waves-light my-picture-wrap">
				<a href="club?clubId=${item.id}">
						<div align="center">
							<h3 class="image-cover-t"><c:out value="${item.name}"/>
							</h3><img class="my-picture-club" src="${allClubsAvas[status.index]}" align="middle">
							</div>
							</a>
							</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
</div>
</div>