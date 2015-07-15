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

			<div class="main-info">
				<div class="section white">
					<div class="container">
						<h3>Edit main page info</h3>
						<button class="btn" onclick="saveInfo()">Save info</button>
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
							<div class="row">
								<div class="col s6">
									<fieldset>
										<h4>1 slide text</h4>
										<p style="color: black; padding-top: 5em;">Ua</p>
										<input type="text" name="ua1" value="${ua1}">
										<p style="color: black; padding-top: 5em;">En</p>
										<input type="text" name="en1" value="${en1}">
									</fieldset>
								</div>
							</div>

							<br>
							<div class="row">
								<div class="col s6">
									<fieldset>
										<h4>2 slide text</h4>
										<p style="color: black; padding-top: 5em;">Ua</p>
										<input type="text" name="ua2" value="${ua2}">
										<p style="color: black; padding-top: 5em;">En</p>
										<input type="text" name="en2" value="${en2}">
									</fieldset>
								</div>
							</div>

							<br>
							<div class="row">
								<div class="col s6">
									<fieldset>
										<h4>3 slide text</h4>
										<p style="color: black; padding-top: 5em;">Ua</p>
										<input type="text" name="ua3" value="${ua3}">
										<p style="color: black; padding-top: 5em;">En</p>
										<input type="text" name="en3" value="${en3}">
									</fieldset>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../general/footer.jsp" />
	<script type="text/javascript">
		function saveInfo() {
			var infoText1 = document.getElementsByName("infoText1")[0].value;
			var infoText2 = document.getElementsByName("infoText2")[0].value;
			var ua1 = document.getElementsByName("ua1")[0].value;
			var ua2 = document.getElementsByName("ua2")[0].value;
			var ua3 = document.getElementsByName("ua3")[0].value;
			var en1 = document.getElementsByName("en1")[0].value;
			var en2 = document.getElementsByName("en2")[0].value;
			var en3 = document.getElementsByName("en3")[0].value;
			$.ajax({
				url : 'updateInfo',
				type : 'get',
				data : {
					infoText1 : infoText1,
					infoText2 : infoText2,
					ua1 : ua1,
					ua2 : ua2,
					ua3 : ua3,
					en1 : en1,
					en2 : en2,
					en3 : en3,
				},
				success : function(data) {
					Materialize.toast('Info saved!', 2000)
				}
			});
		}
	</script>
</body>
</html>