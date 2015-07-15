var selDiv = "";
var storedImages = [];
var storedVideos = [];
var storedAudios = [];
var num;

function deleteMedia(mediaId) {	
	$.ajax({
		type : 'get',
		url : 'deleteMedia',
		dataType : 'JSON',
		data : {
			'mediaId' : mediaId
		},
		complete : function(data) {
			// document.getElementById('recordId' + recordId).innerHTML = "";
			alert(mediaId);
			document.getElementById('mediaId' + mediaId).hidden = true;
			if (document.getElementById('restore' + mediaId) != null) {
				document.getElementById('restore' + mediaId).hidden = false;
			}
		}
	});
}

function restoreMedia(mediaId) {
	$.ajax({
		type : 'get',
		url : 'restoreMedia',
		dataType : 'JSON',
		data : {
			'mediaId' : mediaId
		},
		complete : function(data) {

			// document.getElementById('recordId' + recordId).innerHTML = "";
			document.getElementById('mediaId' + mediaId).hidden = false;
			if (document.getElementById('restore' + mediaId) != null) {
				document.getElementById('restore' + mediaId).hidden = true;

			}
		}
	});
}

function deleteRecord(recordId) {
	$.ajax({
		type : 'get',
		url : 'deleteRecord',
		dataType : 'JSON',
		data : {
			'recordId' : recordId
		},
		complete : function(data) {
			// document.getElementById('recordId' + recordId).innerHTML = "";
			document.getElementById('recordId' + recordId).hidden = true;
			document.getElementById('restore' + recordId).hidden = false;
		}
	});
}

function restoreRecord(recordId) {
	$.ajax({
		type : 'get',
		url : 'restoreRecord',
		dataType : 'JSON',
		data : {
			'recordId' : recordId
		},
		complete : function(data) {
			// document.getElementById('recordId' + recordId).innerHTML = "";
			document.getElementById('recordId' + recordId).hidden = false;
			document.getElementById('restore' + recordId).hidden = true;
		}
	});
}

function like(value, id) {
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

					var id = data.responseJSON.id;
					alert(document.getElementById("recordLike" + id).innerHTML);
					document.getElementById("recordLike" + id).innerHTML = data.responseJSON.like;
					alert(document.getElementById("recordLike" + id).innerHTML);
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
				selDiv = $("#selectedFiles");
				if (f.type.match("image.*")) {
					storedImages.push(f);
					// selDiv = $("#selectedImages");

					var reader = new FileReader();
					reader.onload = function(e) {

						var html = "<div id='"
								+ f.name
								+ "' class='col s3 center' style='height:130px'><img src=\""
								+ e.target.result
								+ "\" data-file='"
								+ f.name
								+ "'height='105' class='selFile toDelete' title='"
								+ recordJsTranslations["click_to_remove"]
								+ "'></div>";

						selDiv.prepend(html);

					}
					reader.readAsDataURL(f);
				} else if (f.type.match("video.*")) {
					storedVideos.push(f);
					// selDiv = $("#selectedVideos");

					var reader = new FileReader();

					reader.onload = function(e) {
						/*
						 * var html = "<div><video width = '400'
						 * class='selFile' title='Click to remove' controls><source
						 * src=\"" + e.target.result + "\"></video></div>";
						 * 
						 * selDiv.append(html);
						 */

						var html = "<div id='"
								+ f.name
								+ "' class='col s3 center' style='height:130px'><img src=\""
								+ "images/photo-video-start-icon.png"
								+ "\" data-file='"
								+ f.name
								+ "'height='105' class='selFile toDelete' title='"
								+ recordJsTranslations["click_to_remove"]
								+ "'>" + f.name + "</div>";
						selDiv.prepend(html);
					}

					reader.readAsDataURL(f);
				} else if (f.type.match("audio.*")) {
					storedAudios.push(f);

					// selDiv = $("#selectedAudios");

					var reader = new FileReader();
					reader.onload = function(e) {

						// var html = "<div><audio class='selFile' title='Click
						// to remove' controls><source src=\""
						// + e.target.result + "\"></audio></div>";

						var html = "<div id='"
								+ f.name
								+ "' class='col s3 center' style='height:130px'><img src=\""
								+ "images/AudioIcon2.png"
								+ "\" data-file='"
								+ f.name
								+ "'height='105' class='selFile toDelete' title='"
								+ recordJsTranslations["click_to_remove"]
								+ "'>" + f.name + "</div>";
						selDiv.prepend(html);

					}

					reader.readAsDataURL(f);
				} else {
					return;
				}
			});
}

