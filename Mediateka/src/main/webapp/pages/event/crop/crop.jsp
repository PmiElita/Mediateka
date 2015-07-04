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

<
style>.image-cover-t {
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

	<div id="modal16" class="modal" style="width: 75%; background: black">
		<div class="modal-content">
			<div style="height: 33em">
				<div class="crop-container"
					style="margin-top: 1em; margin-left: 0.5em">

					<div class="imageBox">
						<div class="thumbBox"></div>
						<div class="spinner" style="display: none">Loading...</div>
					</div>

					<div class="action" style="margin-top: -0.8em">

						<div class="row">
							<form id="eventAvaForm" enctype="multipart/form-data">
								<div class="file-field input-field col s3 offset-s3">
									<input class="file-path validate" type="hidden" />
									<div class="btn" style="width: 100%">
										<span>Choose image</span> <input type="file" id="file"
											name="image" onchange="readURL(this);" accept="image/*" />
									</div>
								</div>
							</form>
							<button class="btn col s1 offset-s1" id="btnZoomIn"
								style="margin-top: 1.5em; border-radius: 50%">
								<i>+</i>
							</button>
							<button class="btn col s1" id="btnZoomOut"
								style="margin-top: 1.5em; border-radius: 50%">
								<i>-</i>
							</button>
						</div>
						<div class="row" style="margin-top: -1em">
							<button class="btn col s12" id="btnCrop" type="submit"
								form="eventAvaForm" name="action">Confirm</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
		$(document).ready(function() {
			$("#eventAvaForm").on("submit", loadEventAva);

		});
		var cropper;
		var storedImages = [];
		function loadEventAva(e) {

			document.getElementById("ava").setAttribute('style',
					'margin-left: 0em;');			
			var img = cropper.getDataURL();
			storedImages.push(cropper.getBlob());
			document.getElementById("ava").src = img;			
			$('#modal16').closeModal();
			
			e.preventDefault();
			var data = new FormData();
			if (document.getElementById('eventId') != null) {
				data.append('eventId',
						document.getElementById('eventId').innerHTML.toString());
			}
			for (var i = 0, len = storedImages.length; i < len; i++) {
				data.append('image', storedImages[i]);
			}
			var xhr = new XMLHttpRequest();
			xhr.open('POST', 'loadEventAva', true);
			xhr.send(data);

		}

		$(window).load(function() {
			var options = {
				thumbBox : '.thumbBox',
				spinner : '.spinner',
				imgSrc : 'avatar.png'
			}
			
			$('#file').on('change', function() {
				var reader = new FileReader();
				reader.onload = function(e) {
					options.imgSrc = e.target.result;
					cropper = $('.imageBox').cropbox(options);
				}										
														
				reader.readAsDataURL(this.files[0]);
				this.files = [];
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