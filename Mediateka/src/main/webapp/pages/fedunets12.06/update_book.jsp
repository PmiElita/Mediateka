<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page import="com.mediateka.model.enums.State" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html>
<html>
<head>
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/eventCreation.js"></script>
<script src="js/imageView.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update_book</title>
</head>
<body>
	${message}
	<form id="update_book" action="UpdateBook" method="post" enctype= "multipart/form-data">
		Book name:<input type="text" name="name" placeholder="book name..." pattern=".{1,45}" value="${book.getName()}"><br>
		Book author:<input type="text" name="author" placeholder="book author..." pattern=".{1,45}" value="${book.getAuthor()}"><br>
		<!-- book type selection --> 
		<p><select size="6" name="type" required>
    		<option disabled>Book type..</option>
    		<c:forEach var="types" items="${book_type}">
    		<c:choose>
    		<c:when test="${types.getId()==book.getTypeId()}">
    			<option value="${types.getId()}" selected><c:out value="${types.getName()}"/></option>
    		</c:when>
    		<c:otherwise>
    			<option value="${types.getId()}"><c:out value="${types.getName()}"/></option>
    		</c:otherwise>
    		</c:choose>
    		</c:forEach>
   		</select></p>
   		<!-- book meaning selection -->
   		<p><select size="6" name="meaning" required>
    		<option disabled>Book meaning...</option>
    		<c:forEach var="meanings" items="${book_meaning}">
    		<c:choose>
    		<c:when test="${meanings.getId()==book.getMeaningId()}">
    			<option value="${meanings.getId()}" selected><c:out value="${meanings.getName()}"/></option>
    		</c:when>
    		<c:otherwise>
    			<option value="${meanings.getId()}"><c:out value="${meanings.getName()}"/></option>
    		</c:otherwise>
    		</c:choose>
    		</c:forEach>
   		</select></p>
   		<!-- book language selection -->
   		<p><select size="6" name="language" required>
    		<option disabled>Book language...</option>
    		<c:forEach var="languages" items="${book_language}">
    		<c:choose>
    		<c:when test="${languages.getId()==book.getLanguageId()}">
    			<option value="${languages.getId()}" selected><c:out value="${languages.getName()}"/></option>
    		</c:when>
    		<c:otherwise>
    			<option value="${languages.getId()}"><c:out value="${languages.getName()}"/></option>
    		</c:otherwise>
    		</c:choose>
    		</c:forEach>
   		</select></p>
   		Book title:<input type="file" name="image" id="image" placeholder="book cover screenshot..." value="${book.getMediaId()}" onchange="readURL(this);"><img id="photo" src="${imagePath}"><br>
   		Book state:<c:choose>
   				   <c:when test="${book.getState()==State.ACTIVE}">
   				   <input type="radio" name="state" value="ACTIVE" checked>active
   				   </c:when>
   				   <c:otherwise>
   				   <input type="radio" name="state" value="ACTIVE">active
   				   </c:otherwise>
   				   </c:choose>
   				   <c:choose>
   				   <c:when test="${book.getState()==State.BLOCKED}">
   				   <input type="radio" name="state" value="BLOCKED" checked>blocked
   				   </c:when>
   				   <c:otherwise>
   				   <input type="radio" name="state" value="BLOCKED">blocked
   				   </c:otherwise>
   				   </c:choose>
   				   <c:choose>
   				   <c:when test="${book.getState()==State.DELETED}">
   				   <input type="radio" name="state" value="DELETED" checked>deleted
   				   </c:when>
   				   <c:otherwise>
   				   <input type="radio" name="state" value="DELETED">deleted
   				   </c:otherwise>
   				   </c:choose><br>
   		<input type="submit" value="Update book">
	</form>
</body>
</html>