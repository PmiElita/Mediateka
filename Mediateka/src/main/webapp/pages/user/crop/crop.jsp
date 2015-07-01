<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Crop Box</title>
<link rel="stylesheet" href="pages/user/crop/crop.css" type="text/css" />
<style>
.crop-container {
	position: absolute;
}

.action {
	width: 425px;
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

	<script src="pages/user/crop/crop.js"></script>

	<div id="modal21" class="modal"
		style="width: 33%; background: transparent">
		<div class="modal-content">
			<div style="height: 30em">
				<div class="crop-container">

					<div class="imageBox">
						<div class="thumbBox"></div>
						<div class="spinner" style="display: none">Loading...</div>
					</div>
					
					<div class="action">
						<div>
							<input type="file" id="file"
								style="float: left; width: 225px; color: white"
								class="image-cover-t">
						</div>
							<input type="button" id="btnCrop" value="Confirm"
								style="float: center;"> <input
								type="button" id="btnZoomIn" value="+"
								style="float: center;"> <input
								type="button" id="btnZoomOut" value="-"
								style="float: center;">
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
								var img = cropper.getDataURL()
								document.getElementById("avatar").src = img;
								$('#modal21').closeModal();
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