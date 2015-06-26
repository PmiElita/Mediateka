<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Crop Box</title>
<link rel="stylesheet" href="pages/event/crop/crop.css" type="text/css" />
<style>
.crop-container {
	position: absolute;
}

.action {
	width: 400px;
	height: 30px;
	margin: 10px 0;
}
</style>

<style>
.image-cover-t {
	color: white;
	position: relative;
	z-index: 1000;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>
</head>
<body>

	<script src="pages/event/crop/crop.js"></script>

	<div id="modal16" class="modal" style="width: 80%; background:transparent">
		<div class="modal-content">
			<div style="height: 30em">
				<div class="crop-container">

					<div class="imageBox">
						<div class="thumbBox"></div>
						<div class="spinner" style="display: none">Loading...</div>
					</div>
					<div class="action">
						<input type="file" id="file" style="float: left; width: 250px; color:white" class="image-cover-t">
						<input type="button" id="btnCrop" value="Confirm"
							style="float: right; margin-top:1em;"> <input type="button" id="btnZoomIn"
							value="+" style="float: right; margin-top:1em;"> <input type="button"
							id="btnZoomOut" value="-" style="float: right; margin-top:1em">
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(window).load(
				function() {
					var options = {
						thumbBox : '.thumbBox',
						spinner : '.spinner',
						imgSrc : 'avatar.png'
					}
					var cropper;
					$('#file').on('change', function() {
						var reader = new FileReader();
						reader.onload = function(e) {
							options.imgSrc = e.target.result;
							cropper = $('.imageBox').cropbox(options);
						}
						reader.readAsDataURL(this.files[0]);
						this.files = [];
					})
					$('#btnCrop').on(
							'click',
							function() {

								document.getElementById("ava").setAttribute(
										'style', 'margin-left: 0em;');
								var img = cropper.getDataURL()
								document.getElementById("ava").src = img;
								$('#modal16').closeModal();
							})
					$('#btnZoomIn').on('click', function() {
						cropper.zoomIn();
					})
					$('#btnZoomOut').on('click', function() {
						cropper.zoomOut();
					})
				});
	</script>
</body>
</html>