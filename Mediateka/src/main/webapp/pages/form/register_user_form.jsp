<div id="register_user_form">
	<h3 class="titler">Register new user</h3>

	<div class="container">

		<div class="row">
			<form class="col s12" method="post">
				<button class="btn waves-effect blue titler" type="submit"
					name="action">
					Submit <i class="mdi-content-send right"></i>
				</button>
				<div class="row">
					<div class="input-field col s3">
						<p>Form ID</p>
						<input id="form_id" type="text" class="validate" name="formId">
					</div>
					<div class="input-field col s3">
						<p>First name</p>
						<input id="first_name" type="text" class="validate"
							name="firstName">
					</div>
					<div class="input-field col s3">
						<p>Last name</p>
						<input id="last_name" type="text" class="validate" name="lastName">
					</div>
					<div class="input-field col s3">
						<p>Middle name</p>
						<input id="middle_name" type="text" class="validate"
							name="middleName">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s2">
						<p>Birth date</p>
						<input id="birth_date" type="date" class="datepicker"
							name="birthDate">
					</div>
					<div class="input-field col s2">
						<p>Nationality</p>
						<input id="login" type="text" class="validate" name="nationality">
					</div>
					<div class="input-field col s2">
						<p>Profession</p>
						<select id="profession" name="profession">
							<option value="1">Chemist</option>
							<option value="2">Linguist</option>
							<option value="3">Programmer</option>
							<option value="4">Manager</option>
							<option value="5">Scientist</option>
							<option value="6">Artist</option>
						</select>
					</div>
					<div class="input-field col s3">
						<p>Education</p>
						<select id="Education" name="education">
							<option value="1">Primary</option>
							<option value="2">Lower secondary</option>
							<option value="3">Upper secondary</option>
							<option value="4">Bachelor</option>
							<option value="5">Master</option>
							<option value="6">Doctoral</option>
						</select>
					</div>
					<div class="input-field col s3">
						<p>Institution</p>
						<select id="Institution" name="institution">
							<option value="1">NU "LP"</option>
							<option value="2">LNU</option>
							<option value="3">Academy of arts</option>
							<option value="4">LIET</option>
							<option value="5">InPhys</option>
						</select>
					</div>
				</div>


				<div class="row">
					<div class="input-field col s3">
						<p>E-mail</p>
						<input id="email" type="text" class="validate" name="email">
					</div>
					<div class="input-field col s3">
						<p>Phone</p>
						<input id="phone" type="text" class="validate" name="phone">
					</div>
					<div class="input-field col s3">
						<p>Address</p>
						<input id="address" type="text" class="validate" name="address">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>