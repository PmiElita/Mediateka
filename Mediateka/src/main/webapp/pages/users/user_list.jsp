<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="u" uri="../../WEB-INF/tld/showUsers.tld"%>
<!-- change path, remove one ../-->



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/users" var="msg" />
<fmt:setBundle basename="translations/search_book" var="msg1" />

<div id="userList" onscroll="alert(20);">
<u:showUsers users="${users}" locale="${cookie.lang.value}" adminId="${userId }"/>
</div>
<div id="empty">
<c:if test="${haveMoreResults }">
		<div class="row main-books">
			<button class="  waves-effect btn book-scroll"
				onclick="bodyScroll()">
				<fmt:message bundle="${msg1}" key="show_more_results" />
			</button>
			<p id="index" hidden>${index }</p>
		</div>
	</c:if>
</div>
