$(document).ready( function(){
	connectComment();
});


var commentEndPointURL = "ws://" + window.location.host + "/Mediateka/eventComment";
var lastCommentTime;
var commentClient = null;
var indexes = {};
var showCommentIds={};
window.setInterval(function() {
	if (lastCommentTime != null) {
		if (new Date().getTime() - lastCommentTime.getTime() > 277000) {
			var userId = document.getElementById("userId").value;
			var eventId = document.getElementById("eventId").innerHTML;
			var jsonObj = {
				"userId" : userId,
				"eventId" : eventId,
				"message" : ""
			};
			lastCommentTime = new Date();
			commentClient.send(JSON.stringify(jsonObj));
		}
	}
}, 2000);
function connectComment() {
	index = 1;
	var eventId = document.getElementById("eventId").innerHTML;
	commentClient = new WebSocket(commentEndPointURL + "?eventId=" + eventId);
	lastCommentTime = new Date();
	commentClient.onmessage = function(event) {
		lastCommentTime = new Date();
		var jsonObj = JSON.parse(event.data);

		var userName;
		$
				.ajax({
					url : 'getUserData',
					type : 'get',
					dataType : 'json',
					data : {
						"userId" : jsonObj.userId
					},
					complete : function(data) {
						if (data.responseJSON.userName != null) {
							showComment(data.responseJSON.avaPath,data.responseJSON.userName,
									jsonObj.message, jsonObj.date, jsonObj.recordId, jsonObj.commentId,jsonObj.userId);
						}
					}

				});

	};
}

function showComment(avaPath,userName, messageText, date, recordId, commentId,userId) {

	var chat = document.getElementById("commentArea"+recordId);
	setCommentHeight(recordId);
	if (userName != null) {
		var message = userName + ": " + messageText + "\r\n";
		var noComments = document.getElementById("noComments"+recordId);
        if (noComments!=null){
		noComments.innerHTML = "";
        }
        var newComment ="";
		newComment =newComment
				+ "<div id = \"recordId"+commentId+"\" class=\"col s12 comment-item \">"
				+ "<div class=\"card-panel grey lighten-5 z-depth-1\" style=\"padding:5px;\">"
				+ "<div class=\"row valign-wrapper\" style=\"line-height: 20px; margin-bottom: 5px;\">"
				+ "<div class=\"col s1\">"
				+ "<img src=\""+avaPath+"\" class=\"circle responsive-img\">"
				+ "</div>" + "<div class=\"col s10\">"
				+ "<span class=\"comment_user_name\">" + escapeHtml(userName)
				+ " : </span>" + "<span class=\"comment-text\">"
				+ escapeHtml(messageText)
				+ "</span>  </div>";
		
		var currentUserId = document.getElementById("currentUserId").innerHTML;
		if (currentUserId == userId){
			newComment =newComment + "	<div class=\"col s1 \">"+
									"<span onclick=\"deleteRecord("+commentId+"); Materialize.toast('Deleted', 4000);\" "+
										"onmouseover=\"this.style.color = 'red';\" "+
										"onmouseleave=\"this.style.color = 'black';\" "+
										"class=\"waves-effect waves-circle waves-red\">X</span> "+
								"</div>";
		}
			newComment =newComment+"</div>  <div class=\"row message-date\"><span class=\"comment-date\">"+date+"</span>  <div align=\"right\"> "+
				"<a><span><i onclick=\"like('1',"+commentId+"); \""+
						"class=\"tiny mdi-action-thumb-up waves-effect waves-green \"></i></span></a> <span "+
					"id=\"recordLike"+commentId+"\">"+0+"</span> <a><span><i "+
						"onclick=\"like('-1',"+commentId+");\" "+
						"class=\"tiny mdi-action-thumb-down  waves-effect waves-red\"></i></span></a> <span "+
					"id=\"recordDislike"+commentId+"\">"+0+"</span> "+
			"</div></div> </div> </div>";
		chat.innerHTML = chat.innerHTML +newComment;
	}

	showCommentIds[recordId] = true;
     chat.scrollTop = chat.scrollHeight;
}

function addComment(recordId){

		var userId = document.getElementById("userId").value;
		var eventId = document.getElementById("eventId").innerHTML;

		var inputElement = document.getElementById("commentInput"+recordId);
		var message = inputElement.value.trim();
		if (message !== "") {
			var jsonObj = {
				"userId" : userId,
				"eventId" : eventId,
				"message" : message,
				"recordId" : recordId
			};
			commentClient.send(JSON.stringify(jsonObj));
			inputElement.value = "";
		}
		inputElement.focus();
}

function setCommentHeight(recordId){

	var commentArea = document.getElementById("commentArea"+recordId);
	
	if (!(recordId in indexes)){
		commentArea.style.height = "100px";
		indexes[recordId] = 1;
	}
	
	
	if (commentArea.style.height!="300px"){
		commentArea.style.height=(100*indexes[recordId])+"px";
	
	}
     
	 commentArea.scrollTop = (100*indexes[recordId])+"px";
		indexes[recordId]++;
		showCommentIds[recordId] = true;
}

window.setInterval(function() {
	for (var key in showCommentIds){
		var chat = document.getElementById("commentArea"+key);
	    var colBody = document.getElementById("colbody"+key);
    if (showCommentIds[key]&&colBody.style.display == "block"){
    	
    	chat.scrollTop = chat.scrollHeight;
    	showCommentIds[key] = false;
    }
	}
}, 200);

function escapeHtml(text) {
	var map = {
		'&' : '&amp;',
		'<': '&lt;',
    '>' : '&gt;',
		'"' : '&quot;',
		"'" : '&#039;'
	};

	return text.replace(/[&<>"']/g, function(m) {
		return map[m];
	});
}