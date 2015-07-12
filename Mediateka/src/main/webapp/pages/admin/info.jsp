<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>
<jsp:include page="../general/head.jsp" />
</head>

<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<jsp:include page="admin_side_nav.jsp" />

			<form name="updateInfo" action="updateInfo" method="post"
				enctype="multipart/form-data">
				<div class="main-info">
					<div class="section white">
						<div class="container">
							<h3>Edit main page info</h3>
							<input type="submit" value="Save changes" class="btn primary">
							<div class="row">
								<h5>Ua text:</h5>
								<textarea id="infoText1" name="infoText1"
									class="materialize-textarea info"><c:out
										value="${text1}" /></textarea>
								<h5>En text:</h5>
								<textarea id="infoText2" name="infoText2"
									class="materialize-textarea info"><c:out
										value="${text2}" /></textarea>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<h3>Edit slider images</h3>

								<div class="row">
									<div class="col s6">
										<img src="${imagePath1}" style="width: 20em; height: 20em"
											id="photo1" />
										<div class="file-field input-field">
											<input class="file-path validate" type="hidden" />
											<div class="btn">
												<span>Change image</span><input type="file" name="image"
													onchange="readURL(this,1);" />
											</div>
										</div>
									</div>
									<div class="col s6">
										<p style="color: black; padding-top: 5em;">Ua sign</p>
										<input type="text" name="ua1" value="${ua1}">
										<p style="color: black; padding-top: 5em;">En sign</p>
										<input type="text" name="en1" value="${en1}">
									</div>
								</div>

								<br>
								<div class="row">
									<div class="col s6">
										<img src="${imagePath2}" style="width: 20em; height: 20em"
											id="photo2" />
										<div class="file-field input-field">
											<input class="file-path validate" type="hidden" />
											<div class="btn">
												<span>Change image</span><input type="file" name="image"
													onchange="readURL(this,2);" />
											</div>
										</div>
									</div>
									<div class="col s6">
										<p style="color: black; padding-top: 5em;">Ua sign</p>
										<input type="text" name="ua2" value="${ua2}">
										<p style="color: black; padding-top: 5em;">En sign</p>
										<input type="text" name="en2" value="${en2}">
									</div>
								</div>

								<br>
								<div class="row">
									<div class="col s6">
										<img src="${imagePath3}" style="width: 20em; height: 20em"
											id="photo3" />
										<div class="file-field input-field">
											<input class="file-path validate" type="hidden" />
											<div class="btn">
												<span>Change image</span><input type="file" name="image"
													onchange="readURL(this,3);" />
											</div>
										</div>
									</div>
									<div class="col s6">
										<p style="color: black; padding-top: 5em;">Ua sign</p>
										<input type="text" name="ua3" value="${ua3}">
										<p style="color: black; padding-top: 5em;">En sign</p>
										<input type="text" name="en3" value="${en3}">
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
	<script>
		function readURL(input, id) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#photo' + id).attr('src', e.target.result);
				};
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</body>
</html>