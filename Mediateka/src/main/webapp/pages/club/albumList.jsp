
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<head>

<script src="js/jssor.js"></script>
<script src="js/jssor.slider.js"></script>

</head>


<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 0em;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>


<div id="load"></div>
<label id="index" hidden="true">${index}</label>

<c:forEach var="album" items="${records}">
	<div id="recordId${album.id}" class="col s3">
		<div class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
			<div onclick="loadAlbumPhoto(${album.id})"
				class="my-admin-card card-panel grey lighten-5 z-depth-1"
				style="padding: 0">
				<a title="${album.name }" href="" data-target="albumView${album.id}"
					class="modal-trigger">
					<h3 class="center image-cover-t">${album.name }<span
							style="color: transparent; opacity: 0">!</span>
					</h3>
					<div class="row valign-wrapper" style="margin: 0;">
						<div class="col s12 center" style="height: 12em; padding:0">
							<img class="responsive-image"
								src="${imageMap.get(album.id)[0].path}" alt=""
								style="margin: -4.4em 0 0 0; height: 100%; width:100%; border-radius: 5%">
						</div>
						<div class="club-badge"
							style="z-index: 10; margin-top: 0em; margin-left: -1.3em">${fn:length(imageMap.get(album.id)) }</div>
					</div>
				</a>
			</div>
		</div>
	</div>

	<div id="albumView${album.id}" class="modal black" style="width: 80%">
		<div class="modal-content">
			<div class="row">
												
			<c:forEach var="image" items="${imageMap.get(album.id) }">

					<img class="modal-trigger col" 
						src="<c:out value='${image.path}' ></c:out>" data-target="modal33"
						style="cursor: pointer; height: 10em; margin-top: 0.5em" onclick="startSlider()">
				</c:forEach>
			</div>
		</div>
	</div>

	<div id="modal33" class="modal black" style="width: 60%;">
		<div class="modal-content">
			<div id="slider1_container"
				style="position: relative; width: 600px; height: 500px;">

			
				<!-- Slides Container -->
				<div u="slides"
					style="cursor: move; position: relative; left: 0px; top: 0px; width: 700px; height: 500px; overflow: hidden; margin-left: 2.5em;">

					<c:forEach var="image" items="${imageMap.get(album.id) }">
						<div>
							<img u=image src="<c:out value='${image.path}'></c:out>">
						</div>
					</c:forEach>
				</div>
			</div>


		</div>
	</div>

	<script>
	
        function startSlider() {

        	alert("hello");
    		var num;
    	
        	
        	
		var _SlideshowTransitions = [
            //Fade
            { $Duration: 1200, $Opacity: 2 }
            ];

			
		
            var options = {
            	$StartIndex : num,
            	$FillMode : 5,
                $SlideDuration: 500,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
                $DragOrientation: 3,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)
                $AutoPlay: false,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
                $AutoPlayInterval: 1500,                            //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
                $SlideshowOptions: {                                //[Optional] Options to specify and enable slideshow or not
                    $Class: $JssorSlideshowRunner$,                 //[Required] Class to create instance of slideshow
                    $Transitions: _SlideshowTransitions,            //[Required] An array of slideshow transitions to play slideshow
                    $TransitionsOrder: 1,                           //[Optional] The way to choose transition to play slide, 1 Sequence, 0 Random
                    $ShowLink: true                                    //[Optional] Whether to bring slide link on top of the slider when slideshow is running, default value is false
                }
            };

            var jssor_slider1 = new $JssorSlider$("slider1_container", options);

        };
    </script>

</c:forEach>


<script>
	function loadAlbumPhoto(albumId) {
		alert(albumId);
	}
</script>