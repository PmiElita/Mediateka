<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../general/head.jsp" />
</head>
<body>
	<jsp:include page="../general/nav.jsp" />




	<form action="modifyUser" method="post">

		<div class="input-field col s3">
			<p>First name</p>
			<input id="firstName" name="firstName" type="text" class="validate">
		</div>
		<div class="input-field col s3">
			<p>Last name</p>
			<input id="lastName" name="lastName" type="text" class="validate">
		</div>
		<div class="input-field col s3">
			<p>Middle name</p>
			<input id="middleName" name="middleName" type="text" class="validate">
		</div>

		<div class="input-field col s3" style="margin-top: 0.5em">
			<p>Nationality</p>
			<input id="nationality" name="nationality" type="text"
				class="validate">
		</div>
		<div class="input-field col s3">
			<p>Profession</p>
			<select id="profession" name="profession" class="browser-default"
				style="margin-top: 0.75em">
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
			<select id="education" name="education" class="browser-default"
				style="margin-top: 0.75em">
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

		<div class="input-field col s3">
			<p>Birth date</p>
			<input id="birthDate" name="birthDate" type="date" class="datepicker">
		</div>

		<div class="input-field col s3">
			<p>Address</p>
			<input id="adress" name="address" type="text" class="validate">
		</div>
		<div class="input-field col s3">
			<p>Phone</p>
			<input id="phone" name="phone" type="text" class="validate">
		</div>


		<button class="btn waves-effect blue titler" type="submit"
			name="action" style="margin-bottom: 3.5em">
			change data<i class="mdi-content-send right"></i>
		</button>

	</form>





</body>
</html>