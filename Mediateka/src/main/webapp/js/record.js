var selDiv = "";
var storedImages = [];
var storedVideos = [];
var storedAudios = [];
var num;

function like(value, id) {
	// alert(value);
	$
			.ajax({
				type : 'get',
				url : 'likes',
				dataType : 'JSON',
				data : {
					"likeState" : value,
					"contentGroupId" : id
				},
				complete : function(data) {

					// alert(JSON.stringify(data));
					// alert(data.responseJSON);
					// alert(data.responseJSON.id);
					var id = data.responseJSON.id;
					document.getElementById("recordLike" + id).innerHTML = data.responseJSON.like;
					document.getElementById("recordDislike" + id).innerHTML = data.responseJSON.dislike;
				}
			});

}
$(document).ready(function() {
	$("#image").on("change", handleFileSelect);
	$("#video").on("change", handleFileSelect);
	$("#audio").on("change", handleFileSelect);

	$("#recordForm").on("submit", handleForm);

	$("body").on("click", ".selFile", removeFile);
});

function handleFileSelect(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	filesArr
			.forEach(function(f) {

				if (f.type.match("image.*")) {
					storedImages.push(f);
					selDiv = $("#selectedImages");
					alert(f);

					var reader = new FileReader();
					reader.onload = function(e) {
						var html = "<img src=\""
								+ e.target.result
								+ "\" data-file='"
								+ f.name
								+ "'height='100' class='selFile' title='Click to remove'><button name='"
								+ f.name + "'>x</button" + f.name
								+ "<br clear=\"left\"/>";
						selDiv.append(html);

					}
					reader.readAsDataURL(f);
				} else if (f.type.match("video.*")) {
					storedVideos.push(f);
					alert(f);
					selDiv = $("#selectedVideos");

					var reader = new FileReader();
					reader.onload = function(e) {

						var html = "<div><video width = '400' class='selFile' title='Click to remove' controls><source src=\""
								+ e.target.result + "\"></video></div>";
						selDiv.append(html);
					}
					reader.readAsDataURL(f);
				} else if (f.type.match("audio.*")) {
					storedAudios.push(f);
					alert(f);

					selDiv = $("#selectedAudios");

					var reader = new FileReader();
					reader.onload = function(e) {

						var html = "<div><audio class='selFile' title='Click to remove' controls><source src=\""
								+ e.target.result + "\"></audio></div>";
						selDiv.append(html);

					}
					reader.readAsDataURL(f);
				} else {
					return;
				}
			});
}

function handleForm(e) {
	alert(1);

	e.preventDefault();
	var data = new FormData();
	data.append('text', document.getElementById('text').value);
	data.append('clubId', document.getElementById('clubId').innerHTML
			.toString());
	if (document.getElementById('eventId') != null) {
	data.appent('eventId', document.getElementById('clubId').innerHTML
			.toString());
	}
	for (var i = 0, len = storedImages.length; i < len; i++) {
		data.append('image', storedImages[i]);
	}
	for (var i = 0, len = storedVideos.length; i < len; i++) {
		data.append('video', storedVideos[i]);
	}
	for (var i = 0, len = storedAudios.length; i < len; i++) {
		data.append('audio', storedAudios[i]);
	}
	if ((document.getElementById('text').value == "")
			&& (storedImages.length == 0) && (storedAudios.length == 0)
			&& (storedVideos.length == 0)) {
		return;
	}

	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'loadRecord', true);

	xhr.onload = function(e, data) {
		if (this.status == 200) {
			document.getElementById("recordForm").reset();
			document.getElementById("selectedImages").innerHTML = "";
			storedImages = [];
			storedVideos = [];
			storedAudios = [];
			document.getElementById("selectedVideos").innerHTML = "";
			document.getElementById("selectedAudios").innerHTML = "";
			alert(JSON.stringify(e.currentTarget));
			var responseJSON = JSON.parse(e.currentTarget.responseText);
			// document.getElementById('recordList').appendChild(
			// document.createTextNode());
			var date = new Date(responseJSON["contentGroup"].creationDate);
			alert(JSON.stringify(responseJSON));
			var dateString = date.getDate() + "." + date.getMonth() + "."
					+ date.getFullYear() + " " + date.getHours() + ":"
					+ date.getMinutes();
			recordList = $("#uploaded");
			var html = "<div class=\"main-info container z-depth-1\"> <div class=\"row\">"
					+ dateString;
			var htmlText = responseJSON["contentGroup"].text;
			html += htmlText;
			var htmlImage = "";
			var htmlVideo = "";
			var htmlAudio = "";
			for (var i = 0; i < responseJSON["mediaList"].length; i++) {
				if (responseJSON["mediaList"][i].type == "IMAGE") {
					htmlImage += "<img src='"
							+ responseJSON["mediaList"][i].path
							+ "' width='200' height='150'> ";
				}
				if (responseJSON["mediaList"][i].type == "VIDEO") {
					htmlVideo = "<video controls>" + "<source src='"
							+ responseJSON["mediaList"][i].path + "'</video>";
				}
				if (responseJSON["mediaList"][i].type == "AUDIO") {
					htmlAudio = "<audio controls>" + "<source src='"
							+ responseJSON["mediaList"][i].path + "'</audio>";
				}
			}
			html += htmlImage + htmlVideo + htmlAudio;
			html += "<a><span><i onclick='like('1');'"
					+ " class='tiny mdi-action-thumb-up waves-effect waves-green '>"
					+ "</i></span></a> <span "
					+ "id='recordLike"
					+ responseJSON["contentGroup"].id
					+ "'>"
					+ responseJSON["contentGroup"].like
					+ "</span>"
					+ "<a><span><i onclick='like('-1');' class='tiny mdi-action-thumb-down  waves-effect waves-red'></i></span></a>"
					+ "<span id='recordDislike"
					+ responseJSON["contentGroup"].id + "'>"
					+ responseJSON["contentGroup"].dislike
					+ "</span></div></div>";
			recordList.prepend(html);
			alert(html);
			alert(' items uploaded.');
		}
	}

	xhr.send(data);

}

function removeFile(e) {
	var file = $(this).data("file");
	for (var i = 0; i < storedImages.length; i++) {
		if (storedImages[i].name === file) {
			storedImages.splice(i, 1);
			break;
		}
	}
	for (var i = 0; i < storedVideos.length; i++) {
		if (storedVideos[i].name === file) {
			storedVideos.splice(i, 1);
			break;
		}
	}
	for (var i = 0; i < storedAudios.length; i++) {
		if (storedAudios[i].name === file) {
			storedAudios.splice(i, 1);
			break;
		}
	}
	$(this).parent().remove();
}