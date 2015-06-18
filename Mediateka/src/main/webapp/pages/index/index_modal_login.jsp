<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="modal1" class="modal">
	<div class="modal-content">
		<form action="login" method="post">
			<div class="container" style="margin-top:1em">
				<button class="btn waves-effect blue titler" type="submit"
					name="login">
					Login <i class="mdi-content-send right"></i>
				</button>
				<div class="row" style="margin-top: 1em">
					<div class="col s12">

						<div class="input-field col s6">
							<i class="mdi-action-account-circle prefix"></i> <input
								id="login_log" class="validate" type="text" name="email">
							<label class="active" for="login_log">E-mail</label>
						</div>

						<div class="input-field col s6">
							<i class="small mdi-communication-vpn-key prefix"></i><input
								id="password_log" class="validate" type="password"
								name="password"> <label for="password_log">Password</label>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>