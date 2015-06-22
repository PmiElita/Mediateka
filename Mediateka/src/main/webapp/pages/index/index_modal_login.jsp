<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="translations/index" var="msg" scope="session" />


<div id="modal1" class="modal">
	<div class="modal-content">
		<form action="login" method="post">
			<div class="container" style="margin-top: 1em">
				<button class="btn waves-effect blue titler" type="submit"
					name="login">
					<fmt:message bundle="${msg}" key="login_header" />
					<i class="mdi-content-send right"></i>
				</button>
				<div class="row" style="margin-top: 1em">
					<div class="col s12">

						<div class="input-field col s6">
							<i class="mdi-action-account-circle prefix"></i> <input
								id="login_log" class="validate" type="text" name="email">
							<label class="active" for="login_log"> <fmt:message
									bundle="${msg}" key="email_field" />
							</label>
						</div>

						<div class="input-field col s6">
							<i class="small mdi-communication-vpn-key prefix"></i><input
								id="password_log" class="validate" type="password"
								name="password"> <label for="password_log"> <fmt:message
									bundle="${msg}" key="password_field" />
							</label>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>