function handleForm(e) {

	e.preventDefault();
	var data = new FormData();
	data.append('index', $('#index').text());
	data.append('text', document.getElementById('text').value);
	if (document.getElementById('clubId') != null) {
		data.append('clubId', document.getElementById('clubId').innerHTML
				.toString());
	}
	if (document.getElementById('eventId') != null) {
		data.append('eventId', document.getElementById('eventId').innerHTML
				.toString());
	}
	if (document.getElementById('likedTextarea') != null) {
		data.append('internetContent',
				document.getElementById('likedTextarea').value);
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
			&& (storedImages.length == 0)
			&& (storedAudios.length == 0)
			&& (storedVideos.length == 0)
			&& (document.getElementById('likedTextarea').value
					.indexOf("https://www.youtube.com/watch?v=") < 0)
			&& (document.getElementById('likedTextarea').value
					.indexOf("https://vimeo.com/") < 0)) {
		return;
	}

	var xhr = new XMLHttpRequest();
	xhr.upload
			.addEventListener(
					"progress",
					function(evt) {
						if (evt.lengthComputable) {
							var percentComplete = evt.loaded / evt.total;
							percentComplete = parseInt(percentComplete * 100);
							document.getElementById("progress").hidden = false;
							document.getElementById("determinate").style.width = percentComplete
									+ "%";
							if (percentComplete === 100) {
								document.getElementById("progress").hidden = true;
							}

						}
					}, false);
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
			document.getElementById("selectedFiles").innerHTML = "";
			var responseJSON = JSON.parse(e.currentTarget.responseText);

			loadIndex = document.getElementById('index').textContent;
			var loadEl = document.getElementById("load");
			loadIndex++;
			$("#index").text(loadIndex);
			loadEl.id = loadEl.id + loadIndex;
			$('#' + loadEl.id).load(
					"viewNewRecord?recordId=" + responseJSON["contentGroup"].id
							+ "&index="
							+ document.getElementById("index").textContent);

			Materialize.toast(recordJsTranslations["file_is_uploaded"], 2000);
		}
	}

	xhr.send(data);

}

function removeFile(e) {
	if (this.id == "link") {
		var name = new String(this.name);
		document.getElementById("likedTextarea").value = document
				.getElementById("likedTextarea").value.replace(name, "");
		document.getElementById(this.name).remove();
		return;
	}
	var file = $(this).data("file");
	document.getElementById(file).remove();
	for (var i = 0; i < storedImages.length; i++) {
		if (storedImages[i].name == file) {
			storedImages.splice(i, 1);

			break;
		}
	}
	for (var i = 0; i < storedVideos.length; i++) {
		if (storedVideos[i].name == file) {
			storedVideos.splice(i, 1);
			break;
		}
	}
	for (var i = 0; i < storedAudios.length; i++) {
		if (storedAudios[i].name == file) {
			storedAudios.splice(i, 1);
			break;
		}
	}
	$(this).remove();
}

function linkedVideoForm() {
	selDiv = $("#selectedFiles");

	var links = document.getElementById("likedTextarea").value;
	alert(links);
	var link = links.match(/\S+/g);
	for (var i = 0; i < link.length; i++) {
		if ((link[i].indexOf("https://www.youtube.com/watch?v=") > -1)
				|| (link[i].indexOf("https://vimeo.com/") > -1)) {
			var html = "<div id =\""
					+ link[i]
					+ "\" class='col s3 center' style='height:130px'><img id='link' name =\""
					+ link[i]
					+ "\"src= images/photo-video-start-icon.png height='105' class='selFile toDelete' title='"
					+ recordJsTranslations["click_to_remove"] + "'>" + link[i]
					+ "</div>";
			selDiv.prepend(html);
		} else {
			Materialize.toast(recordJsTranslations["some_links_are_incorrect"],
					2000);
		}
	}
}
function openBody(recordId, isNew) {
	if (isNew) {
		var body = document.getElementById("colbody" + recordId);
		if (body.style.display == "none") {
			body.style.display = "block";
		} else {
			body.style.display = "none";
		}
	}
}

function scrollRecords(){
	var index = document.getElementById("scrollIndex").innerHTML.toString();
	var scroll = document.getElementById("scroll");
	var clubId =null;
	var eventId = null;
	if (document.getElementById('clubId') != null) {
		clubId =document.getElementById('clubId').innerHTML
				.toString();
	}
	if (document.getElementById('eventId') != null) {
	    eventId = document.getElementById('eventId').innerHTML
				.toString();
	}
	scroll.id = "main" + index;
	var regexp = document.getElementById("index").innerHTML.toString()
	$('#' + scroll.id).load(
			"getMoreRecords?clubId=" + clubId + "&eventId=" + eventId+ "&index=" + index);
}
	

