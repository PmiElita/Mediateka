
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
	margin-top: 1em;
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
				class="my-admin-card card-panel grey lighten-5 z-depth-1">
				<a title="${album.name }" href="" data-target="albumView${album.id}"
					class="modal-trigger">
					<h3 style="color: black">${album.name }</h3>
					<div class="row valign-wrapper">
						<div class="col s9">
							<img src="${imageMap.get(album.id)[0].path}" alt=""
								class=" responsive-img">
						</div>
						<div class="club-badge" style="margin-left: 0.4em">${fn:length(imageMap.get(album.id)) }</div>
					</div>
				</a>
			</div>
		</div>
	</div>

	<div id="albumView${album.id}" class="modal black">
		<div class="modal-content">

			<div id="slider1_container"
				style="position: relative; width: 600px; height: 515px;">


				<!-- Slides Container -->
				<div u="slides"
					style="cursor: move; position: relative; left: 0px; top: 0px; width: 725px; height: 500px; overflow: hidden; margin-left: 7.5em;">

					<c:forEach var="image" items="${imageMap.get(album.id) }">
						<div>
							<img u=image src="<c:out value='${image.path}'></c:out>">
						</div>
					</c:forEach>
				</div>
				<a style="display: none" href="http://www.jssor.com">Bootstrap
					Slider</a>
			</div>


		</div>
	</div>

	<script>
        jQuery(document).ready(function ($) {

		var _SlideshowTransitions = [
            //Fade
            { $Duration: 1200, $Opacity: 2 }
            ];

            var options = {
                $SlideDuration: 500,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
                $DragOrientation: 3,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)
                $AutoPlay: true,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
                $AutoPlayInterval: 1500,                            //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
                $SlideshowOptions: {                                //[Optional] Options to specify and enable slideshow or not
                    $Class: $JssorSlideshowRunner$,                 //[Required] Class to create instance of slideshow
                    $Transitions: _SlideshowTransitions,            //[Required] An array of slideshow transitions to play slideshow
                    $TransitionsOrder: 1,                           //[Optional] The way to choose transition to play slide, 1 Sequence, 0 Random
                    $ShowLink: true                                    //[Optional] Whether to bring slide link on top of the slider when slideshow is running, default value is false
                }
            };

            var jssor_slider1 = new $JssorSlider$("slider1_container", options);

        });
    </script>





	<!-- 	<div id="albumView${album.id}" class="modal">
		<div class="modal-content">
			<div class="container">
				<div class="fotorama" id='album${album.id }' data-nav="thumbs"
					data-width="700" data-maxwidth="100%" data-ratio="16/9"
					data-allowfullscreen="true" data-nav="thumbs" data-keyboard="true"
					align="center" data-swipe="true">
					<c:forEach var="image" items="${imageMap.get(album.id) }">

						<a href="<c:out value='${image.path}'></c:out>"
							data-thumb="<c:out value='${image.path}'></c:out>"> <img
							src="<c:out value='${image.path}'></c:out>" data-fit="scaledown">
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div> -->
	<%-- 		<div class="fotorama" id='album${album.id }' data-nav="thumbs" --%>
	<!-- 			data-width="700" data-maxwidth="100%" data-ratio="16/9" -->
	<!-- 			data-allowfullscreen="true" data-nav="thumbs" data-keyboard="true" -->
	<!-- 			align="center" data-swipe="true" data-caption="one"> -->
	<%-- 			<c:forEach var="image" items="${imageMap.get(album.id) }"> --%>
	<%-- 				<a href="<c:out value='${image.path}'></c:out>" --%>
	<%-- 					data-thumb="<c:out value='${image.path}'></c:out>"> <img --%>
	<%-- 					src="<c:out value='${image.path}'></c:out>" data-fit="scaledown"> --%>
	<!-- 				</a> -->
	<%-- 			</c:forEach> --%>
	<!-- 		</div> -->


</c:forEach>


<script>
	function loadAlbumPhoto(albumId) {
		alert(albumId);
	}
</script>