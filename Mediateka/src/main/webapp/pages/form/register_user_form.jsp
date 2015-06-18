<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="modal4" class="modal">
	<div class="modal-content">

		<div id="creation_form">

			<form action="registerNewUser" method="post">
				<h3 class="titler">Register new user</h3>
				<button class="btn waves-effect blue titler" type="submit"
					name="action">
					Register <i class="mdi-content-send right"></i>
				</button>

				<div class="container">

					<div class="row">
						<div class="col s12">
							<div class="row">
								<div class="input-field col s3">
									<p>Form ID</p>
									<input id="formId" name="formId" type="text" class="validate">
								</div>
								<div class="input-field col s3">
									<p>First name</p>
									<input id="firstName" name="firstName" type="text"
										class="validate">
								</div>
								<div class="input-field col s3">
									<p>Last name</p>
									<input id="lastName" name="lastName" type="text"
										class="validate">
								</div>
								<div class="input-field col s3">
									<p>Middle name</p>
									<input id="middleName" name="middleName" type="text"
										class="validate">
								</div>
							</div>

							<div class="row">
								<div class="input-field col s3" style="margin-top:0.5em">
									<p>Nationality</p>
									<input id="nationality" name="nationality" type="text"
										class="validate">
								</div>
								<div class="input-field col s3">
									<p>Profession</p>
									<select id="profession" name="profession"
										class="browser-default" style="margin-top:0.75em">
										<option value="Chemist">Chemist</option>
										<option value="Linguist">Linguist</option>
										<option value="Programmer">Programmer</option>
										<option value="Manager">Manager</option>
										<option value="Scientist">Scientist</option>
										<option value="Artist">Artist</option>
									</select>
								</div>
								<div class="input-field col s3">
									<p>Education</p>
									<select id="education" name="education" class="browser-default" style="margin-top:0.75em">
										<option value="Primary">Primary</option>
										<option value="Lower secondary">Lower secondary</option>
										<option value="Upper secondary">Upper secondary</option>
										<option value="Bachelor">Bachelor</option>
										<option value="Master">Master</option>
										<option value="Doctoral">Doctoral</option>
										
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