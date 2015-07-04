<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link type="text/css" rel="stylesheet" href="css/oleh_style.css"
	media="screen,projection" />

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="translations/club_chat" var="msg" />
<fmt:requestEncoding value="utf-8" />

<nav>
	<div id="sideNav" class="side-nav" style="padding-bottom: 20px;">
		<div id="chat" class="chat row">

			<h3>
				<fmt:message bundle="${msg}" key="header" />
			</h3>

			<div class=" message-area" id="messageArea">
				<c:forEach var="chatMessage" items="${chatMessages }">
					<div class="col s12 ">
						<div class="card-panel grey lighten-5 z-depth-1"
							style="padding: 5px;">
							<div class="row valign-wrapper"
								style="line-height: 20px; margin-bottom: 5px;">
								<div class="col s2">

									<img src="media/book ava/images/dWxrVkt5dbECsuBp.jpg" alt=""
										class="circle responsive-img">
									<!-- notice the "circle" class -->

								</div>


								<div class="col s10">
									<span class="user_name"> <c:out
											value="${chatMessage.value.firstName }"></c:out> :
									</span> <span class="black-text"> <c:out
											value="${chatMessage.key.text}"></c:out>
									</span>
								</div>
							</div>

						</div>
					</div>

				</c:forEach>
			</div>
			<div class="panel input-area ">
				<textarea id="messageInput" class="materialize-textarea"
					placeholder="<fmt:message bundle="${msg}" key="message" />"></textarea>

				<button class=" col s4 offset-s9 btn" onclick="sendMessage();"><fmt:message bundle="${msg}" key="send" /></button>

			</div>

		</div>
	</div>

</nav>


<a href="#" class="chat-side-button" data-activates="sideNav" id="but"><i
	id="collapse-icon" class="mdi-communication-message"></i></a>
<script>
	$(document)
			.ready(
					function() {
						document.getElementById("messageArea").style.height = ($(
								window).height() - 293)
								+ 'px';
						$('#but').sideNav({
							menuWidth : 430, // Default is 240
							edge : 'left', // Choose the horizontal origin
							closeOnClick : true
						// Closes side-nav on <a> clicks, useful for Angular/Meteor
						});
					});

	$(window)
			.resize(
					function() {
						document.getElementById("messageArea").style.height = ($(
								window).height() - 293)
								+ 'px';
					});

	var endPointURL = "ws://" + window.location.host + "/Mediateka/chat";
	var lastMessageTime;
	var chatClient = null;
	var index;
	window.setInterval(function() {
		if (lastMessageTime != null) {
			if (new Date().getTime() - lastMessageTime.getTime() > 277000) {
				var jsonObj = {
					"userId" : userId,
					"clubId" : clubId,
					"message" : ""
				};
				lastMessageTime = new Date();
				chatClient.send(JSON.stringify(jsonObj));
			}
		}
	}, 2000);
	function connect() {
		index = 1;
		var objDiv = document.getElementById("messageArea");
		objDiv.scrollTop = objDiv.scrollHeight;
		var clubId = document.getElementById("clubId").innerHTML;
		chatClient = new WebSocket(endPointURL + "?clubId=" + clubId);
		lastMessageTime = new Date();
		chatClient.onmessage = function(event) {
			lastMessageTime = new Date();
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
								showMessage(data.responseJSON.userName,
										jsonObj.message);
							}
						}

					});

		};
	}

	function showMessage(userName, messageText) {

		var chat = document.getElementById("messageArea");
		if (userName != null) {
			var message = userName + ": " + messageText + "\r\n";

			chat.innerHTML = chat.innerHTML
					+ "<div class=\"col s12 \">"
					+ "<div class=\"card-panel grey lighten-5 z-depth-1\" style=\"padding:5px;\">"
					+ "<div class=\"row valign-wrapper\" style=\"line-height: 20px; margin-bottom: 5px;\">"
					+ "<div class=\"col s2\">"
					+ "<img src=\"media/book ava/images/dWxrVkt5dbECsuBp.jpg\" class=\"circle responsive-img\">"
					+ "</div>" + "<div class=\"col s10\">"
					+ "<span class=\"user_name\">" + escapeHtml(userName)
					+ " : </span>" + "<span class=\"black-text\">"
					+ escapeHtml(messageText)
					+ "</span></div> </div>  </div> </div>";
		}
		var objDiv = document.getElementById("messageArea");
		objDiv.scrollTop = objDiv.scrollHeight;
	}

	function disconnect() {
		chatClient.close();
	}

	function sendMessage() {
		var userId = document.getElementById("userId").value;
		var clubId = document.getElementById("clubId").innerHTML;

		var inputElement = document.getElementById("messageInput");
		var message = inputElement.value.trim();
		if (message !== "") {
			var jsonObj = {
				"userId" : userId,
				"clubId" : clubId,
				"message" : message
			};
			chatClient.send(JSON.stringify(jsonObj));
			inputElement.value = "";
		}
		inputElement.focus();
	}

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

	$('#messageArea')
			.scroll(
					function() {
						var chat = document.getElementById("messageArea");
						var chatHeight = chat.scrollHeight;

						if (chat.scrollTop === 0) {
							var clubId = document.getElementById("clubId").innerHTML;
							$
									.ajax({
										url : 'getMoreMessages',
										type : 'get',
										dataType : 'json',
										data : {
											"clubId" : clubId,
											"index" : index
										},
										complete : function(data) {
											for (var i = 0; i < data.responseJSON.length; i++) {

												chat.innerHTML = "<div class=\"col s12 \">"
														+ "<div class=\"card-panel grey lighten-5 z-depth-1\" style=\"padding:5px;\">"
														+ "<div class=\"row valign-wrapper\" style=\"line-height: 20px; margin-bottom: 5px;\">"
														+ "<div class=\"col s2\">"
														+ "<img src=\"media/book ava/images/dWxrVkt5dbECsuBp.jpg\" class=\"circle responsive-img\">"
														+ "</div>"
														+ "<div class=\"col s10\">"
														+ "<span class=\"user_name\">"
														+ escapeHtml(data.responseJSON[i].userName)
														+ " : </span>"
														+ "<span class=\"black-text\">"
														+ escapeHtml(data.responseJSON[i].message)
														+ "</span></div> </div>  </div> </div>"
														+ chat.innerHTML;
											}
											index = index + 1;

											chat.scrollTop = chat.scrollHeight
													- chatHeight;

										}
									});
						}

					})
	$('#messageInput').keydown(function(e) {

		if (e.ctrlKey && e.keyCode == 13) {
			this.value = this.value + "\r";
		} else if (e.keyCode == 13) {
			sendMessage();
		}
	});
</script>