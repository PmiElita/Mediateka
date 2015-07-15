
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />
<fmt:requestEncoding value="utf-8" />



<style>
body {
	color: #666;
	font-family: sans-serif;
	line-height: 1.4;
}

h1 {
	color: #444;
	font-size: 1.2em;
	padding: 14px 2px 12px;
	margin: 0px;
}

h1 em {
	font-style: normal;
	color: #999;
}

a {
	color: #888;
	text-decoration: none;
}

#wrapper {
	width: 400px;
	margin: 40px auto;
}

ol {
	padding: 0px 30px 0px 0px;
	margin: 0px;
	list-style: decimal-leading-zero inside;
	color: #ccc;
	width: 460px;
	border-top: 1px solid #ccc;
	font-size: 0.9em;
}

ol li {
	position: relative;
	margin: 0px;
	padding: 9px 2px 10px;
	border-bottom: 1px solid #ccc;
	cursor: pointer;
}

ol li a {
	display: block;
	text-indent: -3.3ex;
	padding: 0px 0px 0px 10px;
	margin: -18px 0px 0px 0px;
}

li.playing {
	color: #aaa;
	text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.3);
}

li.playing a {
	color: #000;
}

li.playing:before {
	content: '♬';
	width: 14px;
	height: 14px;
	padding: 3px;
	line-height: 14px;
	margin: 0px;
	position: absolute;
	left: -24px;
	top: 9px;
	color: #000;
	font-size: 13px;
	text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.2);
}

.progress {
	margin: 0px;
	border-radius: 0px;
}

.audiojs .play-pause {
	padding: 3px 0px;
}
</style>



<script>
	$(function() {

		a = null;

		// Setup the player to autoplay the next track

		a = audiojs.createAll({
			trackEnded : function() {
				var next = $('ol li.playing').next();
				if (!next.length)
					next = $('ol li').first();
				next.addClass('playing').siblings().removeClass('playing');
				audio.load($('a', next).attr('data-src'));
				audio.play();
			}
		});

		// Load in the first track
		var audio = a[0];

				first = $('ol a').attr('data-src');
				$('ol li').first().addClass('playing');
				audio.load(first);

		// Load in a track on click
		$('ol li').click(function(e) {
			e.preventDefault();
			$('.playing').removeClass('playing');
			audio.pause();
			$(this).addClass('playing').siblings().removeClass('playing');
			audio.load($('a', this).attr('data-src'));
			audio.play();
		});
		// Keyboard shortcuts
		$(document).keydown(function(e) {
			var unicode = e.charCode ? e.charCode : e.keyCode;
			// right arrow
			if (unicode == 39) {
				var next = $('li.playing').next();
				if (!next.length)
					next = $('ol li').first();
				next.click();
				// back arrow
			} else if (unicode == 37) {
				var prev = $('li.playing').prev();
				if (!prev.length)
					prev = $('ol li').last();
				prev.click();
				// spacebar
			} else if (unicode == 32) {
				audio.playPause();
			}
		})
	});
</script>




<div  id="load"></div>
<label id="index" hidden="true">${index}</label>
<div style="position: fixed; top: 20%; z-index: 1; right: 2%"
	class="row col s1" align="left">
	<audio id='audioPlayer' preload="auto"></audio>
</div>
<div id="uploaded">
	<c:forEach var="audios" items="${records }">
		<c:forEach var="audio" items="${audioMap.get(audios.id) }">


			<li id="mediaId${audio.id}" class="col s1">
				<div class="row" style="margin-bottom: 0;">
					<div class="col s9 offset-s1" style="padding-top: -10px;">
						<a href="#" data-src="${audio.path }">${audio.name }</a>
					</div>
					<div class="col s2" style="margin: -20px 0px 0px 0px;">
						<c:if test="${userId eq audios.creatorId}">

							<span
								onclick="deleteMedia(${audio.id}); Materialize.toast('<fmt:message bundle="${msg}" key="record_central.deleted" />', 4000);"
								onmouseover="this.style.color = 'red';"
								onmouseleave="this.style.color = 'black';"
								class="waves-effect waves-circle waves-red">X</span>

						</c:if>
						<c:if test="${userId ne audios.creatorId}">
							<pre>
									<span> </span>
								</pre>
						</c:if>
					</div>
				</div>
			</li>


		</c:forEach>
	</c:forEach>
</div>

