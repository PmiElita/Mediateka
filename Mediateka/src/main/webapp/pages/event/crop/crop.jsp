<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Crop Box</title>
<link rel="stylesheet" href="pages/event/crop/crop.css" type="text/css" />
<style>
.crop-container {
	position: absolute;
	top: 10%;
	left: 10%;
	right: 0;
	bottom: 0;
}

.action {
	width: 400px;
	height: 30px;
	margin: 10px 0;
}

.cropped img {
	margin-left:5em;
}
</style>
</head>
<body>

	<script src="pages/event/crop/crop.js"></script>

	<div id="modal16" class="modal">
		<div class="modal-content">
			<div class="container" style="height: 33.5em">
				<div class="crop-container">


					<div class="imageBox">
						<div class="thumbBox"></div>
						<div class="spinner" style="display: none">Loading...</div>
					</div>
					<div class="action">
						<input type="file" id="file" style="float: left; width: 250px">
						<input type="button" id="btnCrop" value="Confirm"
							style="float: right"> <input type="button" id="btnZoomIn"
							value="+" style="float: right"> <input type="button"
							id="btnZoomOut" value="-" style="float: right">
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(window).load(function() {
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
			$('#btnCrop').on('click', function() {
				
				document.getElementById("ava").setAttribute('style', 'margin-left: 0em;');
				var img = cropper.getDataURL()
				document.getElementById("ava").src = img;
				$('#modal15').closeModal();
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