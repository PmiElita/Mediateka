
<link rel="stylesheet" type="text/css" href="css/DateTimePicker.css" />

<script type="text/javascript" src="js/DateTimePicker.js"></script>


<p>Time from:</p>
<input type="text" data-field="time" name="timeFrom" readonly>

<div id="dtBox"></div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#dtBox").DateTimePicker();
	});
</script>

