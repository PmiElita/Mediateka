<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="modal2" class="modal">
	<div class="modal-content">

		<form action="registerNewUser" method="post">
			<h4 class="titler">Register</h4>

			<div class="row">
				<div class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<input id="first_name" type="text" class="validate"> <label
								for="first_name">First Name*</label>
						</div>
						<div class="input-field col s6">
							<input id="last_name" type="text" class="validate"> <label
								for="last_name">Last Name</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<input id="email" type="email" class="validate"> <label
								for="email">Email</label>
						</div>

						<div class="input-field col s6">
							<input id="password_reg" class="validate" type="password">
							<label for="password_reg">Password*</label>
						</div>
					</div>
					<div class="row" style="margin-left:7em">
						<div class="input-field col s2">
							<p>
								Male <input class="with-gap" name="gender" type="radio"
									id="male" />
							</p>
						</div>
						<div class="input-field col s2">
							<p>
								Female <input class="with-gap" name="gender" type="radio"
									id="female" />
							</p>
						</div>
						<button class="btn waves-effect blue titler" type="submit"
							name="register" style="margin-top:1.5em">
							Register <i class="mdi-content-send right"></i>
						</button>
					</div>
				</div>

			</div>
		</form>
	</div>
</div>