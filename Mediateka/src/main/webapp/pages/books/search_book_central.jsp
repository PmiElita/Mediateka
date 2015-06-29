<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
	<script src="js/search_book.js" type="text/javascript">
</script>
<div id="main" class=" row main-books">
<c:forEach var="item" items="${bookItems }">
<div class="col s4 books">
 <div class="row" style="margin-bottom:5px;">
      <label class="user_label ">${item.value}</label>
 </div>
<img alt="Book name" src="${item.key}" width="100%">
</div>
</c:forEach>
</div>
<div id="scroll">
<c:if test="${haveMoreResults }">
<div  class="row main-books" >
<button class="  waves-effect waves-green btn-flat book-scroll" onclick="doScroll()"> show more results</button>
<p id="index" hidden>${index }</p>
</div>
</c:if>
</div>
