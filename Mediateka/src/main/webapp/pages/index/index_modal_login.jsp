<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/index" var="msg" scope="session" />
<fmt:setBundle basename="translations/forgot_password_form" var="msg1" scope="session" />
<jsp:useBean id="consts" class="com.mediateka.util.RegExps"
	scope="session" />
<script type="text/javascript" src="js/index.js"></script>
<div id="modal1" class="modal">
	<div class="modal-content">
		<form action="login" method="post" id="loginForm"
			onsubmit="return checkSubmit();">
			<div class="container" style="margin-top: 1em; width: 90%">
				<button style="margin-left: 4em" class="btn titler"
					type="submit" name="login">
					<fmt:message bundle="${msg}" key="login_header" />
				</button>
				<label id="message" style="color: red; margin-left:1em"></label>
				<div class="row" style="margin-top: 1em">
					<div class="input-field col s6">
						<i class="mdi-action-account-circle prefix"></i> <input
							id="login_log" class="validate" type="text" name="email"
							pattern="${consts.getEmail() }" required /> <label
							class="active" for="login_log"> <fmt:message
								bundle="${msg}" key="email_field" />
						</label>
					</div>

					<div class="input-field col s6">
						<i class="small mdi-communication-vpn-key prefix"></i><input
							id="password_log" class="validate" type="password"
							name="password" required /> <label for="password_log"> <fmt:message
								bundle="${msg}" key="password_field" />
						</label>
					</div>

					<div class="row">
						<div class="input-field col s6">
						<a title="<fmt:message bundle="${msg}"
									key="forgot_password" />"
					href="" data-target="forgotPasswordModal" class="modal-trigger waves-effect">
						<fmt:message bundle="${msg}"
									key="forgot_password" />
				</a>
							
						</div>
						<div class="col s6">
							<a href="vkLogin"><img alt="VK" src="images/vk.png"
								height="40px;"></a> <a href="googleLogin"><img
								alt="Google+" src="images/google.png" height="40px;"></a> <a
								href="facebookLogin"><img alt="Facebook"
								src="images/facebook.png" height="40px;"></a>
						</div>
					</div>

				</div>
			</div>
		</form>
	</div>
</div>

<div id="forgotPasswordModal" class="modal">
<div class="modal-content">
<c:if test="${notification ne null }">
					<fmt:message bundle="${msg1}" key="${notification}" />
				</c:if>


				<div class="row">
					<div class="input-field col s6">
						<p>
							<fmt:message bundle="${msg1}" key="email" />
						</p>
						<input id="emailFP" name="email" class="validate" required>
					</div>
				</div>

				<button class="btn waves-effect titler" type="submit"
					name="action" style="margin-bottom: 3.5em; margin-left:1em" onclick="show();">
					<fmt:message bundle="${msg1}" key="button" />
				</button>

</div>
</div>
<script type="text/javascript">
		function show() {
			
			if ( document.getElementById("emailFP").value.match("${consts.getEmail() }") == null ){
				Materialize.toast("<fmt:message bundle="${msg1}" key="wrong_email" />", 4000);
				return;
			}
	
			
			wrongEmail = false;
			$.ajax({
				url : 'checkemail',
				data : {
					email : document.getElementById("emailFP").value
				},
				success : function(data) {
					if (data == 'true') {
						Materialize.toast("<fmt:message bundle="${msg1}" key="wrong_email" />", 4000);
					} else {
						Materialize.toast("<fmt:message bundle="${msg1}" key="check_your_email" />", 4000);

						$.ajax({
							url : 'invalidatePassword',
							type : 'post',
							dataType : 'json',
							data : {
								email : document.getElementById("emailFP").value
							}
						});

					}
				}
			});

		}
	</script>