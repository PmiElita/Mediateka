
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script type="text/javascript" src="js/materialize.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.materialboxed').materialbox();

		$(".dropdown-button").dropdown();

		$('.modal-trigger').leanModal();

		$('.parallax').parallax();

		$('.slider').slider({
			full_width : true
		});

		$('.datepicker').pickadate({
			selectMonths : true, // Creates a dropdown to control month
			selectYears : 15
		// Creates a dropdown of 15 years to control year
		});
		
		$('select').material_select();
		
		$('ul.tabs').tabs();
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