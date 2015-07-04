<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>MapChat</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
<jsp:include page="../general/head.jsp" />
   
    <link href="css/map.css" rel="stylesheet"/>
<link href="css/oleh_style.css" rel="stylesheet"/>

    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">
</head>

<body>

<jsp:include page="../general/nav.jsp" />
		


<div class=" container white">
<div class = "row">
<button class="btn-flat" onclick="saveChanges();">Save changes</button>
</div>
<div class="row mapchat">
<div id="map-canvas"></div>
</div>

   

 <div class="row input-field" >
     <input placeholder="Type an event's name" ng-model="dummyInputs.inputFieldInput" id="event_name" type="text" maxlength="101">
        <input placeholder="Type an event's description" id="event_descr" type="text" maxlength="101">
        <input placeholder="Type an event's adress" id="event_adress" type="text" maxlength="101">
    <button onclick="butt_click(this);" class="btn-flat" id="map_button">Add new event</button>
	</div>
</div>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.96.1/js/materialize.min.js"></script>
<script src="https://cdn.jsdelivr.net/sockjs/1.0.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vertx/2.0.0/vertxbus.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&language=en"></script>

<!-- <script type="text/javascript" src="javascript/modernizr.custom.js"></script> -->
<!-- <script type="text/javascript" src="javascript/html-sanitizer-minified.js"></script> -->
<!-- <script src="javascript/main.js"></script> -->
<script src="js/map.js"></script>
<script src="js/messages_map.js"></script>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-63921484-1', 'auto');
    ga('send', 'pageview');
</script>

</body>
</html>