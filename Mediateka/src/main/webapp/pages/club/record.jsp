<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="js/viewImage.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="myForm" enctype="multipart/form-data">
		<input type="hidden" name="clubId" id="clubId" value="1">
		<textarea name="text" id="text" placeholder="text"></textarea>
		<div>
			<input type="file" id="image" name="image" multiple accept="image/*">
			<input type="file" id="video" name="video" onchange="readURL(this)"
				accept="video/*"> <input type="file" id="audio" name="audio"
				onchange="readURL(this)" accept="audio/*"> <label
				id="number" hidden="true">1</label>

		</div>
		<div id="selectedFiles"></div>

		<input type="submit" value="upload">
	</form>	
	<form id="clubForm" action="record" method="post">		
	</form>
</body>
<script>
	var selDiv = "";
	var storedFiles = [];
	var num;

	// 	function isEmpty(form){
	// 		if(storedFiles.length > 0){
	// 			form.submit();
	// 		}

	// 	}
	$(document).ready(function() {
		$("#image").on("change", handleFileSelect);
		$("#video").on("change", handleFileSelect);

		selDiv = $("#selectedFiles");
		$("#myForm").on("submit", handleForm);

		$("body").on("click", ".selFile", removeFile);
	});

	function handleFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		filesArr
				.forEach(function(f) {

					// 					if (!f.type.match("image.*")) {
					// 						return;
					// 					}
					storedFiles.push(f);
					alert(f);

					var reader = new FileReader();
					reader.onload = function(e) {
						//						num = viewFile();
						// 						$('#' + 'photo' + num).attr('src', e.target.result)
						// 						.width(150).height(200);
						// 						$('#' + 'photo' + num).attr('data-file', f.name);
						// 						$('#' + 'photo' + num).attr('class', "selFile");
						var html = "<div><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selFile' title='Click to remove'><button name='"+f.name+"'>x</button>"
								+ f.name + "<br clear=\"left\"/></div>";
						selDiv.append(html);

					}
					reader.readAsDataURL(f);
				});

	}

	function handleForm(e) {
		e.preventDefault();
		var data = new FormData();

		for (var i = 0, len = storedFiles.length; i < len; i++) {
			data.append('image', storedFiles[i]);
		}

		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'loadRecord', true);

		xhr.onload = function(e) {
			if (this.status == 200) {
				console.log(e.currentTarget.responseText);
				alert(' items uploaded.');
				$("#clubForm").submit();
			}
		}

		xhr.send(data);
	}

	function removeFile(e) {
		var file = $(this).data("file");
		for (var i = 0; i < storedFiles.length; i++) {
			if (storedFiles[i].name === file) {
				storedFiles.splice(i, 1);
				break;
			}
		}
		$(this).parent().remove();
	}
</script>
</html>