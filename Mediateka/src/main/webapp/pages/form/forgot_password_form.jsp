<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:useBean id="consts" class="com.mediateka.util.RegExps"
	scope="session" />

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/forgot_password_form" var="msg" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../general/head.jsp" />
<jsp:include page="../index/index_modal_login.jsp" />
<jsp:include page="../index/index_modal_register.jsp" />
</head>
<body>

	<div class=main>
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>
			<div class="container">
				<c:if test="${notification ne null }">
					<fmt:message bundle="${msg}" key="${notification}" />
				</c:if>


				<div class="row">
					<div class="input-field col s6">
						<p>
							<fmt:message bundle="${msg}" key="email" />
						</p>
						<input id="email" name="email" class="validate" required>
					</div>
				</div>

				<button class="btn waves-effect titler" type="submit"
					name="action" style="margin-bottom: 3.5em; margin-left:1em" onclick="show();">
					<fmt:message bundle="${msg}" key="button" />
				</button>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		function show() {
			wrongEmail = false;
			$.ajax({
				url : 'checkemail',
				data : {
					email : document.getElementById("email").value
				},
				success : function(data) {
					if (data == 'true') {
						Materialize.toast("wrong email", 1000);
					} else {
						Materialize.toast("check your email", 3000);

						$.ajax({
							url : 'invalidatePassword',
							type : 'post',
							dataType : 'json',
							data : {
								email : document.getElementById("email").value
							}
						});

					}
				}
			});

		}
	</script>
	<jsp:include page="../general/footer.jsp" />
</body>
</html>