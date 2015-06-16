<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="modal2" class="modal">
	<div class="modal-content">

		<form>
			<h4 class="titler">Register</h4>

			<button class="btn waves-effect blue titler" type="submit"
				name="action">
				Register <i class="mdi-content-send right"></i>
			</button>

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
							<input id="login_reg" class="validate" type="text"> <label
								class="active" for="login_reg">Login*</label>
						</div>

						<div class="input-field col s6">
							<input id="password_reg" class="validate" type="password">
							<label for="password_reg">Password*</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s8">
							<input id="email" type="email" class="validate"> <label
								for="email">Email</label>
						</div>
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
					</div>
				</div>

			</div>
		</form>
	</div>
</div>