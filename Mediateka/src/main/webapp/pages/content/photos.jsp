<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script src="js/jssor.js"></script>
<script src="js/jssor.slider.js"></script>
<div id="photoList">
<c:forEach var="photo" items="${photos}" varStatus="loop">
	<div id="photId${photo.id}" class="col s4" style="margin-bottom: 3.0em;">
		<div class="col s12 m8 offset-m2 l6 offset-l2 my-back-card my-small-card" onclick="setOptions(${loop.index})">
			<div 
				class="my-album-card card-panel grey lighten-5 z-depth-1"
				style="padding: 0">
				<a title="${photo.name }" href="" data-target="modal33"
					class="modal-trigger">
					<div class="row valign-wrapper" style="margin: 0;">
						<div class="col s12 center" style="height: 12em;padding-left:0; padding-right:0;" >
							<img class="responsive-image"
								src="${photo.path}" alt=""
								style="margin: 0 0 0 0; height: 100%; width:100%;  border-radius: 5%">
						</div>
						</div>
				</a>
			</div>
		</div>
	</div>
	</c:forEach>
		<div id="modal33" class="modal black" style="width: 60%;">
		<div class="modal-content">
			<div class="row">
			<div id="slider1_container"
				style="position: relative; width: 600px; height: 500px;" class="col s12">


				<!-- Slides Container -->
				<div u="slides"
					style="cursor: move; position: relative; left: 0px; top: 0px; width: 700px; height: 500px; overflow: hidden; margin-left: 2.5em;">

					<c:forEach var="image" items="${photos }">
						<div>
							<img   u=image src="<c:out value='${image.path}'></c:out>">
						</div>
					</c:forEach>
				</div>
			</div>
</div>
		</div>
	</div>
	
	<script>
	var jssor_slider1
	function setOptions(index){
		jssor_slider1.$GoTo(index);
	}
        jQuery(document).ready(function ($) {

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

             jssor_slider1 = new $JssorSlider$("slider1_container", options);

        });
    </script>
    </div>