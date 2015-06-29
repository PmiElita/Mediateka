<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<jsp:include page="../general/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="js/viewImage.js"></script>
<title>Load album</title>


</head>
<body>
	<div class="main">

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>

			<jsp:include page="../user/user_side_nav.jsp" />

			<div class="container section white">
				<form id="myForm" action="loadAlbum" method="post"
					enctype="multipart/form-data">

					<div class="row">
						Album name:<input type="text" id="name" name="name" required
							pattern=".{1,45}" form="createClub"><br>
					</div>

					Files:
					<div class="row">
						<div class="col s3">
							<div class="row">
								<div class="file-field input-field">
									<input class="file-path validate" type="hidden" />
									<div class="btn">
										<span>Choose files</span> <input type="file" id="image"
											name="image" onchange="readURL(this);" accept="image/*" />
									</div>
									<label id="number" hidden="true">1</label>
								</div>
							</div>
							<div class="row" style="margin-top: 5em">
								<button class="btn" type="submit">Upload</button>
							</div>
						</div>
						<div class="col s9">
							<div id="selectedFiles"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../general/footer.jsp" />
</body>

</html>