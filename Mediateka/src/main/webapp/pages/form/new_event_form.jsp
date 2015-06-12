<div id="modal3" class="modal">
	<div class="modal-content">
		<div id="creation_form">
			<h3 class="titler">Create new event</h3>
			<button class="btn waves-effect blue titler" type="submit"
				name="action">
				Create <i class="mdi-content-send right"></i>
			</button>

			<div class="container">

				<div class="row">
					<div class="input-field col s4">
						<p>Start date</p>
						<input id="start_date" type="date" class="datepicker">
					</div>
					<div class="input-field col s4 offset-s4">
						<p>End date</p>
						<input id="end_date" type="date" class="datepicker">
					</div>

				</div>

				<div class="row">
					<form class="col s12">
						<div class="row">
							<div class="input-field col s4">
								<p>Name</p>
								<input id="event_name" type="text" class="validate">
							</div>
							<div class="input-field col s4">
								<p>Type</p>
								<select id="event_type">
									<option value="1">Closed</option>
									<option value="2">18+</option>
									<option value="3">Artists</option>
									<option value="4">Technical</option>
								</select>
							</div>
							<div class="input-field col s4">
								<p>Club name</p>
								<input id="club_id" type="text" class="validate">
							</div>
						</div>

						<div class="row">
							<div class="input-field col s12">
								<p>Description</p>
								<textarea id="event_description" class="materialize-textarea"
									length="100"></textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
</div>