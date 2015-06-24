<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="u" uri="../../WEB-INF/tld/showActivity.tld"%> <!-- change path, remove one ../-->
<!DOCTYPE html >

	<script type="text/javascript"
	src="js/activity.js"></script>
	<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />
<div class="main-activity" >

<div class="row select-activity" >

<select  class="browser-default col s3 "  id="period" onchange="reloadActivity(this)">
<option value="week" selected>week</option>
<option value="month">month</option>
<option value="allTime">all time</option>
</select>
</div>
<div class="row" id="formRecordsRow">
<div  class="col s12 " id= "formRecords">
<u:showUsers formRecords="${formRecords }"/>
</div>
</div>
</div>
