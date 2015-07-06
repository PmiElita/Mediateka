<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript"
	src="js/jquery-2.1.4.min.js"></script>

<script type="text/javascript" src="js/materialize.js"></script>

<script src="js/imageView.js"></script>

<script src="js/eventCreation.js"></script>

<script src="js/date.js"></script>

<script src="js/crop.js"></script>

<script src= "js/eventCreation.js"></script>

<script type="text/javascript" src="js/DateTimePicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
//		$('.materialboxed').materialbox();

		$(".dropdown-button").dropdown();

		$('.modal-trigger').leanModal();

		$('.parallax').parallax();

		$('.slider').slider({
			full_width : true
		});

		$("#dtBox").DateTimePicker();
		$("#dtBox1").DateTimePicker();
		$("#dtBox2").DateTimePicker();

		$('select').material_select();

		$('ul.tabs').tabs();
		
	    $('.collapsible').collapsible({
	        accordion : false
	      });
	});
</script>



<!-- Side nav -->
<script type="text/javascript">
	$(document).ready(function() {
		$("[data-toggle]").click(function() {
			var toggle_el = $(this).data("toggle");
			$(toggle_el).toggleClass("open-sidebar");
		});

	});
	$(".swipe-area").swipe(
			{
				swipeStatus : function(event, phase, direction, distance,
						duration, fingers) {
					if (phase == "move" && direction == "right") {
						$(".container").addClass("open-sidebar");
						return false;
					}
					if (phase == "move" && direction == "left") {
						$(".container").removeClass("open-sidebar");
						return false;
					}
				}
			});
</script>