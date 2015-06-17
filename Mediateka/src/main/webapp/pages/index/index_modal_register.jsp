<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="modal2" class="modal">
	<div class="modal-content">

		<div id="creation_form">

			<form action="anonymousRegisterNewUser" method="post">
				<h3 class="titler">Register</h3>
				<button class="btn waves-effect blue titler" type="submit"
					name="action">
					Register <i class="mdi-content-send right"></i>
				</button>

				<div class="container">

					<div class="row">
						<div class="col s12">
							<div class="row">
								<div class="input-field col s4">
									<p>First name</p>
									<input id="firstName" name="firstName" type="text"
										class="validate">
								</div>
								<div class="input-field col s4">
									<p>Last name</p>
									<input id="lastName" name="lastName" type="text"
										class="validate">
								</div>
								<div class="input-field col s4">
									<p>Middle name</p>
									<input id="middleName" name="middleName" type="text"
										class="validate">
								</div>
							</div>

							<div class="row">
								<div class="input-field col s6">
									<p>Password</p>
									<input id="password" name="password" type="text"
										class="validate">
								</div>
								<div class="input-field col s6">
									<p>Confirm password</p>
									<input id="password" name="confirmPassword" type="text"
										class="validate">
								</div>
							</div>

							<div class="row">
								<div class="input-field col s3" style="margin-top: 0.5em">
									<p>Nationality</p>
									<input id="nationality" name="nationality" type="text"
										class="validate">
								</div>
								<div class="input-field col s3" style="margin-top: 0em">
									<p>Profession</p>
									<select id="profession" name="profession"
										class="browser-default" style="margin-top: 0.75em">
										<option value="1">Chemist</option>
										<option value="2">Linguist</option>
										<option value="3">Programmer</option>
										<option value="4">Manager</option>
										<option value="5">Scientist</option>
										<option value="6">Artist</option>
									</select>
								</div>
								<div class="input-field col s3" style="margin-top: 0em">
									<p>Education</p>
									<select id="education" name="education" class="browser-default"
										style="margin-top: 0.75em">
										<option value="1">Primary</option>
										<option value="2">Lower secondary</option>
										<option value="3">Upper secondary</option>
										<option value="4">Bachelor</option>
										<option value="5">Master</option>
										<option value="6">Doctoral</option>

									</select>
								</div>

								<div class="input-field col s3" style="margin-top: 0em">
									<p>Institution</p>
									<input id="eduInstitution" name="institution" type="text"
										class="validate" style="margin-top: 0.75em">
								</div>
							</div>


							<div class="row">
								<div class="input-field col s3">
									<p>Birth date</p>
									<input id="birthDate" name="birthDate" type="date"
										class="datepicker">
								</div>
								<div class="input-field col s3">
									<p>E-mail</p>
									<input id="email" name="email" type="text" class="validate">
								</div>
								<div class="input-field col s3">
									<p>Address</p>
									<input id="adress" name="address" type="text" class="validate">
								</div>
								<div class="input-field col s3">
									<p>Phone</p>
									<input id="phone" name="phone" type="text" class="validate">
								</div>

							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>